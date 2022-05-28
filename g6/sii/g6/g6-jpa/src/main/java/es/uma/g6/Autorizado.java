package es.uma.g6;



import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Autorizado {

	@Id
	@GeneratedValue
	private int Id;

	@Column(unique = true)
	private String identificacion;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	private String direccion;
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	@Column(nullable=false)
	private String estado;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	@Column(nullable=false)
	private boolean bloqueado;

	@OneToMany(mappedBy = "autorizado")
	private List<Autorizacion> lista_empresas;


	@OneToOne
	private Usuario usuarioAut;


	public Autorizado(String identificacion, String nombre, String apellido, String direccion, Date fecha_nacimiento,
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

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
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

	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Usuario getUsuarioAut() {
		return usuarioAut;
	}

	public void setUsuarioAut(Usuario usuarioAut) {
		this.usuarioAut = usuarioAut;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificacion == null) ? 0 : identificacion.hashCode());
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
		Autorizado other = (Autorizado) obj;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autorizado [identificacion=" + identificacion + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", direccion=" + direccion + ", fecha_nacimiento=" + fecha_nacimiento + ", estado=" + estado
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", bloqueado=" + bloqueado + "]";
	}

	
}
	
	
	
	
	
	
	
	

