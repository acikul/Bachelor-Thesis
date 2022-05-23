package www.fer.hr.zavrsni.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.RabatRepository;
import www.fer.hr.zavrsni.domain.Rabat;
import www.fer.hr.zavrsni.domain.keys.RabatKey;
import www.fer.hr.zavrsni.service.RabatService;

@Service
public class RabatServiceJpa implements RabatService {

	@Autowired
	RabatRepository rabatRepo;

	@Override
	public List<Rabat> listAll() {
		return rabatRepo.findAll();
	}

	@Override
	public List<Rabat> listByProizvodSif(Integer sif) {
		List<Rabat> listaSvihRabata = rabatRepo.findAll();
		List<Rabat> rabatiTogProizvoda = new LinkedList<>();
		
		for (Rabat rabat : listaSvihRabata) {
			if (rabat.getKompozitSif().getSifProizvod() == sif) {
				rabatiTogProizvoda.add(rabat);
			}
		}
		return rabatiTogProizvoda;
	}

	@Override
	public List<Rabat> listByPartnerSif(Integer sif) {
		List<Rabat> listaSvihRabata = rabatRepo.findAll();
		List<Rabat> rabatiTogPartnera = new LinkedList<>();
		
		for (Rabat rabat : listaSvihRabata) {
			if (rabat.getKompozitSif().getSifPartner() == sif) {
				rabatiTogPartnera.add(rabat);
			}
		}
		return rabatiTogPartnera;
	}

	@Override
	public Optional<Rabat> findByKompozitSif(RabatKey kompozitSif) {
		return rabatRepo.findById(kompozitSif);
	}

	@Override
	public Rabat createRabat(Rabat rabat) {
		return rabatRepo.save(rabat);
	}

	@Override
	public void deleteRabat(RabatKey kompozitSif) {
		rabatRepo.deleteById(kompozitSif);
		return;
	}
	
	
}
