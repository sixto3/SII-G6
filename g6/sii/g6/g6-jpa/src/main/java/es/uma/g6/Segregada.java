package es.uma.g6;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Segregada extends Fintech{
	private double comision;
	@OneToOne
	private Referencia ref;

	public Segregada() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Segregada(String iBAN, Long sWIFT, String estado, Date fechaApertura, Date fechaCierre, String clasificacion, double comision, Referencia ref) {
		super(iBAN, sWIFT, estado, fechaApertura, fechaCierre, clasificacion);
		this.comision = comision;
		this.ref = ref;
		// TODO Auto-generated constructor stub
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public Referencia getRef() {
		return ref;
	}

	public void setRef(Referencia ref) {
		this.ref = ref;
	}

	@Override
	public String toString() {
		return "Segregada [comision=" + comision + ", ref=" + ref + ", getEstado()=" + getEstado()
				+ ", getFechaApertura()=" + getFechaApertura() + ", getFechaCierre()=" + getFechaCierre()
				+ ", getClasificacion()=" + getClasificacion() + ", toString()=" + super.toString() + ", getIBAN()="
				+ getIBAN() + ", getSWIFT()=" + getSWIFT() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(comision);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segregada other = (Segregada) obj;
		if (Double.doubleToLongBits(comision) != Double.doubleToLongBits(other.comision))
			return false;
		return true;
	}


}
