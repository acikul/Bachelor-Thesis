package www.fer.hr.zavrsni.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.StavkaRacunaRepository;
import www.fer.hr.zavrsni.domain.StavkaRacuna;
import www.fer.hr.zavrsni.domain.keys.StavkaRacunaKey;
import www.fer.hr.zavrsni.service.StavkaRacunaService;

@Service
public class StavkaRacunaServiceJpa implements StavkaRacunaService {

	@Autowired
	StavkaRacunaRepository stavkaRacunaRepo;
	
	@Override
	public List<StavkaRacuna> listAll() {
		return stavkaRacunaRepo.findAll();
	}

	@Override
	public StavkaRacuna createStavkaRacuna(StavkaRacuna stavka) {
		return stavkaRacunaRepo.save(stavka);
	}

	@Override
	public Optional<StavkaRacuna> findByKompozitSif(StavkaRacunaKey kompozitSif) {
		return stavkaRacunaRepo.findById(kompozitSif);
	}

	@Override
	public void deleteStavkaRacuna(StavkaRacunaKey kompozitSif) {
		stavkaRacunaRepo.deleteById(kompozitSif);
		return;
	}

	@Override
	public List<StavkaRacuna> listByRacunSif(Integer sif) {
		List<StavkaRacuna> listaSvihStavki = stavkaRacunaRepo.findAll();
		List<StavkaRacuna> stavkeRacuna = new LinkedList<>();
		
		for (StavkaRacuna stavka : listaSvihStavki) {
			if (stavka.getKompozitSif().getSifRacun() == sif) {
				stavkeRacuna.add(stavka);
			}
		}
		
		return stavkeRacuna;
	}

}
