package es.uma.g6;



import javax.persistence.*;
import java.util.Objects;

@Entity
public class Autorizacion {


	@EmbeddedId
	private AutorizacionId id;


	@ManyToOne
	@MapsId("autorizado_id") //This is the name of attr in EmployerDeliveryAgentPK class
	@JoinColumn(name = "AUTORIZADO_ID")
	private Autorizado autorizado;

	@ManyToOne
	@MapsId("empresa_id")
	@JoinColumn(name = "EMPRESA_ID")
	private Empresa empresa;

	public Autorizacion(AutorizacionId id, Autorizado autorizado, Empresa empresa) {
		this.id = id;
		this.autorizado = autorizado;
		this.empresa = empresa;
	}

	public Autorizacion() {

	}

	public AutorizacionId getId() {
		return id;
	}

	public void setId(AutorizacionId id) {
		this.id = id;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.autorizado = autorizado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Autorizacion that = (Autorizacion) o;
		return id.equals(that.id) && autorizado.equals(that.autorizado) && empresa.equals(that.empresa);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, autorizado, empresa);
	}

	@Override
	public String toString() {
		return "Autorizacion{" +
				"id=" + id +
				", autorizado=" + autorizado +
				", empresa=" + empresa +
				'}';
	}
}
	

	

	
	
	
	
	
	

