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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AutorizacionId that = (AutorizacionId) o;
		return autorizado_id == that.autorizado_id && empresa_id == that.empresa_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorizado_id, empresa_id);
	}
}
