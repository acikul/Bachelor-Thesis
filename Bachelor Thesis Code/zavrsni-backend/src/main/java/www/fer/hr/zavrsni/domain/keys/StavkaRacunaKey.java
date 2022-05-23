package www.fer.hr.zavrsni.domain.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StavkaRacunaKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sifproizvod")
	Integer sifProizvod;
	
	@Column(name = "sifracun")
	Integer sifRacun;

	public Integer getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(Integer sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

	public Integer getSifRacun() {
		return sifRacun;
	}

	public void setSifRacun(Integer sifRacun) {
		this.sifRacun = sifRacun;
	}

	public StavkaRacunaKey(Integer sifProizvod, Integer sifRacun) {
		this.sifProizvod = sifProizvod;
		this.sifRacun = sifRacun;
	}

	public StavkaRacunaKey() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sifProizvod == null) ? 0 : sifProizvod.hashCode());
		result = prime * result + ((sifRacun == null) ? 0 : sifRacun.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StavkaRacunaKey other = (StavkaRacunaKey) obj;
		if (sifProizvod == null) {
			if (other.sifProizvod != null)
				return false;
		} else if (!sifProizvod.equals(other.sifProizvod))
			return false;
		if (sifRacun == null) {
			if (other.sifRacun != null)
				return false;
		} else if (!sifRacun.equals(other.sifRacun))
			return false;
		return true;
	}
	
	

	

}
