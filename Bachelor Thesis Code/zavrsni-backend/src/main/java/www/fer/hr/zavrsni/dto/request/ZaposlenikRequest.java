package www.fer.hr.zavrsni.dto.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class ZaposlenikRequest {
	@NotBlank
	private String oib;
	
	@NotBlank
	private String ime;
	
	@NotBlank
	private String prezime;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String brojTel;
	
	private Date datumRod;
	
	private Date datumPoc;
	
	private Date datumKraj;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String adresa;

	private int sifRadnoMjesto;
	
	private int sifGrad;
	
	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrojTel() {
		return brojTel;
	}

	public void setBrojTel(String brojTel) {
		this.brojTel = brojTel;
	}

	public Date getDatumRod() {
		return datumRod;
	}

	public void setDatumRod(Date datumRod) {
		this.datumRod = datumRod;
	}

	public Date getDatumPoc() {
		return datumPoc;
	}

	public void setDatumPoc(Date datumPoc) {
		this.datumPoc = datumPoc;
	}

	public Date getDatumKraj() {
		return datumKraj;
	}

	public void setDatumKraj(Date datumKraj) {
		this.datumKraj = datumKraj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSifRadnoMjesto() {
		return sifRadnoMjesto;
	}

	public void setSifRadnoMjesto(int sifRadnoMjesto) {
		this.sifRadnoMjesto = sifRadnoMjesto;
	}

	public int getSifGrad() {
		return sifGrad;
	}

	public void setSifGrad(int sifGrad) {
		this.sifGrad = sifGrad;
	}

}
