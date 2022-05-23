package www.fer.hr.zavrsni.domain.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RabatKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sifproizvod")
	Integer sifProizvod;
	
	@Column(name = "sifpartner")
	Integer sifPartner;

	public Integer getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(Integer sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

	public Integer getSifPartner() {
		return sifPartner;
	}

	public void setSifPartner(Integer sifPartner) {
		this.sifPartner = sifPartner;
	}

	public RabatKey() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sifPartner == null) ? 0 : sifPartner.hashCode());
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
		RabatKey other = (RabatKey) obj;
		if (sifPartner == null) {
			if (other.sifPartner != null)
				return false;
		} else if (!sifPartner.equals(other.sifPartner))
			return false;
		if (sifProizvod == null) {
			if (other.sifProizvod != null)
				return false;
		} else if (!sifProizvod.equals(other.sifProizvod))
			return false;
		return true;
	}

	
}
