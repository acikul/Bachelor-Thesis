package www.fer.hr.zavrsni.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "drzava")
@JsonIgnoreProperties({"zupanijas"})
public class Drzava {
	
	@Id
	@GeneratedValue
	@Column(name = "sifdrzava")
	private int sifDrzava;
	
	@Column(name = "nazivdrzava")
	private String nazivDrzava;
	
	
	@OneToMany(mappedBy = "drzava")
	private Set<Zupanija> zupanijas;
	

	public int getSifdrzava() {
		return sifDrzava;
	}

	public void setSifdrzava(int sifdrzava) {
		this.sifDrzava = sifdrzava;
	}

	public String getNazivDrzava() {
		return nazivDrzava;
	}

	public void setNazivDrzava(String nazivDrzava) {
		this.nazivDrzava = nazivDrzava;
	}

	public Drzava(String nazivDrzava) {
		this.nazivDrzava = nazivDrzava;
	}

	public Drzava() {
	}
	
	
}
