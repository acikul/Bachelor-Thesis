package www.fer.hr.zavrsni.dto.request;

import java.sql.Date;

public class PrimkaRequest {
	
	private Date datumPrimka;
	
	private String valutaPrimka;
	
	private Integer sifSkladiste;
	
	private Integer sifPartner;

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

	public Integer getSifSkladiste() {
		return sifSkladiste;
	}

	public void setSifSkladiste(Integer sifSkladiste) {
		this.sifSkladiste = sifSkladiste;
	}

	public Integer getSifPartner() {
		return sifPartner;
	}

	public void setSifPartner(Integer sifPartner) {
		this.sifPartner = sifPartner;
	}
	
	

}
