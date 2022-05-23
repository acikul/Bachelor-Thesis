package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Grad;

public interface GradRepository extends JpaRepository<Grad, Integer> {
	Optional<Grad> findByNazivGrad(String naziv);
}
