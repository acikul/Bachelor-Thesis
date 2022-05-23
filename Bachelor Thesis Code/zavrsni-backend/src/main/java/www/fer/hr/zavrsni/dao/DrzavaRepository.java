package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Drzava;

public interface DrzavaRepository extends JpaRepository<Drzava, Integer> {
	Optional<Drzava> findByNazivDrzava(String naziv);
}
