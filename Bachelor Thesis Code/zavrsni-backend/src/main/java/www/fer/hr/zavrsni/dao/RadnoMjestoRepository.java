package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.RadnoMjesto;

public interface RadnoMjestoRepository extends JpaRepository<RadnoMjesto, Integer> {
	Optional<RadnoMjesto> findByNazivRadnoMjesto(String naziv);
}
