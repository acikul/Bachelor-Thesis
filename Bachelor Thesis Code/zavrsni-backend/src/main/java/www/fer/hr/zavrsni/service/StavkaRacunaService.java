package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.StavkaRacuna;
import www.fer.hr.zavrsni.domain.keys.StavkaRacunaKey;

public interface StavkaRacunaService {
	List<StavkaRacuna> listAll();
	List<StavkaRacuna> listByRacunSif(Integer sif);
	Optional<StavkaRacuna> findByKompozitSif(StavkaRacunaKey kompozitSif);
	
	StavkaRacuna createStavkaRacuna(StavkaRacuna stavka);
	void deleteStavkaRacuna(StavkaRacunaKey kompozitSif);

}
