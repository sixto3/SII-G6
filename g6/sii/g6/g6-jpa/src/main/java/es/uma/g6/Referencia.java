package es.uma.g6;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Referencia extends Cuenta {
	@Column(nullable = false)
	private String nombreBanco;
	private String sucursal;
	private String pais;
	@Column(nullable = false)
	private float saldo;
	@Temporal(TemporalType.DATE)
	private Date fechaApertura;
	private String estado;
	@ManyToOne
	private Divisa divisas;
	@OneToMany(mappedBy = "referencia")
	private List<Depositada_en> depositada_referencia;
	
	
	
	public Referencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Referencia(Long iBAN, Long sWIFT, String nombreBanco, String sucursal, String pais, float saldo, Date fechaApertura, String estado,
			Divisa divisas, List<Depositada_en> depositada_referencia) {
		super(iBAN, sWIFT);
		this.nombreBanco = nombreBanco;
		this.sucursal = sucursal;
		this.pais = pais;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.estado = estado;
		this.divisas = divisas;
		this.depositada_referencia = depositada_referencia;
	}
	
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Divisa getDivisas() {
		return divisas;
	}
	public void setDivisas(Divisa divisas) {
		this.divisas = divisas;
	}
	public List<Depositada_en> getDepositada_referencia() {
		return depositada_referencia;
	}
	public void setDepositada_referencia(List<Depositada_en> depositada_referencia) {
		this.depositada_referencia = depositada_referencia;
	}
	@Override
	public String toString() {
		return "Referencia [nombreBanco=" + nombreBanco + ", sucursal=" + sucursal + ", pais=" + pais + ", saldo="
				+ saldo + ", fechaApertura=" + fechaApertura + ", estado=" + estado + ", divisas=" + divisas
				+ ", getIBAN()=" + getIBAN() + ", getSWIFT()=" + getSWIFT() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((divisas == null) ? 0 : divisas.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaApertura == null) ? 0 : fechaApertura.hashCode());
		result = prime * result + ((nombreBanco == null) ? 0 : nombreBanco.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		long temp;
		temp = Double.doubleToLongBits(saldo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
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
		Referencia other = (Referencia) obj;
		if (divisas == null) {
			if (other.divisas != null)
				return false;
		} else if (!divisas.equals(other.divisas))
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
		if (nombreBanco == null) {
			if (other.nombreBanco != null)
				return false;
		} else if (!nombreBanco.equals(other.nombreBanco))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		return true;
	}
	
	
}
