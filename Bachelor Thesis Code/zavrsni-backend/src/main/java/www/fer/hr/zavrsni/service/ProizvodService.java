package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Proizvod;

public interface ProizvodService {
	List<Proizvod> listAll();
	Optional<Proizvod> findByNaziv(String naziv);
	Boolean existsByNaziv(String naziv);
	Optional<Proizvod> findBySifProizvod(Integer sif);
	
	Proizvod createProizvod(Proizvod proizvod);
	void deleteProizvod(Integer sif);

}
