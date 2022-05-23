package www.fer.hr.zavrsni.controllers;

import java.util.LinkedList;
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

import www.fer.hr.zavrsni.domain.StavkaRacuna;
import www.fer.hr.zavrsni.domain.keys.StavkaRacunaKey;
import www.fer.hr.zavrsni.dto.request.StavkaRacunaRequest;
import www.fer.hr.zavrsni.service.ProizvodService;
import www.fer.hr.zavrsni.service.RacunService;
import www.fer.hr.zavrsni.service.StavkaRacunaService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stavke")
public class StavkaRacunaController {
	
	private static final Logger logger = LoggerFactory.getLogger(StavkaRacunaController.class);

	@Autowired
	private StavkaRacunaService stavkaRacunaService;
	
	@Autowired
	private RacunService racunService;
	
	@Autowired
	private ProizvodService proizvodService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR')")
	public ResponseEntity<List<StavkaRacuna>> getStanja() {
		return ResponseEntity.ok(stavkaRacunaService.listAll());
	}
	
	@GetMapping("/racun/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<StavkaRacuna>> getStavkeRacuna(@PathVariable Integer id) {
//		List<StavkaRacuna> listaSvihStavki = stavkaRacunaService.listAll();
//		List<StavkaRacuna> stavkeRacuna = new LinkedList<>();
//		
//		for (StavkaRacuna stavka : listaSvihStavki) {
//			if (stavka.getKompozitSif().getSifRacun() == id) {
//				stavkeRacuna.add(stavka);
//			}
//		}
		
		return ResponseEntity.ok(stavkaRacunaService.listByRacunSif(id));
	}
	
	@PostMapping("/racun/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> addStavkaRacuna(@PathVariable Integer id, @Valid @RequestBody StavkaRacunaRequest stavkaRacReq) {
		StavkaRacunaKey kompozitSif = new StavkaRacunaKey();
		kompozitSif.setSifProizvod(stavkaRacReq.getSifProizvod());
		kompozitSif.setSifRacun(id);
		
		Optional<StavkaRacuna> mybStavka = stavkaRacunaService.findByKompozitSif(kompozitSif);
		if (mybStavka.isPresent()) {
			logger.error("stavka with id: " + kompozitSif + "already exists.");
			return ResponseEntity.badRequest().build();
		}
		
		StavkaRacuna novaStavkaRacuna = new StavkaRacuna();
		novaStavkaRacuna.setKompozitSif(kompozitSif);
		novaStavkaRacuna.setKolicinaProizvodRacun(stavkaRacReq.getKolicinaProizvodRacun());
		
		if (racunService.findBySifRacun(id).isEmpty()) {
			logger.error("racun with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		if (proizvodService.findBySifProizvod(stavkaRacReq.getSifProizvod()).isEmpty()) {
			logger.error("racun with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		
		novaStavkaRacuna.setRacun(racunService.findBySifRacun(id).get());
		novaStavkaRacuna.setProizvod(proizvodService.findBySifProizvod(stavkaRacReq.getSifProizvod()).get());
	
		try {
			stavkaRacunaService.createStavkaRacuna(novaStavkaRacuna);
		} catch (Exception pgSqlExc) {
			logger.error(pgSqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(novaStavkaRacuna);
	}
	
	
	@DeleteMapping("/racun/{id}/proizvod/{sif}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> deleteStavkaRacun(@PathVariable Integer id, @PathVariable Integer sif) {
		StavkaRacunaKey kompozitSif = new StavkaRacunaKey();
		kompozitSif.setSifProizvod(sif);
		kompozitSif.setSifRacun(id);
		
		if (stavkaRacunaService.findByKompozitSif(kompozitSif).isEmpty()) {
			logger.error("stavka with id: " + kompozitSif + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		stavkaRacunaService.deleteStavkaRacuna(kompozitSif);
		return ResponseEntity.ok("stavka uspjesno izbrisana");
	}
	
	
}
