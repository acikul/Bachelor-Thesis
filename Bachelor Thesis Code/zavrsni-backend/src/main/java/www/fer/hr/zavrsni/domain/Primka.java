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

@Entity
@Table(name = "primka")
public class Primka {
	
	@Id
	@GeneratedValue
	@Column(name = "sifprimka")
	private int sifPrimka;
	
	@Column(name = "datumprimka")
	private Date datumPrimka;
	
	@Column(name = "valutaprimka")
	private String valutaPrimka;
	
	@ManyToOne
	@JoinColumn(name = "sifskladiste")
	private Skladiste skladiste;
	
	@ManyToOne
	@JoinColumn(name = "sifpartner")
	private PoslovniPartner poslovniPartner;
	
	@OneToMany(mappedBy = "primka")
	private Set<StavkaPrimke> stavke;
	
	

	public int getSifPrimka() {
		return sifPrimka;
	}

	public void setSifPrimka(int sifPrimka) {
		this.sifPrimka = sifPrimka;
	}

	public Date getDatumPrimka() {
		return datumPrimka;
	}

	public void setDatumPrimka(Date datumPrimka) {
		this.datumPrimka = datumPrimka;
	}

	public String getValutaPrimka() {
		return valutaPrimka;
	}

	public void setValutaPrimka(String valutaPrimka) {
		this.valutaPrimka = valutaPrimka;
	}

	public Skladiste getSkladiste() {
		return skladiste;
	}

	public void setSkladiste(Skladiste skladiste) {
		this.skladiste = skladiste;
	}

	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}

	public Set<StavkaPrimke> getStavke() {
		return stavke;
	}

	public void setStavke(Set<StavkaPrimke> stavke) {
		this.stavke = stavke;
	}

	public Primka() {
	}
	
	
}
