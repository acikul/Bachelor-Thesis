package www.fer.hr.zavrsni.domain.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StavkaPrimkeKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sifproizvod")
	Integer sifProizvod;
	
	@Column(name = "sifprimka")
	Integer sifPrimka;

	public Integer getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(Integer sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

	public Integer getSifPrimka() {
		return sifPrimka;
	}

	public void setSifPrimka(Integer sifPrimka) {
		this.sifPrimka = sifPrimka;
	}

	public StavkaPrimkeKey() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sifPrimka == null) ? 0 : sifPrimka.hashCode());
		result = prime * result + ((sifProizvod == null) ? 0 : sifProizvod.hashCode());
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
		StavkaPrimkeKey other = (StavkaPrimkeKey) obj;
		if (sifPrimka == null) {
			if (other.sifPrimka != null)
				return false;
		} else if (!sifPrimka.equals(other.sifPrimka))
			return false;
		if (sifProizvod == null) {
			if (other.sifProizvod != null)
				return false;
		} else if (!sifProizvod.equals(other.sifProizvod))
			return false;
		return true;
	}
	
	
}
