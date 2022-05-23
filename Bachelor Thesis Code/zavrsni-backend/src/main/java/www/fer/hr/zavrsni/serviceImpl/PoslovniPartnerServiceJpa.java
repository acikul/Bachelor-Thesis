package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.PoslovniPartnerRepository;
import www.fer.hr.zavrsni.domain.PoslovniPartner;
import www.fer.hr.zavrsni.service.PoslovniPartnerService;

@Service
public class PoslovniPartnerServiceJpa implements PoslovniPartnerService {

	@Autowired
	PoslovniPartnerRepository poslovniPartnerRepo;
	
	@Override
	public List<PoslovniPartner> listAll() {
		return poslovniPartnerRepo.findAll();
	}

	@Override
	public Optional<PoslovniPartner> findByNaziv(String naziv) {
		return poslovniPartnerRepo.findByNazivPartner(naziv);
	}

	@Override
	public Optional<PoslovniPartner> findBySif(Integer sif) {
		return poslovniPartnerRepo.findById(sif);
	}

	@Override
	public PoslovniPartner createPoslovniPartner(PoslovniPartner poslovniPartner) {
		return poslovniPartnerRepo.save(poslovniPartner);
	}

	@Override
	public void deletePoslovniPartner(Integer sif) {
		poslovniPartnerRepo.deleteById(sif);
		return;
	}

}
