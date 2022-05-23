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
@Table(name= "proizvod")
@JsonIgnoreProperties({"imaNaStanjus", "stavkeRacuna", "stavkePrimke", "rabats"})
public class Proizvod {
	
	@Id
	@GeneratedValue
	@Column(name = "sifproizvod")
	private int sifProizvod;
	
	@Column(name = "barkod")
	private String barkodProizvod;
	
	@Column(name = "nazivproizvod")
	private String nazivProizvod;
	
	@Column(name = "mjera")
	private String mjeraProizvod;
	
	@Column(name = "vrsta")
	private String vrstaProizvod;
	
	@Column(name = "podgrupa")
	private String podgrupaProizvod;
	
	@Column(name = "kolicinaukutiji")
	private Integer kolicinaKutijaProizvod;
	
	@Column(name = "masa")
	private Double masaProizvod;
	
	@Column(name = "cijena")
	private Double cijenaProizvod;
	
	@ManyToOne
	@JoinColumn(name = "sifproizvodac")
	private Proizvodac proizvodac;

	@OneToMany(mappedBy = "proizvod")
	private Set<ImaNaStanju> imaNaStanjus;
	
	@OneToMany(mappedBy = "proizvod")
	private Set<StavkaRacuna> stavkeRacuna;
	
	@OneToMany(mappedBy = "proizvod")
	private Set<StavkaPrimke> stavkePrimke;
	
	@OneToMany(mappedBy = "proizvod")
	private Set<Rabat> rabats;
	
	
	
	public Set<StavkaPrimke> getStavkePrimke() {
		return stavkePrimke;
	}

	public void setStavkePrimke(Set<StavkaPrimke> stavkePrimke) {
		this.stavkePrimke = stavkePrimke;
	}

	public Set<StavkaRacuna> getStavkeRacuna() {
		return stavkeRacuna;
	}

	public void setStavkeRacuna(Set<StavkaRacuna> stavkeRacuna) {
		this.stavkeRacuna = stavkeRacuna;
	}

	public Set<ImaNaStanju> getImaNaStanjus() {
		return imaNaStanjus;
	}

	public void setImaNaStanjus(Set<ImaNaStanju> imaNaStanjus) {
		this.imaNaStanjus = imaNaStanjus;
	}

	public int getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(int sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

	public String getBarkodProizvod() {
		return barkodProizvod;
	}

	public void setBarkodProizvod(String barkodProizvod) {
		this.barkodProizvod = barkodProizvod;
	}

	public String getNazivProizvod() {
		return nazivProizvod;
	}

	public void setNazivProizvod(String nazivProizvod) {
		this.nazivProizvod = nazivProizvod;
	}

	public String getMjeraProizvod() {
		return mjeraProizvod;
	}

	public void setMjeraProizvod(String mjeraProizvod) {
		this.mjeraProizvod = mjeraProizvod;
	}

	public String getVrstaProizvod() {
		return vrstaProizvod;
	}

	public void setVrstaProizvod(String vrstaProizvod) {
		this.vrstaProizvod = vrstaProizvod;
	}

	public String getPodgrupaProizvod() {
		return podgrupaProizvod;
	}

	public void setPodgrupaProizvod(String podgrupaProizvod) {
		this.podgrupaProizvod = podgrupaProizvod;
	}

	public Integer getKolicinaKutijaProizvod() {
		return kolicinaKutijaProizvod;
	}

	public void setKolicinaKutijaProizvod(Integer kolicinaKutijaProizvod) {
		this.kolicinaKutijaProizvod = kolicinaKutijaProizvod;
	}

	public Double getMasaProizvod() {
		return masaProizvod;
	}

	public void setMasaProizvod(Double masaProizvod) {
		this.masaProizvod = masaProizvod;
	}

	public Double getCijenaProizvod() {
		return cijenaProizvod;
	}

	public void setCijenaProizvod(Double cijenaProizvod) {
		this.cijenaProizvod = cijenaProizvod;
	}

	public Proizvodac getProizvodac() {
		return proizvodac;
	}

	public void setProizvodac(Proizvodac proizvodac) {
		this.proizvodac = proizvodac;
	}

	public Proizvod() {
	}
	
	
}
