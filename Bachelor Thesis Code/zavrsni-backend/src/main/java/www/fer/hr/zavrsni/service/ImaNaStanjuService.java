package www.fer.hr.zavrsni.service;

import java.util.List;

import www.fer.hr.zavrsni.domain.ImaNaStanju;

public interface ImaNaStanjuService {
	List<ImaNaStanju> listAll();
	
	ImaNaStanju createImaNaStanju(ImaNaStanju imaNaStanju);
}
