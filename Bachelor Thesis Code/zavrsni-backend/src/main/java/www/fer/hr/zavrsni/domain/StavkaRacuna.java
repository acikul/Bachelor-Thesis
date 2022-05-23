package www.fer.hr.zavrsni.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import www.fer.hr.zavrsni.domain.keys.StavkaRacunaKey;

@Entity
@Table(name = "stavka_racuna")
@JsonIgnoreProperties({"racun"})
public class StavkaRacuna {

	@EmbeddedId
	StavkaRacunaKey kompozitSif;
	
	@ManyToOne
	@MapsId("sifProizvod")
	@JoinColumn(name = "sifproizvod")
	Proizvod proizvod;
	
	@ManyToOne
	@MapsId("sifRacun")
	@JoinColumn(name = "sifracun")
	Racun racun;
	
	@Column(name = "kolicinaproizvodracun")
	Integer kolicinaProizvodRacun;
	
	
	
	

	public StavkaRacunaKey getKompozitSif() {
		return kompozitSif;
	}

	public void setKompozitSif(StavkaRacunaKey kompozitSif) {
		this.kompozitSif = kompozitSif;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public Integer getKolicinaProizvodRacun() {
		return kolicinaProizvodRacun;
	}

	public void setKolicinaProizvodRacun(Integer kolicinaProizvodRacun) {
		this.kolicinaProizvodRacun = kolicinaProizvodRacun;
	}

	public StavkaRacuna() {
	}
	
	
	
}
