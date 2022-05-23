package www.fer.hr.zavrsni.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import www.fer.hr.zavrsni.domain.RadnoMjesto;
import www.fer.hr.zavrsni.domain.Zaposlenik;
import www.fer.hr.zavrsni.dto.request.ZaposlenikRequest;
import www.fer.hr.zavrsni.dto.response.MessageResponse;
import www.fer.hr.zavrsni.security.services.UserDetailsImpl;
import www.fer.hr.zavrsni.service.GradService;
import www.fer.hr.zavrsni.service.RadnoMjestoService;
import www.fer.hr.zavrsni.service.ZaposlenikService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/zaposlenik")
public class ZaposlenikController {
	
	private static final Logger logger = LoggerFactory.getLogger(ZaposlenikController.class);
	
	@Autowired
	private ZaposlenikService zaposlenikService;
	
	@Autowired
	private RadnoMjestoService radnoMjestoService;
	
	@Autowired
	private GradService gradService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR')")
	public ResponseEntity<List<Zaposlenik>> getZaposleniks() {
		return ResponseEntity.ok(zaposlenikService.listAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or (#id == principal.id)")
	public ResponseEntity<Zaposlenik> getZaposlenik(@PathVariable Integer id) {
		Optional<Zaposlenik> zaposlenik = zaposlenikService.findBySifZaposlenik(id);
		
		if (zaposlenik.isEmpty()) {
			logger.error("User id:" + id + " does not exist");
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(zaposlenik.get());
	}
	
	@GetMapping("/me")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<?> getMeUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
//		HashMap<String, Object> ret = new HashMap<>();
//		ret.put("user_info", zaposlenikService.findBySifZaposlenik(userDetails.getId()));
//		ret.put("roles", roles);
		return ResponseEntity.ok(zaposlenikService.findBySifZaposlenik(userDetails.getId()));
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_DIREKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addZaposlenik(@Valid @RequestBody ZaposlenikRequest zapRequest) {
		Zaposlenik zaposlenik = new Zaposlenik();
		
		RadnoMjesto radnoMj = radnoMjestoService.findBySifRadnoMjesto(zapRequest.getSifRadnoMjesto()).get();
		Grad gradZap = gradService.findBySifGrad(zapRequest.getSifGrad()).get();
		
		zaposlenik.setOibZaposlenik(zapRequest.getOib());
		zaposlenik.setImeZaposlenik(zapRequest.getIme());
		zaposlenik.setPrezimeZaposlenik(zapRequest.getPrezime());
		zaposlenik.setEmailZaposlenik(zapRequest.getEmail());
		zaposlenik.setBrojTelZaposlenik(zapRequest.getBrojTel());
		zaposlenik.setDatumRodenja(zapRequest.getDatumRod());
		zaposlenik.setDatumPocetka(zapRequest.getDatumPoc());
		zaposlenik.setDatumKraj(zapRequest.getDatumKraj());
		zaposlenik.setPasswordHash(passwordEncoder.encode(zapRequest.getPassword()));
//		zaposlenik.setSifRadnoMjesto(zapRequest.getSifRadnoMjesto());
		zaposlenik.setRadnoMj(radnoMj);
		zaposlenik.setGrad(gradZap);
		zaposlenik.setAdresaZaposlenik(zapRequest.getAdresa());
		
		try {
			zaposlenikService.createZaposlenik(zaposlenik);
		} catch (Exception psqlex) {
			logger.error(psqlex.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(new MessageResponse("Zaposlenik kreiran"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or (#id == principal.id)")
	public ResponseEntity<?> updateZaposlenik(@PathVariable Integer id, @Valid @RequestBody ZaposlenikRequest zapReq) {
		if (zaposlenikService.findBySifZaposlenik(id).isEmpty()) {
			logger.error("Zaposlenik with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		Zaposlenik zaposlenik = zaposlenikService.findBySifZaposlenik(id).get();
		Zaposlenik updatedZaposlenik = zaposlenik;
		
		if (zapReq.getOib() != null) updatedZaposlenik.setOibZaposlenik(zapReq.getOib()) ;
		if (zapReq.getIme() != null) updatedZaposlenik.setImeZaposlenik(zapReq.getIme());
		if (zapReq.getPrezime() != null) updatedZaposlenik.setPrezimeZaposlenik(zapReq.getPrezime());
		if (zapReq.getEmail() != null) updatedZaposlenik.setEmailZaposlenik(zapReq.getEmail());
		if (zapReq.getBrojTel() != null) updatedZaposlenik.setBrojTelZaposlenik(zapReq.getBrojTel());
		if (zapReq.getDatumRod() != null) updatedZaposlenik.setDatumRodenja(zapReq.getDatumRod());
		if (zapReq.getDatumPoc() != null) updatedZaposlenik.setDatumPocetka(zapReq.getDatumPoc());
		//if (zapReq.getDatumKraj() != null)
		updatedZaposlenik.setDatumKraj(zapReq.getDatumKraj());
		if (zapReq.getPassword() != null) updatedZaposlenik.setPasswordHash(passwordEncoder.encode(zapReq.getPassword()));
		if (zapReq.getSifRadnoMjesto() != zaposlenik.getRadnoMj().getSifRadnoMjesto()) updatedZaposlenik.setRadnoMj(radnoMjestoService.findBySifRadnoMjesto(zapReq.getSifRadnoMjesto()).get());
		if (zapReq.getSifGrad() != zaposlenik.getGrad().getSifGrad()) updatedZaposlenik.setGrad(gradService.findBySifGrad(zapReq.getSifGrad()).get());
		if (zapReq.getAdresa() != null) updatedZaposlenik.setAdresaZaposlenik(zapReq.getAdresa());
		
		zaposlenikService.createZaposlenik(updatedZaposlenik);
		
		logger.trace("izmjenjen zaposlenik: " + updatedZaposlenik.getEmailZaposlenik());
		return ResponseEntity.ok("Uspjesna izmjena");
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR')")
	public ResponseEntity<?> deleteZaposlenik (@PathVariable Integer id) {
		if (zaposlenikService.findBySifZaposlenik(id).isEmpty()){
			logger.error("Zaposlenik with id: " + id + "does not exist.");
			return ResponseEntity.badRequest().build();
		}
		zaposlenikService.deleteZaposlenik(id);
		return ResponseEntity.ok("Zaposlenik uspješno izbrisan");
	}
}




















