package www.fer.hr.zavrsni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.Grad;
import www.fer.hr.zavrsni.service.GradService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/grad")
public class GradController {
	
	@Autowired
	private GradService gradService;
	
	@GetMapping("/all")
	public List<Grad> listAllGrads() {
		return gradService.listAll();
	}
}
