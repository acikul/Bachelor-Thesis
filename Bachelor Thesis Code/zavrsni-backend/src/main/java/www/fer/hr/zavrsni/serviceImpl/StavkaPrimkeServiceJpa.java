package www.fer.hr.zavrsni.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.StavkaPrimkeRepository;
import www.fer.hr.zavrsni.domain.StavkaPrimke;
import www.fer.hr.zavrsni.domain.keys.StavkaPrimkeKey;
import www.fer.hr.zavrsni.service.StavkaPrimkeService;

@Service
public class StavkaPrimkeServiceJpa implements StavkaPrimkeService {

	@Autowired
	StavkaPrimkeRepository stavkaPrimkeRepo;
	
	@Override
	public List<StavkaPrimke> listAll() {
		return stavkaPrimkeRepo.findAll();
	}

	@Override
	public List<StavkaPrimke> listByPrimkaSif(Integer sif) {
		List<StavkaPrimke> listaSvihStavki = stavkaPrimkeRepo.findAll();
		List<StavkaPrimke> stavkePrimke = new LinkedList<>();
		
		for (StavkaPrimke stavka : listaSvihStavki) {
			if (stavka.getKompozitSif().getSifPrimka() == sif) {
				stavkePrimke.add(stavka);
			}
		}
		return stavkePrimke;
	}

	@Override
	public Optional<StavkaPrimke> findByKompozitSif(StavkaPrimkeKey kompozitSif) {
		return stavkaPrimkeRepo.findById(kompozitSif);
	}

	@Override
	public StavkaPrimke createStavkaPrimke(StavkaPrimke stavka) {
		return stavkaPrimkeRepo.save(stavka);
	}

	@Override
	public void deleteStavkaPrimke(StavkaPrimkeKey kompozitSif) {
		stavkaPrimkeRepo.deleteById(kompozitSif);
		return;
	}

}
