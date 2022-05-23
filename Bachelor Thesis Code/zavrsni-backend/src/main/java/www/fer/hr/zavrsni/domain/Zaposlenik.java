package www.fer.hr.zavrsni.domain;

import java.sql.Date;
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
@Table(name = "zaposlenik")
@JsonIgnoreProperties({"racuns"})
public class Zaposlenik {

	@Id
	@GeneratedValue
	@Column(name = "sifzaposlenik")
	private int sifZaposlenik;
	
	@Column(name = "oibzaposlenik")
	private String oibZaposlenik;
	
	@Column(name = "ime")
	private String imeZaposlenik;
	
	@Column(name = "prezime")
	private String prezimeZaposlenik;
	
	@Column(name = "email")
	private String emailZaposlenik;
	
	@Column(name = "brojtel")
	private String brojTelZaposlenik;
	
	@Column(name = "datumrod")
	private Date datumRodenja;

	@Column(name = "datumpoc")
	private Date datumPocetka;
	
	@Column(name = "datumkraj")
	private Date datumKraj;
	
	@Column(name = "passwordhash")
	private String passwordHash;
	
//	@Column(name= "sifradnomjesto")
//	private int sifRadnoMjesto;
	
//	@Column(name= "sifgrad")
//	private int sifGrad;
	
	@Column(name = "adresazaposlenik")
	private String adresaZaposlenik;

	@ManyToOne
	@JoinColumn(name = "sifradnomjesto")
	private RadnoMjesto radnoMj;
	
	@ManyToOne
	@JoinColumn(name = "sifgrad")
	private Grad grad;
	
	@OneToMany(mappedBy = "zaposlenik")
	private Set<Racun> racuns;
	
	
	
	
	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public RadnoMjesto getRadnoMj() {
		return radnoMj;
	}

	public void setRadnoMj(RadnoMjesto radnoMj) {
		this.radnoMj = radnoMj;
	}

	
	public String getAdresaZaposlenik() {
		return adresaZaposlenik;
	}

	public void setAdresaZaposlenik(String adresaZaposlenik) {
		this.adresaZaposlenik = adresaZaposlenik;
	}
	
	
	public int getSifZaposlenik() {
		return sifZaposlenik;
	}

	public void setSifZaposlenik(int sifZaposlenik) {
		this.sifZaposlenik = sifZaposlenik;
	}

	public String getOibZaposlenik() {
		return oibZaposlenik;
	}

	public void setOibZaposlenik(String oibZaposlenik) {
		this.oibZaposlenik = oibZaposlenik;
	}

	public String getImeZaposlenik() {
		return imeZaposlenik;
	}

	public void setImeZaposlenik(String imeZaposlenik) {
		this.imeZaposlenik = imeZaposlenik;
	}

	public String getPrezimeZaposlenik() {
		return prezimeZaposlenik;
	}

	public void setPrezimeZaposlenik(String prezimeZaposlenik) {
		this.prezimeZaposlenik = prezimeZaposlenik;
	}

	public String getEmailZaposlenik() {
		return emailZaposlenik;
	}

	public void setEmailZaposlenik(String emailZaposlenik) {
		this.emailZaposlenik = emailZaposlenik;
	}

	public String getBrojTelZaposlenik() {
		return brojTelZaposlenik;
	}

	public void setBrojTelZaposlenik(String brojTelZaposlenik) {
		this.brojTelZaposlenik = brojTelZaposlenik;
	}

	public Date getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(Date datumRodenja) {
		this.datumRodenja = datumRodenja;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumKraj() {
		return datumKraj;
	}

	public void setDatumKraj(Date datumKraj) {
		this.datumKraj = datumKraj;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	

	public Zaposlenik() {
	}
	
	
}
