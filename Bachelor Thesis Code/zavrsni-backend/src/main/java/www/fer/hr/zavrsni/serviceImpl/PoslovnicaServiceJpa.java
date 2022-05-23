package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.PoslovnicaRepository;
import www.fer.hr.zavrsni.domain.Poslovnica;
import www.fer.hr.zavrsni.service.PoslovnicaService;


@Service
public class PoslovnicaServiceJpa implements PoslovnicaService {

	@Autowired
	PoslovnicaRepository poslovnicaRepo;
	
	@Override
	public List<Poslovnica> listAll() {
		return poslovnicaRepo.findAll();
	}

	@Override
	public Optional<Poslovnica> findByNaziv(String naziv) {
		return poslovnicaRepo.findByNazivPoslovnica(naziv);
	}

	@Override
	public Optional<Poslovnica> findBySif(Integer sif) {
		return poslovnicaRepo.findById(sif);
	}

	@Override
	public Poslovnica createPoslovnica(Poslovnica poslovnica) {
		return poslovnicaRepo.save(poslovnica);
	}

	@Override
	public void deletePoslovnica(Integer sif) {
		poslovnicaRepo.deleteById(sif);
		return;
	}

}
