package es.uma.g6;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Divisa/* implements Serializable*/{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String abreviatura;
	@Column(name="nombre", nullable =false)
	private String nombre;
	private char simbolo;
	@Column(name="CambioEuro", nullable=false)
	private float cambioEuro;
	@OneToMany(mappedBy="emisor")
	private List<Transaccion> transaccion_emisor;
	@OneToMany(mappedBy="receptor")
	private List<Transaccion> transaccion_receptor;
	@OneToMany(mappedBy="divisas")
	private List<Referencia> referencia_divisas;
	
	
	public Divisa(String abreviatura, String nombre, char simbolo, float cambioEuro) {
		
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.cambioEuro = cambioEuro;
	}
	
	public Divisa() {
		super();
	}
	
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public char getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	public float getCambioEuro() {
		return cambioEuro;
	}
	public void setCambioEuro(float cambioEuro) {
		this.cambioEuro = cambioEuro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreviatura == null) ? 0 : abreviatura.hashCode());
		result = prime * result + Float.floatToIntBits(cambioEuro);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((referencia_divisas == null) ? 0 : referencia_divisas.hashCode());
		result = prime * result + simbolo;
		result = prime * result + ((transaccion_emisor == null) ? 0 : transaccion_emisor.hashCode());
		result = prime * result + ((transaccion_receptor == null) ? 0 : transaccion_receptor.hashCode());
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
		Divisa other = (Divisa) obj;
		if (abreviatura == null) {
			if (other.abreviatura != null)
				return false;
		} else if (!abreviatura.equals(other.abreviatura))
			return false;
		if (Float.floatToIntBits(cambioEuro) != Float.floatToIntBits(other.cambioEuro))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (referencia_divisas == null) {
			if (other.referencia_divisas != null)
				return false;
		} else if (!referencia_divisas.equals(other.referencia_divisas))
			return false;
		if (simbolo != other.simbolo)
			return false;
		if (transaccion_emisor == null) {
			if (other.transaccion_emisor != null)
				return false;
		} else if (!transaccion_emisor.equals(other.transaccion_emisor))
			return false;
		if (transaccion_receptor == null) {
			if (other.transaccion_receptor != null)
				return false;
		} else if (!transaccion_receptor.equals(other.transaccion_receptor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "abreviatura: "+this.abreviatura+" nombre: "+this.nombre+" simbolo: "+this.simbolo+" cambio_a_euro: "+cambioEuro;
		
	}
	
}