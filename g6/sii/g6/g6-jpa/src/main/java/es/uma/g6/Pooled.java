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

	@Override
	public String toString() {
		return "Pooled [getEstado()=" + getEstado() + ", getFechaApertura()=" + getFechaApertura()
				+ ", getFechaCierre()=" + getFechaCierre() + ", getClasificacion()=" + getClasificacion()
				+ ", toString()=" + super.toString() + ", hashCode()=" + hashCode() + ", getIBAN()=" + getIBAN()
				+ ", getSWIFT()=" + getSWIFT() + ", getClass()=" + getClass() + "]";
	}



	

}
