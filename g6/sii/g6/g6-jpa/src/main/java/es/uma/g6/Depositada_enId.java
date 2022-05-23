package es.uma.g6;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import es.uma.g6.Depositada_enId;
@Embeddable
public class Depositada_enId implements Serializable{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	@Column(name = "POOLED_IBAN")
	private String pooled_iban;

	@Column(name = "REFERENCIA_IBAN")
	private int referencia_iban;



	public Depositada_enId() {
			
		}

	public Depositada_enId(String pooled_iban, int referencia_iban) {
		this.pooled_iban = pooled_iban;
		this.referencia_iban = referencia_iban;
	}

	public String getPooled_iban() {
		return pooled_iban;
	}

	public void setPooled_iban(String pooled_iban) {
		this.pooled_iban = pooled_iban;
	}

	public int getReferencia_iban() {
		return referencia_iban;
	}

	public void setReferencia_iban(int referencia_iban) {
		this.referencia_iban = referencia_iban;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Depositada_enId that = (Depositada_enId) o;
		return referencia_iban == that.referencia_iban && pooled_iban.equals(that.pooled_iban);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pooled_iban, referencia_iban);
	}

	@Override
	public String toString() {
		return "Depositada_enId{" +
				"pooled_iban='" + pooled_iban + '\'' +
				", referencia_iban=" + referencia_iban +
				'}';
	}
}
	

