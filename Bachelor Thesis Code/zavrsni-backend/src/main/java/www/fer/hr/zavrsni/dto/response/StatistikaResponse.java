package www.fer.hr.zavrsni.dto.response;

import java.util.LinkedList;
import java.util.List;

public class StatistikaResponse {
	
	private List<MjesecPromet> mjeseciPrometi;
	private Double ukupno;
	
	public StatistikaResponse() {
		this.mjeseciPrometi = new LinkedList<>();
		this.ukupno = 0.0;
	}

	public List<MjesecPromet> getMjeseciPrometi() {
		return mjeseciPrometi;
	}

	public void addMjeseciPrometi(MjesecPromet mjesecPromet) {
		this.mjeseciPrometi.add(mjesecPromet);
	}

	public Double getUkupno() {
		return ukupno;
	}

	public void setUkupno(Double ukupno) {
		this.ukupno = ukupno;
	}
	
	
}

