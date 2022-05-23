package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.Zaposlenik;

public interface ZaposlenikService {
	List<Zaposlenik>  listAll();
	Optional<Zaposlenik> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<Zaposlenik> findBySifZaposlenik(Integer sif);
	
	Zaposlenik createZaposlenik(Zaposlenik zaposlenik);
	void deleteZaposlenik(Integer sif);
}
