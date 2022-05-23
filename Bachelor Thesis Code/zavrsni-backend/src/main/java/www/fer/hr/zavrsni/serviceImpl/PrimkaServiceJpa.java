package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.PrimkaRepository;
import www.fer.hr.zavrsni.domain.Primka;
import www.fer.hr.zavrsni.service.PrimkaService;

@Service
public class PrimkaServiceJpa implements PrimkaService{

	@Autowired
	PrimkaRepository primkaRepo;

	@Override
	public List<Primka> listAll() {
		return primkaRepo.findAll();
	}

	@Override
	public Optional<Primka> findBySifPrimka(Integer sif) {
		return primkaRepo.findById(sif);
	}

	@Override
	public Primka createPrimka(Primka primka) {
		return primkaRepo.save(primka);
	}

	@Override
	public void deletePrimka(Integer sif) {
		primkaRepo.deleteById(sif);
		return;
	}
	
	
	
}
