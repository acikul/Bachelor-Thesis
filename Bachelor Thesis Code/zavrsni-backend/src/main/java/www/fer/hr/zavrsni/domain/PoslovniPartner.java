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
@Table(name = "poslovni_partner")
@JsonIgnoreProperties({"primkas", "rabats"})
public class PoslovniPartner {
	
	@Id
	@GeneratedValue
	@Column(name = "sifpartner")
	private int sifPartner;
	
	@Column(name = "nazivpartner")
	private String nazivPartner;
	
	@Column(name = "odgoda")
	private Integer odgodaPartner;
	
	@Column(name = "brojtelpartner")
	private String brTelPartner;
	
	@Column(name = "adresasjediste")
	private String adresaSjedistePartner;
	
	@ManyToOne
	@JoinColumn(name = "sifgrad")
	private Grad grad;

	@OneToMany(mappedBy = "poslovniPartner")
	private Set<Poslovnica> poslovnicas;
	
	@OneToMany(mappedBy = "poslovniPartner")
	private Set<Primka> primkas;
	
	@OneToMany(mappedBy = "partner")
	private Set<Rabat> rabats;
	
	
	
	
	
	public Set<Rabat> getRabats() {
		return rabats;
	}

	public void setRabats(Set<Rabat> rabats) {
		this.rabats = rabats;
	}

	public Set<Primka> getPrimkas() {
		return primkas;
	}

	public void setPrimkas(Set<Primka> primkas) {
		this.primkas = primkas;
	}

	public Set<Poslovnica> getPoslovnicas() {
		return poslovnicas;
	}

	public void setPoslovnicas(Set<Poslovnica> poslovnicas) {
		this.poslovnicas = poslovnicas;
	}

	public int getSifPartner() {
		return sifPartner;
	}

	public void setSifPartner(int sifPartner) {
		this.sifPartner = sifPartner;
	}

	public String getNazivPartner() {
		return nazivPartner;
	}

	public void setNazivPartner(String nazivPartner) {
		this.nazivPartner = nazivPartner;
	}

	public Integer getOdgodaPartner() {
		return odgodaPartner;
	}

	public void setOdgodaPartner(Integer odgodaPartner) {
		this.odgodaPartner = odgodaPartner;
	}

	public String getBrTelPartner() {
		return brTelPartner;
	}

	public void setBrTelPartner(String brTelPartner) {
		this.brTelPartner = brTelPartner;
	}

	public String getAdresaSjedistePartner() {
		return adresaSjedistePartner;
	}

	public void setAdresaSjedistePartner(String adresaSjedistePartner) {
		this.adresaSjedistePartner = adresaSjedistePartner;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public PoslovniPartner() {
	}
	
	
}
