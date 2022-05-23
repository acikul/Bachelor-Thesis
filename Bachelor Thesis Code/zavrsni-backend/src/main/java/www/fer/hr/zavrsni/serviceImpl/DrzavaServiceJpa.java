package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import www.fer.hr.zavrsni.dao.DrzavaRepository;
import www.fer.hr.zavrsni.domain.Drzava;
import www.fer.hr.zavrsni.service.DrzavaService;

@Service
public class DrzavaServiceJpa implements DrzavaService {

	@Autowired
	DrzavaRepository drzavaRepo;
	
	@Override
	public List<Drzava> listAll() {
		return drzavaRepo.findAll();
	}

	@Override
	public Optional<Drzava> findByNaziv(String naziv) {
		Assert.notNull(naziv, "naziv mora biti zadan");
		return drzavaRepo.findByNazivDrzava(naziv);
	}
	@Override
	public Optional<Drzava> findBySifDrzava(Integer sif) {
		return drzavaRepo.findById(sif);
	}

	@Override
	public Drzava createDrzava(Drzava drzava) {
		return drzavaRepo.save(drzava);
	}

	@Override
	public void deleteDrzava(Integer sif) {
		drzavaRepo.deleteById(sif);
		return;
	}
}
