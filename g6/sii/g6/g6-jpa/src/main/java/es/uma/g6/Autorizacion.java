package es.uma.g6;


import java.util.Objects;
import java.io.Serializable;
import javax.persistence.*;
@Entity
@IdClass(Autorizacion.AutorizacionId.class)
public class Autorizacion {
	public static class AutorizacionId implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Empresa empresa;
		private Autorizado autorizado;
		
		public AutorizacionId() {
			
		}

		public AutorizacionId(Empresa empresa, Autorizado autorizado) {
			super();
			this.empresa = empresa;
			this.autorizado = autorizado;
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

		@Override
		public int hashCode() {
			return Objects.hash(autorizado, empresa);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AutorizacionId other = (AutorizacionId) obj;
			return Objects.equals(autorizado, other.autorizado) && Objects.equals(empresa, other.empresa);
		}
		
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID",nullable = false)
	private Empresa empresa;

	@Id
	@ManyToOne
	@JoinColumn(name = "AUTORIZADO_ID", nullable = false, referencedColumnName = "usuarioAut")
	private Autorizado autorizado;
	
	private String tipo;
	private boolean bloqueado;


	
	public Autorizacion(Empresa empresa, Autorizado autorizado, String tipo, boolean estado) {
		super();
		this.empresa = empresa;
		this.autorizado = autorizado;
		this.tipo = tipo;
		this.bloqueado = estado;
	}

	public Autorizacion() {

	}
	
	

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
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
		return "Autorizacion [empresa=" + empresa + ", autorizado=" + autorizado + ", tipo=" + tipo + ", bloqueado="
				+ bloqueado + "]";
	}

	
	
	
	
	
	
}
