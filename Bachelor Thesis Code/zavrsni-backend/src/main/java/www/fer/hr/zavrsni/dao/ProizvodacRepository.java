package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Proizvodac;

public interface ProizvodacRepository extends JpaRepository<Proizvodac, Integer>{
	Optional<Proizvodac> findByNazivProizvodac(String naziv);
}
