package www.fer.hr.zavrsni.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import www.fer.hr.zavrsni.domain.keys.ImaNaStanjuKey;

@Entity
@Table(name = "ima_na_stanju")
public class ImaNaStanju {

	@EmbeddedId
	ImaNaStanjuKey kompozitSif;
	
	@ManyToOne
	@MapsId("sifSkladiste")
	@JoinColumn(name = "sifskladiste")
	Skladiste skladiste;
	
	@ManyToOne
	@MapsId("sifProizvod")
	@JoinColumn(name = "sifproizvod")
	Proizvod proizvod;
	
	@Column(name = "kolicinastanje")
	Integer kolicinaStanje;
	
	
	

	public ImaNaStanju() {
	}

	public ImaNaStanjuKey getKompozitSif() {
		return kompozitSif;
	}

	public void setKompozitSif(ImaNaStanjuKey kompozitSif) {
		this.kompozitSif = kompozitSif;
	}

	public Skladiste getSkladiste() {
		return skladiste;
	}

	public void setSkladiste(Skladiste skladiste) {
		this.skladiste = skladiste;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Integer getKolicinaStanje() {
		return kolicinaStanje;
	}

	public void setKolicinaStanje(Integer kolicinaStanje) {
		this.kolicinaStanje = kolicinaStanje;
	}
	
	
}
