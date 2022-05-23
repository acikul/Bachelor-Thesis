package www.fer.hr.zavrsni.dto.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class RacunRequest {
	
	private Date datumRacun;
	
	private String valutaRacun;
	
	private int placenRacun;
	
	private int sifSkladiste;
	
	private int sifPoslovnica;
	
	private int sifZaposlenik;
	
	
	
	

	@Override
	public String toString() {
		return "RacunRequest [datumRacun=" + datumRacun + ", valutaRacun=" + valutaRacun + ", placen=" + placenRacun
				+ ", sifSkladiste=" + sifSkladiste + ", sifPoslovnica=" + sifPoslovnica + ", sifZaposlenik="
				+ sifZaposlenik + "]";
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

	public int getPlacenRacun() {
		return placenRacun;
	}

	public void setPlacenRacun(int placen) {
		this.placenRacun = placen;
	}

	public int getSifSkladiste() {
		return sifSkladiste;
	}


	public void setSifSkladiste(int sifSkladiste) {
		this.sifSkladiste = sifSkladiste;
	}

	public int getSifPoslovnica() {
		return sifPoslovnica;
	}

	public void setSifPoslovnica(int sifPoslovnica) {
		this.sifPoslovnica = sifPoslovnica;
	}

	public int getSifZaposlenik() {
		return sifZaposlenik;
	}

	public void setSifZaposlenik(int sifZaposlenik) {
		this.sifZaposlenik = sifZaposlenik;
	}
	
	

}
