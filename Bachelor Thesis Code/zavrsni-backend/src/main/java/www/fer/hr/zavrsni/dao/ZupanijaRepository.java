package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Zupanija;

public interface ZupanijaRepository extends JpaRepository<Zupanija, Integer> {
	Optional<Zupanija> findByNazivZupanija(String naziv);
}
