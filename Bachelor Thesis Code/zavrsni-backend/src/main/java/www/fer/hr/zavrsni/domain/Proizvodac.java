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
@Table(name = "proizvodac")
@JsonIgnoreProperties({"proizvods"})
public class Proizvodac {
	
	@Id
	@GeneratedValue
	@Column(name = "sifproizvodac")
	private int sifProizvodac;
	
	@Column(name = "nazivproizvodac")
	private String nazivProizvodac;
	
	@OneToMany(mappedBy = "proizvodac")
	private Set<Proizvod> proizvods;

	
	
	
	public int getSifProizvodac() {
		return sifProizvodac;
	}

	public void setSifProizvodac(int sifProizvodac) {
		this.sifProizvodac = sifProizvodac;
	}

	public String getNazivProizvodac() {
		return nazivProizvodac;
	}

	public void setNazivProizvodac(String nazivProizvodac) {
		this.nazivProizvodac = nazivProizvodac;
	}

	public Set<Proizvod> getProizvods() {
		return proizvods;
	}

	public void setProizvods(Set<Proizvod> proizvods) {
		this.proizvods = proizvods;
	}

	public Proizvodac() {
	}

}
