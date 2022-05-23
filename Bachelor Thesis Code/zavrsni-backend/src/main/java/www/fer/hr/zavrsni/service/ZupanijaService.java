package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Zupanija;

public interface ZupanijaService {
	List<Zupanija> listAll();
	Optional<Zupanija> findByNaziv(String naziv);
	Optional<Zupanija> findBySifZupanija(Integer sif);
	
	Zupanija createZupanija(Zupanija zupanija);
	void deleteZupanija(Integer sif);

}
