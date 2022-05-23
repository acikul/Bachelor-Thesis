package www.fer.hr.zavrsni.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProizvodRequest {
	@NotBlank
	private String barkodProizvod;
	
	@NotBlank
	private String nazivProizvod;
	
	@NotBlank
	private String mjeraProizvod;
	
	@NotBlank
	private String vrstaProizvod;
	
	@NotBlank
	private String podgrupaProizvod;
	
	@NotNull
	private Integer kolicinaKutijaProizvod;
	
	@NotNull
	private Double masaProizvod;
	
	@NotNull
	private Double cijenaProizvod;
	
	@NotNull
	private Integer sifProizvodac;

	public String getBarkodProizvod() {
		return barkodProizvod;
	}

	public void setBarkodProizvod(String barkodProizvod) {
		this.barkodProizvod = barkodProizvod;
	}

	public String getNazivProizvod() {
		return nazivProizvod;
	}

	public void setNazivProizvod(String nazivProizvod) {
		this.nazivProizvod = nazivProizvod;
	}

	public String getMjeraProizvod() {
		return mjeraProizvod;
	}

	public void setMjeraProizvod(String mjeraProizvod) {
		this.mjeraProizvod = mjeraProizvod;
	}

	public String getVrstaProizvod() {
		return vrstaProizvod;
	}

	public void setVrstaProizvod(String vrstaProizvod) {
		this.vrstaProizvod = vrstaProizvod;
	}

	public String getPodgrupaProizvod() {
		return podgrupaProizvod;
	}

	public void setPodgrupaProizvod(String podgrupaProizvod) {
		this.podgrupaProizvod = podgrupaProizvod;
	}

	public Integer getKolicinaKutijaProizvod() {
		return kolicinaKutijaProizvod;
	}

	public void setKolicinaKutijaProizvod(Integer kolicinaKutijaProizvod) {
		this.kolicinaKutijaProizvod = kolicinaKutijaProizvod;
	}

	public Double getMasaProizvod() {
		return masaProizvod;
	}

	public void setMasaProizvod(Double masaProizvod) {
		this.masaProizvod = masaProizvod;
	}

	public Double getCijenaProizvod() {
		return cijenaProizvod;
	}

	public void setCijenaProizvod(Double cijenaProizvod) {
		this.cijenaProizvod = cijenaProizvod;
	}

	public Integer getSifProizvodac() {
		return sifProizvodac;
	}

	public void setSifProizvodac(Integer sifProizvodac) {
		this.sifProizvodac = sifProizvodac;
	}
	
	
}
