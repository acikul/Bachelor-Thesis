package www.fer.hr.zavrsni.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "zupanija")
@JsonIgnoreProperties({"grads"})
public class Zupanija {
	
	@Id
	@GeneratedValue
	@Column(name = "sifzupanija")
	private int sifZupanija;
	
	@Column(name = "nazivzupanija")
	private String nazivZupanija;
	
	@ManyToOne
	@JoinColumn(name = "sifdrzava")
	private Drzava drzava;
	
	
	@OneToMany(mappedBy = "zupanija")
	private Set<Grad> grads;
	
	

	public int getSifZupanija() {
		return sifZupanija;
	}

	public void setSifZupanija(int sifZupanija) {
		this.sifZupanija = sifZupanija;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public Set<Grad> getGrads() {
		return grads;
	}

	public void setGrads(Set<Grad> grads) {
		this.grads = grads;
	}

	public String getNazivZupanija() {
		return nazivZupanija;
	}

	public void setNazivZupanija(String nazivZupanija) {
		this.nazivZupanija = nazivZupanija;
	}

	public Zupanija(String nazivZupanija) {
		this.nazivZupanija = nazivZupanija;
	}

	public Zupanija() {
	}
	
	
	
	
}
