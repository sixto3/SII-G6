package es.uma.g6;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@IdClass(Depositada_en.Depositada_enId.class)
public class Depositada_en {
	public static class Depositada_enId implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Referencia referencia;
		private Pooled pooled;
		
		public Depositada_enId() {
			
		}
		public Depositada_enId(Referencia referencia, Pooled pooled) {
			super();
			this.referencia = referencia;
			this.pooled = pooled;
		}
		
		public Referencia getReferencia() {
			return referencia;
		}
		public void setReferencia(Referencia referencia) {
			this.referencia = referencia;
		}
		public Pooled getPooled() {
			return pooled;
		}
		public void setPooled(Pooled pooled) {
			this.pooled = pooled;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(pooled, referencia);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Depositada_enId other = (Depositada_enId) obj;
			return Objects.equals(pooled, other.pooled) && Objects.equals(referencia, other.referencia);
		}
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "REFERENCIA",nullable = false)
	private Referencia referencia;
	@Id
	@ManyToOne
	@JoinColumn(name = "CUENTA",nullable = false)
	private Pooled pooled;

	@Column(name = "Saldo", nullable = false)
	private float saldo;


	
	public Depositada_en(float saldo) {
		this.saldo = saldo;
	}
	
	public Depositada_en() {
		super();
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
	

	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	public Pooled getPooled() {
		return pooled;
	}

	public void setPooled(Pooled pooled) {
		this.pooled = pooled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pooled == null) ? 0 : pooled.hashCode());
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + Float.floatToIntBits(saldo);
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
		Depositada_en other = (Depositada_en) obj;
		if (pooled == null) {
			if (other.pooled != null)
				return false;
		} else if (!pooled.equals(other.pooled))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (Float.floatToIntBits(saldo) != Float.floatToIntBits(other.saldo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Depositada_en [saldo=" + saldo + "]";
	}
	
}
