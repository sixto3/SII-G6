package es.uma.g6;

import com.sun.jndi.ldap.pool.Pool;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Depositada_en {


	@EmbeddedId
	private Depositada_enId id;

	@ManyToOne
	@MapsId("pooled_iban") //This is the name of attr in EmployerDeliveryAgentPK class
	@JoinColumn(name = "POOLED_IBAN")
	private Pooled pooled;

	@ManyToOne
	@MapsId("referencia_iban")
	@JoinColumn(name = "REFERENCIA_IBAN")
	private Referencia referencia;

	private float saldo;

	public Depositada_en(Depositada_enId id, Pooled pooled, Referencia referencia, float saldo) {
		this.id = id;
		this.pooled = pooled;
		this.referencia = referencia;
		this.saldo = saldo;
	}

	public Depositada_en(){


	}

	public Depositada_enId getId() {
		return id;
	}

	public void setId(Depositada_enId id) {
		this.id = id;
	}

	public Pooled getPooled() {
		return pooled;
	}

	public void setPooled(Pooled pooled) {
		this.pooled = pooled;
	}

	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Depositada_en that = (Depositada_en) o;
		return Float.compare(that.saldo, saldo) == 0 && id.equals(that.id) && pooled.equals(that.pooled) && referencia.equals(that.referencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pooled, referencia, saldo);
	}

	@Override
	public String toString() {
		return "Depositada_en{" +
				"id=" + id +
				", pooled=" + pooled +
				", referencia=" + referencia +
				", saldo=" + saldo +
				'}';
	}
}
