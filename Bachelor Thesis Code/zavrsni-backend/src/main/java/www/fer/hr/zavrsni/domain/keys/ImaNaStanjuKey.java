package www.fer.hr.zavrsni.domain.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ImaNaStanjuKey implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "sifskladiste")
	Integer sifSkladiste;
	
	@Column(name = "sifproizvod")
	Integer sifProizvod;

	public Integer getSifSkladiste() {
		return sifSkladiste;
	}

	public void setSifSkladiste(Integer sifSkladiste) {
		this.sifSkladiste = sifSkladiste;
	}

	public Integer getSifProizvod() {
		return sifProizvod;
	}

	public void setSifProizvod(Integer sifProizvod) {
		this.sifProizvod = sifProizvod;
	}

	public ImaNaStanjuKey(Integer sifSkladiste, Integer sifProizvod) {
		this.sifSkladiste = sifSkladiste;
		this.sifProizvod = sifProizvod;
	}

	public ImaNaStanjuKey() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sifProizvod == null) ? 0 : sifProizvod.hashCode());
		result = prime * result + ((sifSkladiste == null) ? 0 : sifSkladiste.hashCode());
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
		ImaNaStanjuKey other = (ImaNaStanjuKey) obj;
		if (sifProizvod == null) {
			if (other.sifProizvod != null)
				return false;
		} else if (!sifProizvod.equals(other.sifProizvod))
			return false;
		if (sifSkladiste == null) {
			if (other.sifSkladiste != null)
				return false;
		} else if (!sifSkladiste.equals(other.sifSkladiste))
			return false;
		return true;
	}
	
	
}
