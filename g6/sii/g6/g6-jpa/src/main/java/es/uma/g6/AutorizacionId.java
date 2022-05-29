package es.uma.g6;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class AutorizacionId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int autorizado_id;

	private int empresa_id;


	public AutorizacionId(int autorizado_id, int empresa_id) {
		this.autorizado_id = autorizado_id;
		this.empresa_id = empresa_id;
	}

	public AutorizacionId() {
		
	}

	public int getAutorizado_id() {
		return autorizado_id;
	}

	public void setAutorizado_id(int autorizado_id) {
		this.autorizado_id = autorizado_id;
	}

	public int getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + autorizado_id;
		result = prime * result + empresa_id;
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
		if (autorizado_id != other.autorizado_id)
			return false;
		if (empresa_id != other.empresa_id)
			return false;
		return true;
	}

	
}
