package es.uma.g6;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import es.uma.g6.DepositadaEnId;
@Embeddable
public class DepositadaEnId implements Serializable{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	private String pooled_iban;

	private String referencia_iban;



	public DepositadaEnId() {
			
		}

	public DepositadaEnId(String pooled_iban, String referencia_iban) {
		this.pooled_iban = pooled_iban;
		this.referencia_iban = referencia_iban;
	}

	public String getPooled_iban() {
		return pooled_iban;
	}

	public void setPooled_iban(String pooled_iban) {
		this.pooled_iban = pooled_iban;
	}

	public String getReferencia_iban() {
		return referencia_iban;
	}

	public void setReferencia_iban(String referencia_iban) {
		this.referencia_iban = referencia_iban;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pooled_iban == null) ? 0 : pooled_iban.hashCode());
		result = prime * result + ((referencia_iban == null) ? 0 : referencia_iban.hashCode());
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
		DepositadaEnId other = (DepositadaEnId) obj;
		if (pooled_iban == null) {
			if (other.pooled_iban != null)
				return false;
		} else if (!pooled_iban.equals(other.pooled_iban))
			return false;
		if (referencia_iban == null) {
			if (other.referencia_iban != null)
				return false;
		} else if (!referencia_iban.equals(other.referencia_iban))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Depositada_enId{" +
				"pooled_iban='" + pooled_iban + '\'' +
				", referencia_iban=" + referencia_iban +
				'}';
	}
}
	

