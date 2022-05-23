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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.Proizvod;
import www.fer.hr.zavrsni.domain.Proizvodac;
import www.fer.hr.zavrsni.dto.request.ProizvodRequest;
import www.fer.hr.zavrsni.service.ProizvodService;
import www.fer.hr.zavrsni.service.ProizvodacService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/proizvod")
public class ProizvodController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProizvodController.class);
	
	@Autowired
	private ProizvodService proizvodService;
	@Autowired
	private ProizvodacService proizvodacService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Proizvod>> getProizvods() {
		return ResponseEntity.ok(proizvodService.listAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<Proizvod> getProizvod(@PathVariable Integer id) {
		Optional<Proizvod> proizvod = proizvodService.findBySifProizvod(id);
		
		if(proizvod.isEmpty()) {
			logger.error("Proizvod id: " + id + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(proizvod.get());
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addProizvod(@Valid @RequestBody ProizvodRequest proizvodRequest) {
		Proizvod noviProizvod = new Proizvod();
		
		Optional<Proizvodac> proizvodac = proizvodacService.findBySifProizvodac(proizvodRequest.getSifProizvodac());
		if (proizvodac.isEmpty()) {
			logger.error("proizvodac s id: " + proizvodRequest.getSifProizvodac() + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		
		noviProizvod.setBarkodProizvod(proizvodRequest.getBarkodProizvod());
		noviProizvod.setNazivProizvod(proizvodRequest.getNazivProizvod());
		noviProizvod.setMjeraProizvod(proizvodRequest.getMjeraProizvod());
		noviProizvod.setVrstaProizvod(proizvodRequest.getVrstaProizvod());
		noviProizvod.setPodgrupaProizvod(proizvodRequest.getPodgrupaProizvod());
		noviProizvod.setKolicinaKutijaProizvod(proizvodRequest.getKolicinaKutijaProizvod());
		noviProizvod.setMasaProizvod(proizvodRequest.getMasaProizvod());
		noviProizvod.setCijenaProizvod(proizvodRequest.getCijenaProizvod());
		noviProizvod.setProizvodac(proizvodac.get());
		
		
		
		try {
			proizvodService.createProizvod(noviProizvod);
		} catch (Exception pSqlExc) {
			logger.error(pSqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(noviProizvod);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateProizvod(@PathVariable Integer id, @RequestBody ProizvodRequest proizvodRequest) {
		
		Optional<Proizvod> proizvodOpt = proizvodService.findBySifProizvod(id);
		if (proizvodOpt.isEmpty()) {
			logger.error("Proizvod with id: " + id + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		
		Optional<Proizvodac> proizvodac = proizvodacService.findBySifProizvodac(proizvodRequest.getSifProizvodac());
		if (proizvodac.isEmpty()) {
			logger.error("proizvodac s id: " + proizvodRequest.getSifProizvodac() + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		
		Proizvod updatedProizvod = proizvodOpt.get();
		if(proizvodRequest.getBarkodProizvod() != null) updatedProizvod.setBarkodProizvod(proizvodRequest.getBarkodProizvod());
		if(proizvodRequest.getNazivProizvod() != null) updatedProizvod.setNazivProizvod(proizvodRequest.getNazivProizvod());
		if(proizvodRequest.getMjeraProizvod() != null) updatedProizvod.setMjeraProizvod(proizvodRequest.getMjeraProizvod());
		if(proizvodRequest.getVrstaProizvod() != null) updatedProizvod.setVrstaProizvod(proizvodRequest.getVrstaProizvod());
		if(proizvodRequest.getPodgrupaProizvod() != null) updatedProizvod.setPodgrupaProizvod(proizvodRequest.getPodgrupaProizvod());
		if(proizvodRequest.getKolicinaKutijaProizvod() != null) updatedProizvod.setKolicinaKutijaProizvod(proizvodRequest.getKolicinaKutijaProizvod());
		if(proizvodRequest.getMasaProizvod() != null) updatedProizvod.setMasaProizvod(proizvodRequest.getMasaProizvod());
		if(proizvodRequest.getCijenaProizvod() != null) updatedProizvod.setCijenaProizvod(proizvodRequest.getCijenaProizvod());
		if(proizvodRequest.getSifProizvodac() != null) updatedProizvod.setProizvodac(proizvodac.get());
		
		proizvodService.createProizvod(updatedProizvod);
		
		logger.trace("izmjenjen proizvod " + updatedProizvod.getNazivProizvod());
		return ResponseEntity.ok(updatedProizvod);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProizvod(@PathVariable Integer id) {
		if (proizvodService.findBySifProizvod(id).isEmpty()) {
			logger.error("Proizvod with id: " + id + " cant be deleted because it does not exist");
			return ResponseEntity.badRequest().build();
		}
		proizvodService.deleteProizvod(id);
		return ResponseEntity.ok("Proizvod with id: " + id + " uspješno izbrisan");
	}
	
}
