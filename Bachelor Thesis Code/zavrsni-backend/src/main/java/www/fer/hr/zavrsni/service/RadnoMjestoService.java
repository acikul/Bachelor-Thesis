package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.RadnoMjesto;

public interface RadnoMjestoService {
	List<RadnoMjesto> listAll();
	Optional<RadnoMjesto> findByNaziv(String naziv);
	Optional<RadnoMjesto> findBySifRadnoMjesto(Integer sif);
	
	RadnoMjesto createRadnoMjesto(RadnoMjesto radnoMjesto);
	void deleteRadnoMjesto(Integer sif);
}
