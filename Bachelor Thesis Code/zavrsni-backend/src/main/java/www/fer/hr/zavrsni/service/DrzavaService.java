package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Drzava;

public interface DrzavaService {
	List<Drzava> listAll();
	Optional<Drzava> findByNaziv(String naziv);
	Optional<Drzava> findBySifDrzava(Integer sif);
	
	Drzava createDrzava(Drzava drzava);
	void deleteDrzava(Integer sif);
}
