package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.ProizvodacRepository;
import www.fer.hr.zavrsni.domain.Proizvodac;
import www.fer.hr.zavrsni.service.ProizvodacService;

@Service
public class ProizvodacServiceJpa implements ProizvodacService{
	
	@Autowired
	ProizvodacRepository proizvodacRepo;
	
	@Override
	public List<Proizvodac> listAllProizvodacs() {
		return proizvodacRepo.findAll();
	}

	@Override
	public Optional<Proizvodac> findByNaziv(String naziv) {
		return proizvodacRepo.findByNazivProizvodac(naziv);
	}

	@Override
	public Optional<Proizvodac> findBySifProizvodac(Integer sif) {
		return proizvodacRepo.findById(sif);
	}

	@Override
	public Proizvodac createProizvodac(Proizvodac proizvodac) {
		return proizvodacRepo.save(proizvodac);
	}

	@Override
	public void deleteProizvodac(Integer sif) {
		proizvodacRepo.deleteById(sif);
		return;
	}

}
