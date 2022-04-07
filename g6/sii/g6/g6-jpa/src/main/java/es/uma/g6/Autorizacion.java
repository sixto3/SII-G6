package es.uma.g6;


import java.util.Objects;

import javax.persistence.*;
@Entity
public class Autorizacion {

	@Id
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID",nullable = false)
	private Empresa empresa;

	@Id
	@ManyToOne
	@JoinColumn(name = "AUTORIZADO_ID", nullable = false)
	private Autorizado autorizado;
	
	private String tipo;


	public Autorizacion(Empresa empresa, Autorizado autorizado, String tipo) {
		super();
		this.empresa = empresa;
		this.autorizado = autorizado;
		this.tipo = tipo;
	}

	public Autorizacion() {

	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.autorizado = autorizado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorizado, empresa, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autorizacion other = (Autorizacion) obj;
		return Objects.equals(autorizado, other.autorizado) && Objects.equals(empresa, other.empresa)
				&& Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Autorizacion [empresa=" + empresa + ", autorizado=" + autorizado + ", tipo=" + tipo + "]";
	}

	
	
	
	
	
}
