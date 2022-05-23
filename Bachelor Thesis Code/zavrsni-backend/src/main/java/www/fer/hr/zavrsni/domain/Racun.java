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
@Table(name = "racun")
public class Racun {

	@Id
	@GeneratedValue
	@Column(name = "sifracun")
	private int sifRacun;
	
	@Column(name = "datumracun")
	private Date datumRacun;
	
	@Column(name = "valutaracun")
	private String valutaRacun;
	
	@Column(name = "placen")
	private Integer placen;
	
	@ManyToOne
	@JoinColumn(name = "sifskladiste")
	private Skladiste skladiste;
	
	@ManyToOne
	@JoinColumn(name = "sifzaposlenik")
	private Zaposlenik zaposlenik;
	

	@ManyToOne
	@JoinColumn(name = "sifposlovnica")
	private Poslovnica poslovnica;
	
	
	@Column(name = "ukupnacijena")
	private Double ukupnaCijena;
	
	
	@OneToMany(mappedBy = "racun")
	private Set<StavkaRacuna> stavke;
	
	
	
	

	public Set<StavkaRacuna> getStavke() {
		return stavke;
	}

	public void setStavke(Set<StavkaRacuna> stavke) {
		this.stavke = stavke;
	}

	public Double getUkupnaCijena() {
		return ukupnaCijena;
	}

	public void setUkupnaCijena(Double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}

	public Racun() {
	}

	public int getSifRacun() {
		return sifRacun;
	}

	public void setSifRacun(int sifRacun) {
		this.sifRacun = sifRacun;
	}

	public Date getDatumRacun() {
		return datumRacun;
	}

	public void setDatumRacun(Date datumRacun) {
		this.datumRacun = datumRacun;
	}

	public String getValutaRacun() {
		return valutaRacun;
	}

	public void setValutaRacun(String valutaRacun) {
		this.valutaRacun = valutaRacun;
	}

	public Integer getPlacen() {
		return placen;
	}

	public void setPlacen(Integer placen) {
		this.placen = placen;
	}

	public Skladiste getSkladiste() {
		return skladiste;
	}

	public void setSkladiste(Skladiste skladiste) {
		this.skladiste = skladiste;
	}

	public Zaposlenik getZaposlenik() {
		return zaposlenik;
	}

	public void setZaposlenik(Zaposlenik zaposlenik) {
		this.zaposlenik = zaposlenik;
	}

	public Poslovnica getPoslovnica() {
		return poslovnica;
	}

	public void setPoslovnica(Poslovnica poslovnica) {
		this.poslovnica = poslovnica;
	}

}
