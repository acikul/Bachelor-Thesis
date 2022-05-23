package www.fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.StavkaRacuna;
import www.fer.hr.zavrsni.domain.keys.StavkaRacunaKey;

public interface StavkaRacunaRepository extends JpaRepository<StavkaRacuna, StavkaRacunaKey> {

}
