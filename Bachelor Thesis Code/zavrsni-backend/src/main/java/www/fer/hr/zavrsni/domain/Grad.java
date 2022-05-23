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
@Table(name = "grad")
@JsonIgnoreProperties({"zaposleniks", "skladistes", "poslovniPartners", "poslovnicas"})
public class Grad {
	
	@Id
	@GeneratedValue
	@Column(name = "sifgrad")
	private int sifGrad;
	
	@Column(name = "nazivgrad")
	private String nazivGrad;
	
	@ManyToOne
	@JoinColumn(name = "sifzupanija")
	private Zupanija zupanija;

	@OneToMany(mappedBy = "grad")
	private Set<Zaposlenik> zaposleniks;
	
	@OneToMany(mappedBy = "grad")
	private Set<Skladiste> skladistes;
	
	@OneToMany(mappedBy = "grad")
	private Set<PoslovniPartner> poslovniPartners;
	
	@OneToMany(mappedBy = "grad")
	private Set<Poslovnica> poslovnicas;
	
	
	
	
	

	public Set<PoslovniPartner> getPoslovniPartners() {
		return poslovniPartners;
	}

	public void setPoslovniPartners(Set<PoslovniPartner> poslovniPartners) {
		this.poslovniPartners = poslovniPartners;
	}

	public Set<Poslovnica> getPoslovnicas() {
		return poslovnicas;
	}

	public void setPoslovnicas(Set<Poslovnica> poslovnicas) {
		this.poslovnicas = poslovnicas;
	}

	public Set<Skladiste> getSkladistes() {
		return skladistes;
	}

	public void setSkladistes(Set<Skladiste> skladistes) {
		this.skladistes = skladistes;
	}

	public Zupanija getZupanija() {
		return zupanija;
	}

	public void setZupanija(Zupanija zupanija) {
		this.zupanija = zupanija;
	}

	public Set<Zaposlenik> getZaposleniks() {
		return zaposleniks;
	}

	public void setZaposleniks(Set<Zaposlenik> zaposleniks) {
		this.zaposleniks = zaposleniks;
	}

	public void setNazivGrad(String nazivGrad) {
		this.nazivGrad = nazivGrad;
	}

	public int getSifGrad() {
		return sifGrad;
	}

	public void setSifGrad(int sifGrad) {
		this.sifGrad = sifGrad;
	}

	public String getNazivGrad() {
		return nazivGrad;
	}

	public Grad(String nazivGrad) {
		this.nazivGrad = nazivGrad;
	}

	public Grad() {
	}
	
	
	
	
}
