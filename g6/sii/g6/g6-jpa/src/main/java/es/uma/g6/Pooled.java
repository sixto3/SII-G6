package es.uma.g6;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Pooled extends Fintech{
	
	@OneToMany(mappedBy ="pooled")
	private List<Depositada_en> depositada_pooled;
	

	public Pooled() {
		super();
	}
	
	
	
	public List<Depositada_en> getDepositada_pooled() {
		return depositada_pooled;
	}



	public void setDepositada_pooled(List<Depositada_en> depositada_pooled) {
		this.depositada_pooled = depositada_pooled;
	}



	@Override
	public String toString() {
		return "Pooled [getEstado()=" + getEstado() + ", getFechaApertura()=" + getFechaApertura()
				+ ", getFechaCierre()=" + getFechaCierre() + ", getClasificacion()=" + getClasificacion()
				+ ", toString()=" + super.toString() + ", hashCode()=" + hashCode() + ", getIBAN()=" + getIBAN()
				+ ", getSWIFT()=" + getSWIFT() + ", getClass()=" + getClass() + "]";
	}



	

}
