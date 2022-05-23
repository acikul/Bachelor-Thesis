package www.fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.Rabat;
import www.fer.hr.zavrsni.domain.keys.RabatKey;

public interface RabatRepository extends JpaRepository<Rabat, RabatKey> {

}
