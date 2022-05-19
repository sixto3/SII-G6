package es.uma.g6;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class AutorizacionId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	Empresa empresa;
	@ManyToOne
	Autorizado autorizado;
	
	public AutorizacionId() {
		
	}

	public AutorizacionId(Empresa empresa, Autorizado autorizado) {
		super();
		this.empresa = empresa;
		this.autorizado = autorizado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.autorizado = autorizado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autorizado == null) ? 0 : autorizado.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
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
		AutorizacionId other = (AutorizacionId) obj;
		if (autorizado == null) {
			if (other.autorizado != null)
				return false;
		} else if (!autorizado.equals(other.autorizado))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AutorizacionId [empresa=" + empresa + ", autorizado=" + autorizado + "]";
	}

	

	


	
}
