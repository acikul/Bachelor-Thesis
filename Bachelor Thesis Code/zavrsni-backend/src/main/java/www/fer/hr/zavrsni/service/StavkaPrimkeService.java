package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.StavkaPrimke;
import www.fer.hr.zavrsni.domain.keys.StavkaPrimkeKey;

public interface StavkaPrimkeService {
	List<StavkaPrimke> listAll();
	List<StavkaPrimke> listByPrimkaSif(Integer sif);
	Optional<StavkaPrimke> findByKompozitSif(StavkaPrimkeKey kompozitSif);
	
	StavkaPrimke createStavkaPrimke(StavkaPrimke stavka);
	void deleteStavkaPrimke(StavkaPrimkeKey kompozitSif);
}
