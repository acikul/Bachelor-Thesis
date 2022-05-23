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
@Table(name = "skladiste")
@JsonIgnoreProperties({"imaNaStanjus", "racuns", "primkas"})
public class Skladiste {

	@Id
	@GeneratedValue
	@Column(name = "sifskladiste")
	private int sifSkladiste;
	
	@Column(name = "nazivskladiste")
	private String nazivSkladiste;
	
	@Column(name = "povrsina")
	private double povrsinaSkladiste;
	
	@Column(name = "adresaskladiste")
	private String adresaSkladiste;
	
	@ManyToOne
	@JoinColumn(name = "sifgrad")
	private Grad grad;
	
	@OneToMany(mappedBy = "skladiste")
	private Set<ImaNaStanju> imaNaStanjus;
	
	@OneToMany(mappedBy = "skladiste")
	private Set<Racun> racuns;
	
	@OneToMany(mappedBy = "skladiste")
	private Set<Primka> primkas;
	
	
	
	

	public Set<Racun> getRacuns() {
		return racuns;
	}

	public void setRacuns(Set<Racun> racuns) {
		this.racuns = racuns;
	}

	public Set<Primka> getPrimkas() {
		return primkas;
	}

	public void setPrimkas(Set<Primka> primkas) {
		this.primkas = primkas;
	}

	public Set<ImaNaStanju> getImaNaStanjus() {
		return imaNaStanjus;
	}

	public void setImaNaStanjus(Set<ImaNaStanju> imaNaStanjus) {
		this.imaNaStanjus = imaNaStanjus;
	}

	public int getSifSkladiste() {
		return sifSkladiste;
	}

	public void setSifSkladiste(int sifSkladiste) {
		this.sifSkladiste = sifSkladiste;
	}

	public String getNazivSkladiste() {
		return nazivSkladiste;
	}

	public void setNazivSkladiste(String nazivSkladiste) {
		this.nazivSkladiste = nazivSkladiste;
	}

	public double getPovrsinaSkladiste() {
		return povrsinaSkladiste;
	}

	public void setPovrsinaSkladiste(double povrsinaSkladiste) {
		this.povrsinaSkladiste = povrsinaSkladiste;
	}

	public String getAdresaSkladiste() {
		return adresaSkladiste;
	}

	public void setAdresaSkladiste(String adresaSkladiste) {
		this.adresaSkladiste = adresaSkladiste;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public Skladiste() {
	}

}
