package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Racun;

public interface RacunService {
	List<Racun> listAll();
	Optional<Racun> findBySifRacun(Integer sif);
	
	List<Racun> listBySifPartner(Integer sif);
	
	Racun createRacun(Racun racun);
	void deleteRacun(Integer sif);

}
