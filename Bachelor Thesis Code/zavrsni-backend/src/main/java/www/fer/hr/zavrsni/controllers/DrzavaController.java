package www.fer.hr.zavrsni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.Drzava;
import www.fer.hr.zavrsni.service.DrzavaService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/drzava")
public class DrzavaController {

	@Autowired
	DrzavaService drzavaService;
	
	@GetMapping("/all")
	public List<Drzava> listAllDrzavas() {
		return drzavaService.listAll();
	}
}
