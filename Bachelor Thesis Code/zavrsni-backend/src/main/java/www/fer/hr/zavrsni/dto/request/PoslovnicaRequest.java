package www.fer.hr.zavrsni.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PoslovnicaRequest {

	@NotBlank
	private String nazivPoslovnica;
	
	@NotBlank
	private String brTelPoslovnica;
	
	@NotBlank
	private String adresaPoslovnica;
	
	@NotNull
	private Integer sifGrad;
	
	@NotNull
	private Integer sifPartner;
	
	

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

	public Integer getSifGrad() {
		return sifGrad;
	}

	public void setSifGrad(Integer sifGrad) {
		this.sifGrad = sifGrad;
	}

	public Integer getSifPartner() {
		return sifPartner;
	}

	public void setSifPartner(Integer sifPartner) {
		this.sifPartner = sifPartner;
	}
	
	
}
