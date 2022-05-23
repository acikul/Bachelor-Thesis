package www.fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Racun;

public interface RacunRepository extends JpaRepository<Racun, Integer> {
	
}
