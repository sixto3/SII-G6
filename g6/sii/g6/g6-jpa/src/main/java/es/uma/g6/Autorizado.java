package es.uma.g6;



import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Autorizado {
	@Column(unique = true)
	private long identificacion;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	private String direccion;
	private Date fecha_nacimiento;
	@Column(nullable=false)
	private String estado;
	private Date fechaInicio;
	private Date fechaFin;
	@Column(nullable=false)
	private boolean bloqueado;
	@OneToMany(mappedBy = "autorizado")
	private List<Autorizacion> lista_empresas;

	@Id
	@JoinColumn(name = "ID")
	@OneToOne
	private Usuario usuarioAut;


	



	public Autorizado(long identificacion, String nombre, String apellido, String direccion, Date fecha_nacimiento,
			String estado, Date fechaInicio, Date fechaFin, boolean bloqueado) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fecha_nacimiento = fecha_nacimiento;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.bloqueado = bloqueado;
	}

	public Autorizado() {

	}

	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public List<Autorizacion> getLista_empresas() {
		return lista_empresas;
	}

	public void setLista_empresas(List<Autorizacion> lista_empresas) {
		this.lista_empresas = lista_empresas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Autorizado that = (Autorizado) o;

		return identificacion == that.identificacion;
	}

	@Override
	public int hashCode() {
		return (int) (identificacion ^ (identificacion >>> 32));
	}

	@Override
	public String toString() {
		return "Autorizado [identificacion=" + identificacion + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", direccion=" + direccion + ", fecha_nacimiento=" + fecha_nacimiento + ", estado=" + estado
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", bloqueado=" + bloqueado + "]";
	}

	
}
	
	
	
	
	
	
	
	

