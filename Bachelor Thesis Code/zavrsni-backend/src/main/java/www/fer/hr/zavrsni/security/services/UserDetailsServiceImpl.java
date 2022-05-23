package www.fer.hr.zavrsni.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import www.fer.hr.zavrsni.domain.RadnoMjesto;
import www.fer.hr.zavrsni.domain.Zaposlenik;
import www.fer.hr.zavrsni.service.RadnoMjestoService;
import www.fer.hr.zavrsni.service.ZaposlenikService;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ZaposlenikService zaposlenikService;
	
	@Autowired
	private RadnoMjestoService radnoMjestoService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		Zaposlenik zaposlenik = zaposlenikService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found - username: " + username));
		RadnoMjesto radnoMjesto = radnoMjestoService.findBySifRadnoMjesto(zaposlenik.getRadnoMj().getSifRadnoMjesto()).get();
		
//		RadnoMjesto radnoMjesto = radnoMjestoService.findById(zaposlenik.getSifRadnoMjesto());
		
		return UserDetailsImpl.build(zaposlenik, radnoMjesto);
	}
}
