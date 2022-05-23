package www.fer.hr.zavrsni.dto.request;

import javax.validation.constraints.NotNull;

public class StavkaRacunaRequest {

	@NotNull
	private Integer sifProizvod;
	
	@NotNull
	private Integer kolicinaProizvodRacun;

	public Integer getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(Integer sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

//	public Integer getSifRacun() {
//		return sifRacun;
//	}
//
//	public void setSifRacun(Integer sifRacun) {
//		this.sifRacun = sifRacun;
//	}

	public Integer getKolicinaProizvodRacun() {
		return kolicinaProizvodRacun;
	}

	public void setKolicinaProizvodRacun(Integer kolicinaProizvodRacun) {
		this.kolicinaProizvodRacun = kolicinaProizvodRacun;
	}
	
	
}
