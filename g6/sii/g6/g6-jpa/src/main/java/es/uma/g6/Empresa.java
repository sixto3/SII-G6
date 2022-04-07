package es.uma.g6;


import java.sql.Date;
import java.util.List;


import javax.persistence.*;

	@Entity
public class Empresa extends Cliente{
	@Column(nullable=false)
	private String razon_social;
	
	@OneToMany (mappedBy = "empresa")
	private List<Autorizacion> lista_autorizados;

	

	public Empresa(int id, int identificacion, String tipo_Cliente, String estado, Date fecha_Alta, Date fecha_Baja,
		String direccion, int codigo_Postal, String ciudad, String país, boolean bloqueado,
		List<Fintech> cuentas_fintech, String razon_social, List<Autorizacion> lista_autorizados) {
		super(id, identificacion, tipo_Cliente, estado, fecha_Alta, fecha_Baja, direccion, codigo_Postal, ciudad, país,
				bloqueado, cuentas_fintech);
		this.razon_social = razon_social;
		this.lista_autorizados = lista_autorizados;
	}

	public Empresa() {

	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (razon_social != null ? razon_social.hashCode() : 0);
		result = 31 * result + (lista_autorizados != null ? lista_autorizados.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Empresa empresa = (Empresa) o;

		if (razon_social != null ? !razon_social.equals(empresa.razon_social) : empresa.razon_social != null)
			return false;
		return lista_autorizados != null ? lista_autorizados.equals(empresa.lista_autorizados) : empresa.lista_autorizados == null;
	}

	@Override
	public String toString() {
		return "Empresa [razon_social=" + razon_social + ", lista_autorizados=" + lista_autorizados
				+ ", getIdentificacion()=" + getIdentificacion() + ", getTipo_Cliente()=" + getTipo_Cliente()
				+ ", getEstado()=" + getEstado() + ", getFecha_Alta()=" + getFecha_Alta() + ", getFecha_Baja()="
				+ getFecha_Baja() + ", getDireccion()=" + getDireccion() + ", getCodigo_Postal()=" + getCodigo_Postal()
				+ ", getCiudad()=" + getCiudad() + ", getPaís()=" + getPaís() + ", isBloqueado()=" + isBloqueado()
				+ ", toString()=" + super.toString() + ", getId()=" + getId() + ", getClass()=" + getClass() + "]";
	}

	
	
	
}
	
	
	

