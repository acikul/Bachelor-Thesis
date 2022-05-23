package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Grad;

public interface GradService {
	List<Grad> listAll();
	Optional<Grad> findByNaziv(String naziv);
	Optional<Grad> findBySifGrad(Integer sif);
	
	Grad createGrad(Grad grad);
	void deleteGrad(Integer sif);
}
