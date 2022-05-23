package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.ZupanijaRepository;
import www.fer.hr.zavrsni.domain.Zupanija;
import www.fer.hr.zavrsni.service.ZupanijaService;

@Service
public class ZupanijaServiceJpa implements ZupanijaService {
	
	@Autowired
	ZupanijaRepository zupanijaRepo;

	@Override
	public List<Zupanija> listAll() {
		return zupanijaRepo.findAll();
	}

	@Override
	public Optional<Zupanija> findByNaziv(String naziv) {
		return zupanijaRepo.findByNazivZupanija(naziv);
	}

	@Override
	public Optional<Zupanija> findBySifZupanija(Integer sif) {
		return zupanijaRepo.findById(sif);
	}

	@Override
	public Zupanija createZupanija(Zupanija zupanija) {
		return zupanijaRepo.save(zupanija);
	}

	@Override
	public void deleteZupanija(Integer sif) {
		zupanijaRepo.deleteById(sif);
		return;
	}

}
