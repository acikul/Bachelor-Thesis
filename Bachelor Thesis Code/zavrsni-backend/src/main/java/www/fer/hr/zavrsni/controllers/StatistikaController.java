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

import www.fer.hr.zavrsni.domain.Proizvod;
import www.fer.hr.zavrsni.domain.Racun;
import www.fer.hr.zavrsni.domain.StavkaRacuna;
import www.fer.hr.zavrsni.dto.response.MjesecPromet;
import www.fer.hr.zavrsni.dto.response.StatistikaResponse;
import www.fer.hr.zavrsni.service.ProizvodService;
import www.fer.hr.zavrsni.service.RacunService;
import www.fer.hr.zavrsni.service.StavkaRacunaService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/statistika")
public class StatistikaController {

	private static final Logger logger = LoggerFactory.getLogger(StatistikaController.class);

	@Autowired
	private RacunService racunService;
	@Autowired
	private ProizvodService proizvodService;
	@Autowired
	private StavkaRacunaService stavkaRacService;
	
	@SuppressWarnings("deprecation")
	@GetMapping("/partner/{id}/{godina}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<StatistikaResponse> getPrometiPoMjOdPartner(@PathVariable Integer id, @PathVariable Integer godina) {
		StatistikaResponse response = new StatistikaResponse();
		Double ukupno = 0.0;
		
		for (int i=0; i<12; i++) {
			List<Racun> racuniPartnera = racunService.listBySifPartner(id);
			
			MjesecPromet mjesecPromet = new MjesecPromet();
			
			String mjesec = null;
			switch((i+1)){
            case 1:
                mjesec = "siječanj";
                break;
            case 2:
                mjesec = "veljača";
                break;
            case 3:
                mjesec = "ožujak";
                break;
            case 4:
                mjesec = "travanj";
                break;
            case 5:
                mjesec = "svibanj";
                break;
            case 6:
                mjesec = "lipanj";
                break;
            case 7:
                mjesec = "srpanj";
                break;
            case 8:
                mjesec = "kolovoz";
                break;
            case 9:
                mjesec = "rujan";
                break;
            case 10:
                mjesec = "listopad";
                break;
            case 11:
                mjesec = "studeni";
                break;
            case 12:
                mjesec = "prosinac";
                break;
            default:
                mjesec = "krivi mjesec";
                break;
			}
			
			mjesecPromet.setMjesec(mjesec);
			mjesecPromet.setPromet(0.0);
			
			for (Racun r : racuniPartnera) {
				if (r.getDatumRacun().getMonth() == i && r.getDatumRacun().getYear() == (godina-1900)) {
					mjesecPromet.setPromet(mjesecPromet.getPromet() + r.getUkupnaCijena());
				}
			}
			
			ukupno += mjesecPromet.getPromet();
			
			
			response.addMjeseciPrometi(mjesecPromet);
		}
		response.setUkupno(ukupno);
		
		return ResponseEntity.ok(response);
	}
	
	
	@SuppressWarnings("deprecation")
	@GetMapping("/proizvod/{id}/{godina}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<StatistikaResponse> getPrometiPoMjOdProizvod(@PathVariable Integer id, @PathVariable Integer godina) {
		StatistikaResponse response = new StatistikaResponse();
		Double ukupno = 0.0;
		
		for (int i=0; i<12; i++) {
			List<Racun> sviRacuni = racunService.listAll();
			
			MjesecPromet mjesecPromet = new MjesecPromet();
			
			String mjesec = null;
			switch((i+1)){
            case 1:
                mjesec = "siječanj";
                break;
            case 2:
                mjesec = "veljača";
                break;
            case 3:
                mjesec = "ožujak";
                break;
            case 4:
                mjesec = "travanj";
                break;
            case 5:
                mjesec = "svibanj";
                break;
            case 6:
                mjesec = "lipanj";
                break;
            case 7:
                mjesec = "srpanj";
                break;
            case 8:
                mjesec = "kolovoz";
                break;
            case 9:
                mjesec = "rujan";
                break;
            case 10:
                mjesec = "listopad";
                break;
            case 11:
                mjesec = "studeni";
                break;
            case 12:
                mjesec = "prosinac";
                break;
            default:
                mjesec = "krivi mjesec";
                break;
			}
			
			mjesecPromet.setMjesec(mjesec);
			mjesecPromet.setPromet(0.0);
			
			for (Racun r : sviRacuni) {
				if (r.getDatumRacun().getMonth() == i && r.getDatumRacun().getYear() == (godina-1900)) {
					for (StavkaRacuna stavka : r.getStavke()) {
						if (stavka.getProizvod().getSifProizvod() == id) {
							mjesecPromet.setPromet(mjesecPromet.getPromet() + stavka.getKolicinaProizvodRacun()*stavka.getProizvod().getCijenaProizvod());
	
						}
					}
				}
			}
			
			ukupno += mjesecPromet.getPromet();
			
			response.addMjeseciPrometi(mjesecPromet);
		}
		response.setUkupno(ukupno);
		
		return ResponseEntity.ok(response);
	}
	
	
	@SuppressWarnings("deprecation")
	@GetMapping("/sviProizvodi/{godina}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIREKTOR') or hasRole('ROLE_SKLADISTAR')")
	public ResponseEntity<StatistikaResponse> getPrometiSvihProizvoda(@PathVariable Integer godina) {
		StatistikaResponse response = new StatistikaResponse();
		Double ukupno = 0.0;
		
		for (Proizvod p : proizvodService.listAll()) {
			List<Racun> sviRacuni = racunService.listAll();
			
			MjesecPromet mjesecPromet = new MjesecPromet();
			
			mjesecPromet.setMjesec(p.getNazivProizvod());
			mjesecPromet.setPromet(0.0);
			
			for (Racun r : sviRacuni) {
				if (r.getDatumRacun().getYear() == (godina-1900)) {
					for (StavkaRacuna stavka : r.getStavke()) {
						if (stavka.getProizvod().getSifProizvod() == p.getSifProizvod()) {
							mjesecPromet.setPromet(mjesecPromet.getPromet() + stavka.getKolicinaProizvodRacun()*stavka.getProizvod().getCijenaProizvod());
	
						}
					}
				}
			}
			
			ukupno += mjesecPromet.getPromet();
			
			response.addMjeseciPrometi(mjesecPromet);
		}
		response.setUkupno(ukupno);
		
		return ResponseEntity.ok(response);
	}
}
