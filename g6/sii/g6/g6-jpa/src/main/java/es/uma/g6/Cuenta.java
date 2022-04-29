package es.uma.g6;


import java.util.List;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta {

	@Id @GeneratedValue @Column(nullable = false)
	private Long IBAN;
	private Long SWIFT;
	
	@OneToMany (mappedBy = "origen")
	private List<Transaccion> transacciones_origen;
	
	@OneToMany (mappedBy = "destino")
	private List<Transaccion> transacciones_destino;

	public Cuenta() {
		super();
	}

	public Cuenta(Long iBAN, Long sWIFT) {
		IBAN = iBAN;
		SWIFT = sWIFT;
	}

	public Long getIBAN() {
		return this.IBAN;
	}

	public void setIBAN(Long id) {
		this.IBAN = id;
	}

	public Long getSWIFT() {
		return SWIFT;
	}

	public void setSWIFT(Long sWIFT) {
		SWIFT = sWIFT;
	}
	
	

	public List<Transaccion> getTransacciones_origen() {
		return transacciones_origen;
	}

	public void setTransacciones_origen(List<Transaccion> transacciones_origen) {
		this.transacciones_origen = transacciones_origen;
	}

	public List<Transaccion> getTransacciones_destino() {
		return transacciones_destino;
	}

	public void setTransacciones_destino(List<Transaccion> transacciones_destino) {
		this.transacciones_destino = transacciones_destino;
	}

	@Override
	public String toString() {
		return "Cuenta [IBAN=" + IBAN + ", SWIFT=" + SWIFT + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IBAN == null) ? 0 : IBAN.hashCode());
		result = prime * result + ((SWIFT == null) ? 0 : SWIFT.hashCode());
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
		Cuenta other = (Cuenta) obj;
		if (IBAN == null) {
			if (other.IBAN != null)
				return false;
		} else if (!IBAN.equals(other.IBAN))
			return false;
		if (SWIFT == null) {
			if (other.SWIFT != null)
				return false;
		} else if (!SWIFT.equals(other.SWIFT))
			return false;
		return true;
	}
	
	

}