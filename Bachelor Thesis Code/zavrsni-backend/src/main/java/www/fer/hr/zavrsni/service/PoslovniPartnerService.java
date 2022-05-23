package www.fer.hr.zavrsni.service;

import java.util.List;
import java.util.Optional;

import www.fer.hr.zavrsni.domain.PoslovniPartner;

public interface PoslovniPartnerService {
	List<PoslovniPartner> listAll();
	Optional<PoslovniPartner> findByNaziv(String naziv);
	Optional<PoslovniPartner> findBySif(Integer sif);
	
	PoslovniPartner createPoslovniPartner(PoslovniPartner poslovniPartner);
	void deletePoslovniPartner(Integer sif);
}
