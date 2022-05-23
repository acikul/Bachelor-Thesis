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

import www.fer.hr.zavrsni.domain.Poslovnica;
import www.fer.hr.zavrsni.domain.Racun;
import www.fer.hr.zavrsni.domain.Skladiste;
import www.fer.hr.zavrsni.domain.StavkaRacuna;
import www.fer.hr.zavrsni.domain.Zaposlenik;
import www.fer.hr.zavrsni.dto.request.RacunRequest;
import www.fer.hr.zavrsni.service.PoslovnicaService;
import www.fer.hr.zavrsni.service.RacunService;
import www.fer.hr.zavrsni.service.SkladisteService;
import www.fer.hr.zavrsni.service.StavkaRacunaService;
import www.fer.hr.zavrsni.service.ZaposlenikService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/racun")
public class RacunController {
	
	private static final Logger logger = LoggerFactory.getLogger(RacunController.class);

	@Autowired
	RacunService racunService;
	@Autowired
	StavkaRacunaService stavkaService;
	@Autowired
	ZaposlenikService zaposlenikService;
	@Autowired
	SkladisteService skladisteService;
	@Autowired
	PoslovnicaService poslovnicaService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Racun>> getRacuns() {
		List<Racun> sviRacuni = racunService.listAll();
		
		for (Racun rac : sviRacuni) {
			Racun updatedRacun = rac;
			double ukupno = 0.0;
			List<StavkaRacuna> stavke = stavkaService.listByRacunSif(rac.getSifRacun());
			
			for (StavkaRacuna stavka : stavke) {
				ukupno += stavka.getKolicinaProizvodRacun()*stavka.getProizvod().getCijenaProizvod();
			}
			
			updatedRacun.setUkupnaCijena(ukupno);
			racunService.createRacun(updatedRacun);
		}
		
		return ResponseEntity.ok(racunService.listAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<Racun> getRacun(@PathVariable Integer id) {
		Optional<Racun> racun = racunService.findBySifRacun(id);
		
		if (racun.isEmpty()) {
			logger.error("Racun s id: " + id + " ne postoji!");
			return ResponseEntity.badRequest().build();
		}
		
		Racun updatedRacun = racun.get();
		double ukupno = 0.0;
		List<StavkaRacuna> stavke = stavkaService.listByRacunSif(id);
		
		for (StavkaRacuna stavka : stavke) {
			ukupno += stavka.getKolicinaProizvodRacun()*stavka.getProizvod().getCijenaProizvod();
		}
		
		updatedRacun.setUkupnaCijena(ukupno);
		racunService.createRacun(updatedRacun);
		
		return ResponseEntity.ok(updatedRacun);
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> addRacun(@Valid @RequestBody RacunRequest racunRequest) {
		
		Racun noviRacun = new Racun();
		Optional<Zaposlenik> mybZaposlenik= zaposlenikService.findBySifZaposlenik(racunRequest.getSifZaposlenik());
		Optional<Skladiste> mybSkladiste = skladisteService.findBySif(racunRequest.getSifSkladiste());
		Optional<Poslovnica> mybPoslovnica = poslovnicaService.findBySif(racunRequest.getSifPoslovnica());
		
		if (mybZaposlenik.isEmpty()) {
			logger.error("nema takvog zaposlenika sa id: " + racunRequest);
			return ResponseEntity.badRequest().build();
		}
		if (mybSkladiste.isEmpty()) {
			logger.error("nema takvog skladista sa id: " + racunRequest);
			return ResponseEntity.badRequest().build();
		}
		if (mybPoslovnica.isEmpty()) {
			logger.error("nema takve poslovnice sa id: " + racunRequest);
			return ResponseEntity.badRequest().build();
		}
		
		
		Zaposlenik zaposlenikRac = mybZaposlenik.get();
		Skladiste skladisteRac = mybSkladiste.get();
		Poslovnica poslovnicaRac = mybPoslovnica.get();
		
		noviRacun.setDatumRacun(racunRequest.getDatumRacun());
		noviRacun.setPlacen(racunRequest.getPlacenRacun());
		noviRacun.setPoslovnica(poslovnicaRac);
		noviRacun.setSkladiste(skladisteRac);
		noviRacun.setValutaRacun(racunRequest.getValutaRacun());
		noviRacun.setZaposlenik(zaposlenikRac);
		noviRacun.setUkupnaCijena(0.0);
		
		System.out.println(racunRequest.getValutaRacun());
		
		try {
			racunService.createRacun(noviRacun);
		} catch (Exception pgSqlExc) {
			logger.error(pgSqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(noviRacun);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> deleteRacun(@PathVariable Integer id) {
		if (racunService.findBySifRacun(id).isEmpty()) {
			logger.error("Račun with id: " + id + " can not be deleted because it does not exist");
			return ResponseEntity.badRequest().build();
		}
		racunService.deleteRacun(id);
		return ResponseEntity.ok("Račun with id: " + id + " uspješno izbrisan");
	}
	
}
