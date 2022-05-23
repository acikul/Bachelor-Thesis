package www.fer.hr.zavrsni.controllers;

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

import www.fer.hr.zavrsni.domain.PoslovniPartner;
import www.fer.hr.zavrsni.domain.Proizvod;
import www.fer.hr.zavrsni.domain.Rabat;
import www.fer.hr.zavrsni.domain.keys.RabatKey;
import www.fer.hr.zavrsni.service.PoslovniPartnerService;
import www.fer.hr.zavrsni.service.ProizvodService;
import www.fer.hr.zavrsni.service.RabatService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rabati")
public class RabatController {

	private static final Logger logger = LoggerFactory.getLogger(RabatController.class);

	@Autowired
	private RabatService rabatService;
	
	@Autowired
	private PoslovniPartnerService partnerService;
	
	@Autowired
	private ProizvodService proizvodService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Rabat>> getRabats() {
		
		// only for testing - generates random iznosRabat between 0 and 50
		// for all proizvods x partners
		for (PoslovniPartner partner : partnerService.listAll()) {
			for (Proizvod proizvod : proizvodService.listAll()) {
				RabatKey kompozitSif = new RabatKey();
				kompozitSif.setSifPartner(partner.getSifPartner());
				kompozitSif.setSifProizvod(proizvod.getSifProizvod());
				
				if (rabatService.findByKompozitSif(kompozitSif).isEmpty()) {
					Rabat noviRabat = new Rabat();
					noviRabat.setKompozitSif(kompozitSif);
					noviRabat.setPartner(partner);
					noviRabat.setProizvod(proizvod);
					noviRabat.setIznosRabat((int)(Math.random()*26));
					try {
						rabatService.createRabat(noviRabat);
					} catch (Exception e) {
						logger.error(e.getMessage());
						return ResponseEntity.badRequest().build();
					}
				}
			}
		}
		
		return ResponseEntity.ok(rabatService.listAll());
	}
	
	@GetMapping("/proizvod/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Rabat>> getRabatsOdProizvod(@PathVariable Integer id) {
		return ResponseEntity.ok(rabatService.listByProizvodSif(id));
	}
	
	@GetMapping("/partner/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<List<Rabat>> getRabatsOdPartner(@PathVariable Integer id) {
		// only for testing - generates random iznosRabat between 0 and 50
				// for all proizvods x partners
				for (PoslovniPartner partner : partnerService.listAll()) {
					for (Proizvod proizvod : proizvodService.listAll()) {
						RabatKey kompozitSif = new RabatKey();
						kompozitSif.setSifPartner(partner.getSifPartner());
						kompozitSif.setSifProizvod(proizvod.getSifProizvod());
						
						if (rabatService.findByKompozitSif(kompozitSif).isEmpty()) {
							Rabat noviRabat = new Rabat();
							noviRabat.setKompozitSif(kompozitSif);
							noviRabat.setPartner(partner);
							noviRabat.setProizvod(proizvod);
							noviRabat.setIznosRabat((int)(Math.random()*26));
							try {
								rabatService.createRabat(noviRabat);
							} catch (Exception e) {
								logger.error(e.getMessage());
								return ResponseEntity.badRequest().build();
							}
						}
					}
				}
		return ResponseEntity.ok(rabatService.listByPartnerSif(id));
	}
	
	
}















