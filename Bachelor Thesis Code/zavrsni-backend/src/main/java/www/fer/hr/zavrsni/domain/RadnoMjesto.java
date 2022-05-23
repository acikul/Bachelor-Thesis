package www.fer.hr.zavrsni.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "radno_mjesto")
@JsonIgnoreProperties({"zaposleniks"})
public class RadnoMjesto {
	
	@Id
	@GeneratedValue
	@Column(name = "sifradnomjesto")
	private int sifRadnoMjesto;
	
	@Column(name = "nazivradnomjesto")
	private String nazivRadnoMjesto;
	
	@OneToMany(mappedBy = "radnoMj")
	private Set<Zaposlenik> zaposleniks;
	
	
	
	
	
	
	
	public Set<Zaposlenik> getZaposleniks() {
		return zaposleniks;
	}

	public void setZaposleniks(Set<Zaposlenik> zaposleniks) {
		this.zaposleniks = zaposleniks;
	}

	public int getSifRadnoMjesto() {
		return sifRadnoMjesto;
	}

	public void setSifRadnoMjesto(int sifRadnoMjesto) {
		this.sifRadnoMjesto = sifRadnoMjesto;
	}

	public String getNazivRadnoMjesto() {
		return nazivRadnoMjesto;
	}

	public void setNazivRadnoMjesto(String nazivRadnoMjesto) {
		this.nazivRadnoMjesto = nazivRadnoMjesto;
	}

	public RadnoMjesto(String nazivRadnoMjesto) {
		this.nazivRadnoMjesto = nazivRadnoMjesto;
	}

	public RadnoMjesto() {
	}
	
	
}
