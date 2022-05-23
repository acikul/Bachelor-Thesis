package www.fer.hr.zavrsni.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.RacunRepository;
import www.fer.hr.zavrsni.domain.Racun;
import www.fer.hr.zavrsni.service.RacunService;

@Service
public class RacunServiceJpa implements RacunService {
	
	@Autowired
	RacunRepository racunRepo;

	@Override
	public List<Racun> listAll() {
		return racunRepo.findAll();
	}

	@Override
	public Optional<Racun> findBySifRacun(Integer sif) {
		return racunRepo.findById(sif);
	}

	@Override
	public Racun createRacun(Racun racun) {
		return racunRepo.save(racun);
	}

	@Override
	public void deleteRacun(Integer sif) {
		racunRepo.deleteById(sif);
		return;
	}

	@Override
	public List<Racun> listBySifPartner(Integer sif) {
		List<Racun> sviRacuni = racunRepo.findAll();
		List<Racun> racuniPartnera = new LinkedList<>();
		
		for (Racun r : sviRacuni) {
			if (r.getPoslovnica().getPoslovniPartner().getSifPartner() == sif) {
				racuniPartnera.add(r);
			}
		}
		
		return racuniPartnera;
	}

}
