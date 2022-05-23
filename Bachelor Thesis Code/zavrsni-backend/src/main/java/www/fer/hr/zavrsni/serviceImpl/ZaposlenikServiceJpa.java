package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;

import www.fer.hr.zavrsni.dao.ZaposlenikRepository;
import www.fer.hr.zavrsni.domain.Zaposlenik;
import www.fer.hr.zavrsni.service.ZaposlenikService;

@Service
public class ZaposlenikServiceJpa implements ZaposlenikService {

	@Autowired
	ZaposlenikRepository zaposlenikRepo;
	
	@Override
	public List<Zaposlenik> listAll() {
		return zaposlenikRepo.findAll();
	}

	@Override
	public Optional<Zaposlenik> findByEmail(String email) {
//		Assert.notNull(email, "email must be specified");
		return zaposlenikRepo.findByEmailZaposlenik(email);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return zaposlenikRepo.existsByEmailZaposlenik(email);
	}

	@Override
	public Optional<Zaposlenik> findBySifZaposlenik(Integer sif) {
		return zaposlenikRepo.findById(sif);
	}

	@Override
	public Zaposlenik createZaposlenik(Zaposlenik zaposlenik) {
		return zaposlenikRepo.save(zaposlenik);
	}

	@Override
	public void deleteZaposlenik(Integer sif) {
		zaposlenikRepo.deleteById(sif);
		return;
	}

}
