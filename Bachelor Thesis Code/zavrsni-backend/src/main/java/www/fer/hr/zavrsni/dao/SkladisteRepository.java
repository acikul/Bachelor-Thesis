package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Skladiste;

@Transactional
public interface SkladisteRepository extends JpaRepository<Skladiste, Integer>{
	
	Optional<Skladiste> findByNazivSkladiste(String naziv);
	
}
