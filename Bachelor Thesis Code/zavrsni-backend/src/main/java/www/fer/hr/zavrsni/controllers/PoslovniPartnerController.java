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

import www.fer.hr.zavrsni.domain.Grad;
import www.fer.hr.zavrsni.domain.PoslovniPartner;
import www.fer.hr.zavrsni.dto.request.PoslovniPartnerRequest;
import www.fer.hr.zavrsni.service.GradService;
import www.fer.hr.zavrsni.service.PoslovniPartnerService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/poslovniPartner")
public class PoslovniPartnerController {
	
	public static final Logger logger = LoggerFactory.getLogger(PoslovniPartnerController.class);
	
	@Autowired
	PoslovniPartnerService poslovniPartnerService;
	
	@Autowired
	GradService gradService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<PoslovniPartner>> getPoslovniPartners() {
		return ResponseEntity.ok(poslovniPartnerService.listAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<PoslovniPartner> getPoslovniPartner(@PathVariable Integer id) {
		Optional<PoslovniPartner> poslovniPartner = poslovniPartnerService.findBySif(id);
		
		if (poslovniPartner.isEmpty()) {
			logger.error("Poslovni partner with id: " + id + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(poslovniPartner.get());
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addPoslovniPartner(@Valid @RequestBody PoslovniPartnerRequest partnerReq) {
		PoslovniPartner partner = new PoslovniPartner();
		
		Grad gradPartner = gradService.findBySifGrad(partnerReq.getSifGrad()).get();
		
		partner.setNazivPartner(partnerReq.getNazivPartner());
		partner.setBrTelPartner(partnerReq.getBrTelPartner());
		partner.setAdresaSjedistePartner(partnerReq.getAdresaSjedistePartner());
		partner.setOdgodaPartner(partnerReq.getOdgodaPartner());
		partner.setGrad(gradPartner);
		
		try {
			poslovniPartnerService.createPoslovniPartner(partner);
		} catch (Exception pgSqlExc) {
			logger.error(pgSqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(partner);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updatePartner(@PathVariable Integer id, @Valid @RequestBody PoslovniPartnerRequest partnerReq) {
		Optional<PoslovniPartner> mybPartner = poslovniPartnerService.findBySif(id);
		if(mybPartner.isEmpty()) {
			logger.error("Partner with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		
		PoslovniPartner partner = mybPartner.get();
		PoslovniPartner updatedPartner = partner;
		
		updatedPartner.setNazivPartner(partnerReq.getNazivPartner());
		updatedPartner.setBrTelPartner(partnerReq.getBrTelPartner());
		updatedPartner.setAdresaSjedistePartner(partnerReq.getAdresaSjedistePartner());
		updatedPartner.setOdgodaPartner(partnerReq.getOdgodaPartner());
		if (partnerReq.getSifGrad() != partner.getGrad().getSifGrad()) {
			updatedPartner.setGrad(gradService.findBySifGrad(partnerReq.getSifGrad()).get());
		}
		
		poslovniPartnerService.createPoslovniPartner(updatedPartner);
		
		logger.trace("izmjenjen partner: " + updatedPartner.getNazivPartner());
		return ResponseEntity.ok(updatedPartner);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deletePoslovniPartner(@PathVariable Integer id) {
		if (poslovniPartnerService.findBySif(id).isEmpty()){
			logger.error("Partner with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		poslovniPartnerService.deletePoslovniPartner(id);
		return ResponseEntity.ok("Zaposlenik uspješno izbrisan");
	}
}
