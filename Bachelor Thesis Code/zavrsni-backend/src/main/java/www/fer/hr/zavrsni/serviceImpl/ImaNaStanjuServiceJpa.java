package www.fer.hr.zavrsni.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.dao.ImaNaStanjuRepository;
import www.fer.hr.zavrsni.domain.ImaNaStanju;
import www.fer.hr.zavrsni.service.ImaNaStanjuService;

@Service
public class ImaNaStanjuServiceJpa implements ImaNaStanjuService{
	
	@Autowired
	ImaNaStanjuRepository imaNaStanjuRepo;

	@Override
	public List<ImaNaStanju> listAll() {
		return imaNaStanjuRepo.findAll();
	}

	@Override
	public ImaNaStanju createImaNaStanju(ImaNaStanju imaNaStanju) {
		return imaNaStanjuRepo.save(imaNaStanju);
	}
	
	

}
