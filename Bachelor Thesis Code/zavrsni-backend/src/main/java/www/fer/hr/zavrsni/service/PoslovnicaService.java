package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Poslovnica;

public interface PoslovnicaService {
	List<Poslovnica> listAll();
	Optional<Poslovnica> findByNaziv(String naziv);
	Optional<Poslovnica> findBySif(Integer sif);
	
	Poslovnica createPoslovnica(Poslovnica skladiste);
	void deletePoslovnica(Integer sif);
}
