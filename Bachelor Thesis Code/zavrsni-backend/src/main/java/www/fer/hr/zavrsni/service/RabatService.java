package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Rabat;
import www.fer.hr.zavrsni.domain.keys.RabatKey;

public interface RabatService {
	List<Rabat> listAll();
	List<Rabat> listByProizvodSif(Integer sif);
	List<Rabat> listByPartnerSif(Integer sif);
	Optional<Rabat> findByKompozitSif(RabatKey kompozitSif);
	
	Rabat createRabat(Rabat rabat);
	void deleteRabat(RabatKey kompozitSif);
}
