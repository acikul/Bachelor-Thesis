package www.fer.hr.zavrsni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.fer.hr.zavrsni.domain.RadnoMjesto;
import www.fer.hr.zavrsni.service.RadnoMjestoService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("api/radnomjesto")
public class RadnoMjestoController {

	@Autowired
	RadnoMjestoService radnoMjestoService;
	
	@GetMapping("")
	public List<RadnoMjesto> listAllRadnoMjestos() {
		return radnoMjestoService.listAll();
	}
}
