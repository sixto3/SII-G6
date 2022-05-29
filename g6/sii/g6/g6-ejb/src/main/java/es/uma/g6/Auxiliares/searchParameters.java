package es.uma.g6.Auxiliares;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class searchParameters {
	
	
	private name nombre;
	

	@Temporal(TemporalType.DATE)
	private Date startPeriod;
	
	@Temporal(TemporalType.DATE)
	private Date endPeriod;
	
	
	
	
	

	public name getNombre() {
		return nombre;
	}

	public void setNombre(name nombre) {
		this.nombre = nombre;
	}

	public Date getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}

	public Date getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}
	
	
	
	
}
