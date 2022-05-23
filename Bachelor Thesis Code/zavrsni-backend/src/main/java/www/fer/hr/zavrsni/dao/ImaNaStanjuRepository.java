package www.fer.hr.zavrsni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fer.hr.zavrsni.domain.ImaNaStanju;
import www.fer.hr.zavrsni.domain.keys.ImaNaStanjuKey;

public interface ImaNaStanjuRepository extends JpaRepository<ImaNaStanju, ImaNaStanjuKey> {
}
