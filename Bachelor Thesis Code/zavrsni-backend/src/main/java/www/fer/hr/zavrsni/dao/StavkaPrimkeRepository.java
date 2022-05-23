package www.fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.StavkaPrimke;
import www.fer.hr.zavrsni.domain.keys.StavkaPrimkeKey;

public interface StavkaPrimkeRepository extends JpaRepository<StavkaPrimke, StavkaPrimkeKey>{

}
