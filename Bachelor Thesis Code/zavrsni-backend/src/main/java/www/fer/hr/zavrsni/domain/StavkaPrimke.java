package www.fer.hr.zavrsni.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import www.fer.hr.zavrsni.domain.keys.StavkaPrimkeKey;

@Entity
@Table(name = "stavka_primke")
@JsonIgnoreProperties({"primka"})
public class StavkaPrimke {

	@EmbeddedId
	StavkaPrimkeKey kompozitSif;
	
	@ManyToOne
	@MapsId("sifProizvod")
	@JoinColumn(name = "sifproizvod")
	Proizvod proizvod;
	
	@ManyToOne
	@MapsId("sifPrimka")
	@JoinColumn(name = "sifprimka")
	Primka primka;
	
	@Column(name = "kolicinaproizvodprimka")
	Integer kolicinaProizvodPrimka;
	
	@Column(name = "kupovnacijena")
	Double kupovnaCijena;

	public StavkaPrimkeKey getKompozitSif() {
		return kompozitSif;
	}

	public void setKompozitSif(StavkaPrimkeKey kompozitSif) {
		this.kompozitSif = kompozitSif;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Primka getPrimka() {
		return primka;
	}

	public void setPrimka(Primka primka) {
		this.primka = primka;
	}

	public Integer getKolicinaProizvodPrimka() {
		return kolicinaProizvodPrimka;
	}

	public void setKolicinaProizvodPrimka(Integer kolicinaProizvodPrimka) {
		this.kolicinaProizvodPrimka = kolicinaProizvodPrimka;
	}

	public Double getKupovnaCijena() {
		return kupovnaCijena;
	}

	public void setKupovnaCijena(Double kupovnaCijena) {
		this.kupovnaCijena = kupovnaCijena;
	}

	public StavkaPrimke() {
	}
	
	
}
