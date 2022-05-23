package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.SkladisteRepository;
import www.fer.hr.zavrsni.service.SkladisteService;
import www.fer.hr.zavrsni.domain.Skladiste;

@Service
public class SkladisteServiceJpa implements SkladisteService {
	
	@Autowired
	SkladisteRepository skladisteRepo;

	@Override
	public List<Skladiste> listAll() {
		return skladisteRepo.findAll();
	}

	@Override
	public Optional<Skladiste> findByNaziv(String naziv) {
		return skladisteRepo.findByNazivSkladiste(naziv);
	}

	@Override
	public Optional<Skladiste> findBySif(Integer sif) {
		return skladisteRepo.findById(sif);
	}

	@Override
	public Skladiste createSkladiste(Skladiste skladiste) {
		return skladisteRepo.save(skladiste);
	}

	@Override
	public void deleteSkladiste(Integer sif) {
		skladisteRepo.deleteById(sif);
		return;
	}

}
