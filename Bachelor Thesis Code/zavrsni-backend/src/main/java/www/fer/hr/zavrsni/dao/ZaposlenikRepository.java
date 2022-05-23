package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Zaposlenik;

@Transactional
public interface ZaposlenikRepository extends JpaRepository<Zaposlenik, Integer>{
	
	Optional<Zaposlenik> findByEmailZaposlenik(String email);
	
	Boolean existsByEmailZaposlenik(String email);
}
