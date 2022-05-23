package www.fer.hr.zavrsni.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.Skladiste;
import www.fer.hr.zavrsni.service.SkladisteService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/skladiste")
public class SkladisteController {

	public static final Logger logger = LoggerFactory.getLogger(SkladisteController.class);
	
	@Autowired
	SkladisteService skladisteService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Skladiste>> getSkladistes() {
		return ResponseEntity.ok(skladisteService.listAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<Skladiste> getSkladiste(@PathVariable Integer id) {
		Optional<Skladiste> skladiste = skladisteService.findBySif(id);
		
		if (skladiste.isEmpty()) {
			logger.error("Skladiste with id: " + id + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(skladiste.get());
	}
	
}
