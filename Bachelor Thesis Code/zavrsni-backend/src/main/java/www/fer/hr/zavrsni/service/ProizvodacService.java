package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Proizvodac;

public interface ProizvodacService {
	List<Proizvodac> listAllProizvodacs();
	Optional<Proizvodac> findByNaziv(String naziv);
	Optional<Proizvodac> findBySifProizvodac(Integer sif);
	
	Proizvodac createProizvodac(Proizvodac proizvodac);
	void deleteProizvodac(Integer sif);
}
