package es.uma.g6;



import java.util.Date;
import java.util.Objects;
import java.util.List;
import javax.persistence.*;

@Entity
public class Autorizado extends Usuario{
	@Column(unique = true)
	private long identificacion;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	private String contrasenia;
	@Column(nullable=false)
	private String direccion;
	private Date fecha_nacimiento;
	private String estado;
	private Date fechaInicio;
	private Date fechaFin;
	@Column(nullable=false)
	private boolean bloqueado;
	@OneToMany(mappedBy = "autorizado")
	private List<Autorizacion> lista_empresas;
	
	public Autorizado(int id, long identificacion, String nombre, String apellido, String contraseña, String direccion,
			Date fecha_nacimiento, String estado, Date fechaInicio, Date fechaFin) {
		super(id);
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contraseña;
		this.direccion = direccion;
		this.fecha_nacimiento = fecha_nacimiento;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Autorizado() {

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

	public String getContraseña() {
		return contrasenia;
	}

	public void setContraseña(String contraseña) {
		this.contrasenia = contraseña;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(identificacion);
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
		Autorizado other = (Autorizado) obj;
		return identificacion == other.identificacion;
	}

	@Override
	public String toString() {
		return "Autorizado [identificacion=" + identificacion + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", contraseña=" + contrasenia + ", direccion=" + direccion + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
}
	
	
	
	
	
	
	
	

