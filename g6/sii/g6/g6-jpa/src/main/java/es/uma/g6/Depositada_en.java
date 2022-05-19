package es.uma.g6;

import javax.persistence.*;

@Entity
public class Depositada_en {
	
	@EmbeddedId
	private Depositada_enId id;

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
		return id.getReferencia();
	}

	public void setReferencia(Referencia referencia) {
		this.id.setReferencia(referencia);
	}

	public Pooled getPooled() {
		return this.id.getPooled();
	}

	public void setPooled(Pooled pooled) {
		this.id.setPooled(pooled);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Float.floatToIntBits(saldo) != Float.floatToIntBits(other.saldo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Depositada_en [id=" + id + ", saldo=" + saldo + "]";
	}

	
	
}
