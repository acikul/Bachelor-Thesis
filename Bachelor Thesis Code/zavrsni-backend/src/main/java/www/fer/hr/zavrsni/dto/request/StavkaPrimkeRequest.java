package www.fer.hr.zavrsni.dto.request;

import javax.validation.constraints.NotNull;

public class StavkaPrimkeRequest {

	@NotNull
	private Integer sifProizvod;
	
	@NotNull
	private Integer kolicinaProizvodPrimka;
	
	@NotNull
	private Double kupovnaCijena;

	public Integer getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(Integer sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

	public Integer getKolicinaProizvodPrimka() {
		return kolicinaProizvodPrimka;
	}

	public void setKolicinaProizvodPrimka(Integer kolicinaProizvodPrimka) {
		this.kolicinaProizvodPrimka = kolicinaProizvodPrimka;
	}

	public Double getKupovnaCijena() {
		return kupovnaCijena;
	}

	public void setKupovnaCijena(Double kupovnaCijena) {
		this.kupovnaCijena = kupovnaCijena;
	}
	
}
