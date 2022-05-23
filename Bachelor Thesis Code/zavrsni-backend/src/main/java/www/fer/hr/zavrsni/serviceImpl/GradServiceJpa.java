package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.GradRepository;
import www.fer.hr.zavrsni.domain.Grad;
import www.fer.hr.zavrsni.service.GradService;

@Service
public class GradServiceJpa implements GradService {
	
	@Autowired
	GradRepository gradRepo;

	@Override
	public List<Grad> listAll() {
		return gradRepo.findAll();
	}

	@Override
	public Optional<Grad> findByNaziv(String naziv) {
		return gradRepo.findByNazivGrad(naziv);
	}

	@Override
	public Optional<Grad> findBySifGrad(Integer sif) {
		return gradRepo.findById(sif);
	}

	@Override
	public Grad createGrad(Grad grad) {
		return gradRepo.save(grad);
	}

	@Override
	public void deleteGrad(Integer sif) {
		gradRepo.deleteById(sif);
		return;
	}

}
