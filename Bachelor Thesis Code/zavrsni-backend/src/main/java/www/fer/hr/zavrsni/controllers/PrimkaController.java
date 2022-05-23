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

import www.fer.hr.zavrsni.domain.PoslovniPartner;
import www.fer.hr.zavrsni.domain.Primka;
import www.fer.hr.zavrsni.domain.Skladiste;
import www.fer.hr.zavrsni.dto.request.PrimkaRequest;
import www.fer.hr.zavrsni.service.PoslovniPartnerService;
import www.fer.hr.zavrsni.service.PrimkaService;
import www.fer.hr.zavrsni.service.SkladisteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/primka")
public class PrimkaController {
	
	private static final Logger logger = LoggerFactory.getLogger(PrimkaController.class);

	@Autowired
	PrimkaService primkaService;
	@Autowired
	SkladisteService skladisteService;
	@Autowired
	PoslovniPartnerService partnerService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Primka>> getPrimkas() {
		List<Primka> svePrimke = primkaService.listAll();
		
		return ResponseEntity.ok(svePrimke);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<Primka> getPrimka(@PathVariable Integer id) {
		Optional<Primka> primka = primkaService.findBySifPrimka(id);
		
		if (primka.isEmpty()) {
			logger.error("Primka s id: " + id + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(primka.get());
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> addPrimka (@Valid @RequestBody PrimkaRequest primkaReq) {
		Primka novaPrimka = new Primka();
		
		Optional<Skladiste> mybSkladiste = skladisteService.findBySif(primkaReq.getSifSkladiste());
		Optional<PoslovniPartner> mybPartner = partnerService.findBySif(primkaReq.getSifPartner());
		if (mybSkladiste.isEmpty()) {
			logger.error("nema takvog skladista sa id: " + primkaReq);
			return ResponseEntity.badRequest().build();
		}
		if (mybPartner.isEmpty()) {
			logger.error("nema takvog partnera sa id: " + primkaReq);
			return ResponseEntity.badRequest().build();
		}
		
		novaPrimka.setDatumPrimka(primkaReq.getDatumPrimka());
		novaPrimka.setValutaPrimka(primkaReq.getValutaPrimka());
		novaPrimka.setSkladiste(mybSkladiste.get());
		novaPrimka.setPoslovniPartner(mybPartner.get());
		
		try {
			primkaService.createPrimka(novaPrimka);
		} catch (Exception sqlExc) {
			logger.error(sqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(novaPrimka);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> deletePrimka(@PathVariable Integer id) {
		if (primkaService.findBySifPrimka(id).isEmpty()) {
			logger.error("Primka with id: " + id + " can not be deleted as it does not exist");
			return ResponseEntity.badRequest().build();
		}
		primkaService.deletePrimka(id);
		return ResponseEntity.ok("Primka with id " + id + "successfully deleted!");
	}

}
