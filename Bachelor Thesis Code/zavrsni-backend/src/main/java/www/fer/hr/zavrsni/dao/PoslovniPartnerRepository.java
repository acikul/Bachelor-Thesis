package www.fer.hr.zavrsni.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.PoslovniPartner;

@Transactional
public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner, Integer> {
	
	Optional<PoslovniPartner> findByNazivPartner(String naziv);

}
