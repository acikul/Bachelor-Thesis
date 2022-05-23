package www.fer.hr.zavrsni.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import www.fer.hr.zavrsni.domain.keys.RabatKey;

@Entity
@Table(name = "rabat")
public class Rabat {
	
	@EmbeddedId
	RabatKey kompozitSif;
	
	@ManyToOne
	@MapsId("sifProizvod")
	@JoinColumn(name = "sifproizvod")
	Proizvod proizvod;
	
	@ManyToOne
	@MapsId("sifPartner")
	@JoinColumn(name = "sifpartner")
	PoslovniPartner partner;
	
	@Column(name = "iznosrabat")
	Integer iznosRabat;

	public RabatKey getKompozitSif() {
		return kompozitSif;
	}

	public void setKompozitSif(RabatKey kompozitSif) {
		this.kompozitSif = kompozitSif;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public PoslovniPartner getPartner() {
		return partner;
	}

	public void setPartner(PoslovniPartner partner) {
		this.partner = partner;
	}

	public Integer getIznosRabat() {
		return iznosRabat;
	}

	public void setIznosRabat(Integer iznosRabat) {
		this.iznosRabat = iznosRabat;
	}

	public Rabat() {
	}
	
	
}
