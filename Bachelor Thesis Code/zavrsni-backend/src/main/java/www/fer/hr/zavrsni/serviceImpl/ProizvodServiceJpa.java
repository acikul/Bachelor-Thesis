package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.ProizvodRepository;
import www.fer.hr.zavrsni.domain.Proizvod;
import www.fer.hr.zavrsni.service.ProizvodService;

@Service
public class ProizvodServiceJpa implements ProizvodService {
	
	@Autowired
	ProizvodRepository proizvodRepo;

	@Override
	public List<Proizvod> listAll() {
		return proizvodRepo.findAll();
	}

	@Override
	public Optional<Proizvod> findByNaziv(String naziv) {
		return proizvodRepo.findByNazivProizvod(naziv);
	}

	@Override
	public Boolean existsByNaziv(String naziv) {
		return proizvodRepo.existsByNazivProizvod(naziv);
	}

	@Override
	public Optional<Proizvod> findBySifProizvod(Integer sif) {
		return proizvodRepo.findById(sif);
	}

	@Override
	public Proizvod createProizvod(Proizvod proizvod) {
		return proizvodRepo.save(proizvod);
	}

	@Override
	public void deleteProizvod(Integer sif) {
		proizvodRepo.deleteById(sif);
		return;
	}

}
