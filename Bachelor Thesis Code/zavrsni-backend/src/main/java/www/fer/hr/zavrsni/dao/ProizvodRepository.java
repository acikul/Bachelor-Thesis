package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Proizvod;

@Transactional
public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {
	
	Optional<Proizvod> findByNazivProizvod(String naziv);
	
	Boolean existsByNazivProizvod(String naziv);
}
