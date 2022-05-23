package www.fer.hr.zavrsni.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class PoslovniPartnerRequest {
	
	@NotBlank
	private String nazivPartner;
	
	@NotBlank
	private String brTelPartner;
	
	@NotBlank
	private String adresaSjedistePartner;
	
	@NotNull
	private Integer odgodaPartner;
	
	@NotNull
	private Integer sifGrad;

	public String getNazivPartner() {
		return nazivPartner;
	}

	public void setNazivPartner(String nazivPartner) {
		this.nazivPartner = nazivPartner;
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

	public Integer getOdgodaPartner() {
		return odgodaPartner;
	}

	public void setOdgodaPartner(Integer odgodaPartner) {
		this.odgodaPartner = odgodaPartner;
	}

	public Integer getSifGrad() {
		return sifGrad;
	}

	public void setSifGrad(Integer sifGrad) {
		this.sifGrad = sifGrad;
	}


	
}
