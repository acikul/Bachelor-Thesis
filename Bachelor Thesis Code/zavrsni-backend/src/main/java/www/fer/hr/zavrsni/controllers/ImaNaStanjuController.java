package www.fer.hr.zavrsni.controllers;

import java.util.LinkedList;
import java.util.List;

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

import www.fer.hr.zavrsni.domain.ImaNaStanju;
import www.fer.hr.zavrsni.service.ImaNaStanjuService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stanje")
public class ImaNaStanjuController {

	private static final Logger logger = LoggerFactory.getLogger(ZaposlenikController.class);
	
	@Autowired
	private ImaNaStanjuService imaNaStanjuService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR')")
	public ResponseEntity<List<ImaNaStanju>> getStanja() {
		return ResponseEntity.ok(imaNaStanjuService.listAll());
	}
	
	@GetMapping("/skladiste/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<ImaNaStanju>> getStanjeSkladiste(@PathVariable Integer id) {
		List<ImaNaStanju> listaSvihStanja = imaNaStanjuService.listAll();
		List<ImaNaStanju> retLista = new LinkedList<>();
		
		for (ImaNaStanju stavka : listaSvihStanja) {
			if (stavka.getKompozitSif().getSifSkladiste() == id) {
				retLista.add(stavka);
			}
		}
		
		return ResponseEntity.ok(retLista);
	}
	
}
