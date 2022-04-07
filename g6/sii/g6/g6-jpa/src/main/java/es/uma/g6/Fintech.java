package es.uma.g6;


import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Fintech extends Cuenta{
	@Column(nullable = false)
	private String estado;
	@Temporal(TemporalType.DATE) @Column(nullable = false)
	private Date fechaApertura;
	@Temporal(TemporalType.DATE)
	private Date fechaCierre;
	private String clasificacion;
	@ManyToOne
	private Cliente duenio;

	public Fintech() {
		super();
	}

	public Fintech(Long iBAN, Long sWIFT, String estado, Date fechaApertura, Date fechaCierre, String clasificacion) {
		super(iBAN, sWIFT);		
		this.estado = estado;
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
		this.clasificacion = clasificacion;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	@Override
	public String toString() {
		return "Fintech [estado=" + estado + ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre
				+ ", clasificacion=" + clasificacion + ", getIBAN()=" + getIBAN() + ", getSWIFT()=" + getSWIFT()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clasificacion == null) ? 0 : clasificacion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaApertura == null) ? 0 : fechaApertura.hashCode());
		result = prime * result + ((fechaCierre == null) ? 0 : fechaCierre.hashCode());
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
		Fintech other = (Fintech) obj;
		if (clasificacion == null) {
			if (other.clasificacion != null)
				return false;
		} else if (!clasificacion.equals(other.clasificacion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaApertura == null) {
			if (other.fechaApertura != null)
				return false;
		} else if (!fechaApertura.equals(other.fechaApertura))
			return false;
		if (fechaCierre == null) {
			if (other.fechaCierre != null)
				return false;
		} else if (!fechaCierre.equals(other.fechaCierre))
			return false;
		return true;
	}
	
	
}
