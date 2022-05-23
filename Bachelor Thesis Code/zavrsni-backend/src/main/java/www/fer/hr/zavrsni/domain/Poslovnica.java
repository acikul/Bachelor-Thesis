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
@Table(name = "poslovnica")
@JsonIgnoreProperties({"poslovniPartner", "racuns"})
public class Poslovnica {
	
	@Id
	@GeneratedValue
	@Column(name = "sifposlovnica")
	private int sifPoslovnica;
	
	@Column(name = "nazivposlovnica")
	private String nazivPoslovnica;
	
	@Column(name = "brojtelposlovnica")
	private String brTelPoslovnica;
	
	@Column(name = "adresaposlovnica")
	private String adresaPoslovnica;
	
	@ManyToOne
	@JoinColumn(name = "sifgrad")
	private Grad grad;
	
	@ManyToOne
	@JoinColumn(name = "sifpartner")
	private PoslovniPartner poslovniPartner;
	
	@OneToMany(mappedBy = "poslovnica")
	private Set<Racun> racuns;
	
	
	
	

	public int getSifPoslovnica() {
		return sifPoslovnica;
	}

	public void setSifPoslovnica(int sifPoslovnica) {
		this.sifPoslovnica = sifPoslovnica;
	}

	public String getNazivPoslovnica() {
		return nazivPoslovnica;
	}

	public void setNazivPoslovnica(String nazivPoslovnica) {
		this.nazivPoslovnica = nazivPoslovnica;
	}

	public String getBrTelPoslovnica() {
		return brTelPoslovnica;
	}

	public void setBrTelPoslovnica(String brTelPoslovnica) {
		this.brTelPoslovnica = brTelPoslovnica;
	}

	public String getAdresaPoslovnica() {
		return adresaPoslovnica;
	}

	public void setAdresaPoslovnica(String adresaPoslovnica) {
		this.adresaPoslovnica = adresaPoslovnica;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}
	
	

	public Set<Racun> getRacuns() {
		return racuns;
	}

	public void setRacuns(Set<Racun> racuns) {
		this.racuns = racuns;
	}

	public Poslovnica() {
	}
	
	
}
