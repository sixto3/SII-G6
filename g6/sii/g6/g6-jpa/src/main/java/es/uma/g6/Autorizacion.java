package es.uma.g6;



import javax.persistence.*;
@Entity
public class Autorizacion {
	
	
	
	@EmbeddedId
	private AutorizacionId id;
	
	private String tipo;
	private boolean bloqueado;


	
	public Autorizacion(Empresa empresa, Autorizado autorizado, String tipo, boolean estado) {
		super();
		this.id.empresa = empresa;
		this.id.autorizado = autorizado;
		this.tipo = tipo;
		this.bloqueado = estado;
	}

	public Autorizacion() {

	}
	
	

	public boolean isBloqueado() {
		return bloqueado;
	}

	public AutorizacionId getId() {
		return id;
	}

	public void setId(AutorizacionId id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bloqueado ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Autorizacion other = (Autorizacion) obj;
		if (bloqueado != other.bloqueado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autorizacion [id=" + id + ", tipo=" + tipo + ", bloqueado=" + bloqueado + "]";
	}

	

	

	
	
	
	
	
	
}
