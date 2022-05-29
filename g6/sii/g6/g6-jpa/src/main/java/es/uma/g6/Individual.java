package es.uma.g6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Individual extends Cliente{



    @Column(name= "Nombre", nullable = false)
    private String Nombre;

    @Column(name= "Apellido", nullable = false)
    private String Apellido;




    private Date Fecha_Nacimiento;

   

    public Individual(int id, String identificacion, String tipo_Cliente, String estado, java.util.Date fecha_Alta,
			java.util.Date fecha_Baja, String direccion, String codigo_Postal, String ciudad, String país,
			boolean bloqueado, String nombre, String apellido, Date fecha_Nacimiento) {
		super(id, identificacion, tipo_Cliente, estado, fecha_Alta, fecha_Baja, direccion, codigo_Postal, ciudad, país,
				bloqueado);
		Nombre = nombre;
		Apellido = apellido;
		Fecha_Nacimiento = fecha_Nacimiento;
	}

	public Individual(String nombre, String apellido, Date fecha_Nacimiento) {
        Nombre = nombre;
        Apellido = apellido;
        Fecha_Nacimiento = fecha_Nacimiento;
    }

    public Individual() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }


    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
    }
    
    

    @Override
	public int hashCode() {
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individual other = (Individual) obj;
		return Objects.equals(Apellido, other.Apellido) && Objects.equals(Nombre, other.Nombre);
	}


    @Override
    public String toString() {
        return "Individual{" +
                "Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Fecha_Nacimiento=" + Fecha_Nacimiento +
                '}';
    }
}
