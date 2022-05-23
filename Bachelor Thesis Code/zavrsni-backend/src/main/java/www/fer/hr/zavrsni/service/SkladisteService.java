package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Skladiste;

public interface SkladisteService {
	List<Skladiste> listAll();
	Optional<Skladiste> findByNaziv(String naziv);
	Optional<Skladiste> findBySif(Integer sif);
	
	Skladiste createSkladiste(Skladiste skladiste);
	void deleteSkladiste(Integer sif);
}
