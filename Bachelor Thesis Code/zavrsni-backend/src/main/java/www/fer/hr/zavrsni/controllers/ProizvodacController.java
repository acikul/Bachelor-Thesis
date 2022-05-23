package www.fer.hr.zavrsni.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.Proizvodac;
import www.fer.hr.zavrsni.service.ProizvodacService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/proizvodac")
public class ProizvodacController {
	private static final Logger logger = LoggerFactory.getLogger(ProizvodacController.class);
	
	@Autowired
	ProizvodacService proizService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR')")
	public ResponseEntity<List<Proizvodac>> getProizvodacs() {
		return ResponseEntity.ok(proizService.listAllProizvodacs());
	}
	

}
