package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.RadnoMjestoRepository;
import www.fer.hr.zavrsni.domain.RadnoMjesto;
import www.fer.hr.zavrsni.service.RadnoMjestoService;

@Service
public class RadnoMjestoServiceJpa implements RadnoMjestoService {

	@Autowired
	RadnoMjestoRepository radnoMjestoRepo;
	
	@Override
	public List<RadnoMjesto> listAll() {
		return radnoMjestoRepo.findAll();
	}

	@Override
	public Optional<RadnoMjesto> findByNaziv(String naziv) {
		return radnoMjestoRepo.findByNazivRadnoMjesto(naziv);
	}
	
	@Override
	public Optional<RadnoMjesto> findBySifRadnoMjesto(Integer sif) {
		return radnoMjestoRepo.findById(sif);
	}

	@Override
	public RadnoMjesto createRadnoMjesto(RadnoMjesto radnoMjesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRadnoMjesto(Integer sif) {
		// TODO Auto-generated method stub
		
	}

}
