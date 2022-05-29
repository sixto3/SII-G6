package es.uma.g6;


import java.util.Date;
import java.util.List;


import javax.persistence.*;

	@Entity
public class Empresa extends Cliente{

	@Column(nullable=false)
	private String razon_social;
	
	@OneToMany (mappedBy = "empresa")
	private List<Autorizacion> lista_autorizados;


		public Empresa(int id, String identificacion, String tipo_Cliente, String estado, Date fecha_Alta, Date fecha_Baja,
					   String direccion, String codigo_Postal, String ciudad, String país, boolean bloqueado, String razon_social) {
			super(id, identificacion, tipo_Cliente, estado, fecha_Alta, fecha_Baja, direccion,
					codigo_Postal, ciudad, país, bloqueado);
			this.razon_social = razon_social;
		}

		public Empresa(String razon_social) {
			this.razon_social = razon_social;
		}

		public Empresa() {

		}

		public String getRazon_social() {
			return razon_social;
		}

		public void setRazon_social(String razon_social) {
			this.razon_social = razon_social;
		}

		public List<Autorizacion> getLista_autorizados() {
			return lista_autorizados;
		}

		public void setLista_autorizados(List<Autorizacion> lista_autorizados) {
			this.lista_autorizados = lista_autorizados;
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

			return razon_social != null ? razon_social.equals(empresa.razon_social) : empresa.razon_social == null;
		}

		@Override
		public String toString() {
			return "Empresa{" +
					"razon_social='" + razon_social + '\'' +
					'}';
		}
	}
	
	
	

