package www.fer.hr.zavrsni.controllers;

import java.util.List;

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
import www.fer.hr.zavrsni.dto.request.PoslovnicaRequest;
import www.fer.hr.zavrsni.service.GradService;
import www.fer.hr.zavrsni.service.PoslovniPartnerService;
import www.fer.hr.zavrsni.service.PoslovnicaService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/poslovnica")
public class PoslovnicaController {
	
	public static final Logger logger = LoggerFactory.getLogger(PoslovnicaController.class);
	
	@Autowired
	PoslovnicaService poslovnicaService;
	
	@Autowired
	GradService gradService;
	
	@Autowired
	PoslovniPartnerService partnerService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Poslovnica>> allPoslovnicas() {
		return ResponseEntity.ok(poslovnicaService.listAll());
		
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addPoslovnica(@Valid @RequestBody PoslovnicaRequest poslovnicaReq) {
		Poslovnica novaPoslovnica = new Poslovnica();
		
		novaPoslovnica.setNazivPoslovnica(poslovnicaReq.getNazivPoslovnica());
		novaPoslovnica.setBrTelPoslovnica(poslovnicaReq.getBrTelPoslovnica());
		novaPoslovnica.setAdresaPoslovnica(poslovnicaReq.getAdresaPoslovnica());
		novaPoslovnica.setGrad(gradService.findBySifGrad(poslovnicaReq.getSifGrad()).get());
		novaPoslovnica.setPoslovniPartner(partnerService.findBySif(poslovnicaReq.getSifPartner()).get());
	
		try {
			poslovnicaService.createPoslovnica(novaPoslovnica);
		} catch (Exception pSqlExc) {
			logger.error(pSqlExc.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(novaPoslovnica);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deletePoslovnica(@PathVariable Integer id) {
		if (poslovnicaService.findBySif(id).isEmpty()) {
			logger.error("poslovnica with id: " + id + " cant be deleted because it does not exist");
			return ResponseEntity.badRequest().build();
		}
		poslovnicaService.deletePoslovnica(id);
		return ResponseEntity.ok("poslovnica with id: " + id + " uspješno izbrisan");
	}
}
