package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Primka;

public interface PrimkaService {
	List<Primka> listAll();
	Optional<Primka> findBySifPrimka(Integer sif);
	
	Primka createPrimka(Primka primka);
	void deletePrimka(Integer sif);

}
