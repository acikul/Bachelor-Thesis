package www.fer.hr.zavrsni.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import www.fer.hr.zavrsni.domain.RadnoMjesto;
import www.fer.hr.zavrsni.domain.Zaposlenik;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Zaposlenik zaposlenik, RadnoMjesto radnoMjesto) {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (radnoMjesto.getNazivRadnoMjesto().equals("direktor")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_DIREKTOR"));
		}
		if (radnoMjesto.getNazivRadnoMjesto().equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (radnoMjesto.getNazivRadnoMjesto().equals("skladistar")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_SKLADISTAR"));
		}
		
		return new UserDetailsImpl(zaposlenik.getSifZaposlenik(), zaposlenik.getEmailZaposlenik(), zaposlenik.getPasswordHash(), authorities);
	}
	
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
