package www.fer.hr.zavrsni.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.ImaNaStanju;
import www.fer.hr.zavrsni.domain.Primka;
import www.fer.hr.zavrsni.domain.Proizvod;
import www.fer.hr.zavrsni.domain.StavkaPrimke;
import www.fer.hr.zavrsni.domain.keys.ImaNaStanjuKey;
import www.fer.hr.zavrsni.domain.keys.StavkaPrimkeKey;
import www.fer.hr.zavrsni.dto.request.StavkaPrimkeRequest;
import www.fer.hr.zavrsni.service.ImaNaStanjuService;
import www.fer.hr.zavrsni.service.PrimkaService;
import www.fer.hr.zavrsni.service.ProizvodService;
import www.fer.hr.zavrsni.service.SkladisteService;
import www.fer.hr.zavrsni.service.StavkaPrimkeService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stavkePrimki")
public class StavkaPrimkeController {

	private static final Logger logger = LoggerFactory.getLogger(StavkaPrimkeController.class);

	@Autowired
	private StavkaPrimkeService stavkaPrimkeService;
	
	@Autowired
	private PrimkaService primkaService;
	
	@Autowired
	private ProizvodService proizvodService;
	
	@Autowired
	private ImaNaStanjuService stanjeService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<StavkaPrimke>> getStavkePrimki() {
		return ResponseEntity.ok(stavkaPrimkeService.listAll());
	}
	
	@GetMapping("/primka/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<StavkaPrimke>> getStavkePrimke(@PathVariable Integer id) {
		return ResponseEntity.ok(stavkaPrimkeService.listByPrimkaSif(id));
	}
	
	@PostMapping("primka/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> addStavkaPrimke(@PathVariable Integer id, @Valid @RequestBody StavkaPrimkeRequest stavkaPrimReq) {
		Optional<Primka> mybPrimka = primkaService.findBySifPrimka(id);
		Optional<Proizvod> mybProizvod = proizvodService.findBySifProizvod(stavkaPrimReq.getSifProizvod());
		if (mybPrimka.isEmpty()) {
			logger.error("primka with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		if (mybProizvod.isEmpty()) {
			logger.error("proizvod with id: " + stavkaPrimReq.getSifProizvod() + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		
		StavkaPrimkeKey kompozitSif = new StavkaPrimkeKey();
		kompozitSif.setSifPrimka(id);
		kompozitSif.setSifProizvod(stavkaPrimReq.getSifProizvod());
		
		Optional<StavkaPrimke> mybStavka = stavkaPrimkeService.findByKompozitSif(kompozitSif);
		if(mybStavka.isPresent()) {
			logger.error("Stavka primke with id: " + kompozitSif.getSifPrimka()  + "," + kompozitSif.getSifProizvod() + " already exists");
			return ResponseEntity.badRequest().build();
		}
		
		StavkaPrimke novaStavkaPrimke = new StavkaPrimke();
		novaStavkaPrimke.setKompozitSif(kompozitSif);
		novaStavkaPrimke.setKolicinaProizvodPrimka(stavkaPrimReq.getKolicinaProizvodPrimka());
		novaStavkaPrimke.setKupovnaCijena(stavkaPrimReq.getKupovnaCijena());
		novaStavkaPrimke.setPrimka(mybPrimka.get());
		novaStavkaPrimke.setProizvod(mybProizvod.get());
		
		// ako nije prije bilo tog proizvoda na skladištu dodaje se sa stanjem 0
		List<ImaNaStanju> svaStanja = stanjeService.listAll();
		boolean postojiNaSkladistu = false;
		for (ImaNaStanju ins : svaStanja) {
			if (mybPrimka.get().getSkladiste().getSifSkladiste() == ins.getKompozitSif().getSifSkladiste()) {
				if (ins.getKompozitSif().getSifProizvod() == kompozitSif.getSifProizvod()) {
					postojiNaSkladistu = true;
					break;
				}
			}
		}
		if (!postojiNaSkladistu) {
			ImaNaStanjuKey insKey = new ImaNaStanjuKey();
			insKey.setSifProizvod(kompozitSif.getSifProizvod());
			insKey.setSifSkladiste(mybPrimka.get().getSkladiste().getSifSkladiste());
			ImaNaStanju ins = new ImaNaStanju();
			ins.setKompozitSif(insKey);
			ins.setKolicinaStanje(0);
			ins.setProizvod(mybProizvod.get());
			ins.setSkladiste(mybPrimka.get().getSkladiste());
			stanjeService.createImaNaStanju(ins);
		}
		//
		
		try {
			stavkaPrimkeService.createStavkaPrimke(novaStavkaPrimke);
		} catch (Exception sqlExc) {
			logger.error(sqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(novaStavkaPrimke);
	}
	
	@DeleteMapping("/primka/{id}/proizvod/{sif}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> deleteStavkaPrimke(@PathVariable Integer id, @PathVariable Integer sif) {
		StavkaPrimkeKey kompozitSif = new StavkaPrimkeKey();
		kompozitSif.setSifPrimka(id);
		kompozitSif.setSifProizvod(sif);
		
		if (stavkaPrimkeService.findByKompozitSif(kompozitSif).isEmpty()) {
			logger.error("stavka primke with id: " + kompozitSif + " can not be deleted as it does not exist");
			return ResponseEntity.badRequest().build();
		}
		try {
			stavkaPrimkeService.deleteStavkaPrimke(kompozitSif);
		} catch (Exception sqlExc) {
			logger.error("error deleting stavka primke:\n" + sqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok("stavka primke uspješno izbrisana");
	}
}















