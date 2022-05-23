package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Poslovnica;

@Transactional
public interface PoslovnicaRepository extends JpaRepository<Poslovnica, Integer> {

	Optional<Poslovnica> findByNazivPoslovnica(String naziv);
}
