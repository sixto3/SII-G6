package es.uma.g6;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * @author Juan Ignacio
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {


    @Id
    @GeneratedValue
    private int Id;

    @Column(name= "Identificacion", unique = true, nullable = false)
    private String Identificacion;

    @Column(name= "Tipo_Cliente", nullable = false)
    private String Tipo_Cliente;

    @Column(name= "Estado", nullable = false)
    private String Estado;

    @Column(name= "Fecha_Alta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date Fecha_Alta;

    @Temporal(TemporalType.DATE)
    private Date Fecha_Baja;

    @Column(name= "Direccion", nullable = false)
    private String Direccion;

    @Column(name= "Codigo_Postal", nullable = false)
    private String Codigo_Postal;

    @Column(name= "Ciudad", nullable = false)
    private String Ciudad;

    @Column(name= "Pais", nullable = false)
    private String País;
    
    @Column (nullable = false)
    private boolean bloqueado;
    
    @OneToMany (mappedBy = "duenio")
    private List<Fintech> cuentas_fintech;

    @OneToOne
    private Usuario usuario;

    public Cliente(int id, String identificacion, String tipo_Cliente, String estado,
                   Date fecha_Alta, Date fecha_Baja, String direccion,
                   String codigo_Postal, String ciudad, String país, boolean bloqueado) {
        Id = id;
        Identificacion = identificacion;
        Tipo_Cliente = tipo_Cliente;
        Estado = estado;
        Fecha_Alta = fecha_Alta;
        Fecha_Baja = fecha_Baja;
        Direccion = direccion;
        Codigo_Postal = codigo_Postal;
        Ciudad = ciudad;
        País = país;
        this.bloqueado = bloqueado;
    }

    public Cliente() {

    }

    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	


    public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }

    public String getTipo_Cliente() {
        return Tipo_Cliente;
    }

    public void setTipo_Cliente(String tipo_Cliente) {
        Tipo_Cliente = tipo_Cliente;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Date getFecha_Alta() {
        return Fecha_Alta;
    }

    public void setFecha_Alta(Date fecha_Alta) {
        Fecha_Alta = fecha_Alta;
    }

    public Date getFecha_Baja() {
        return Fecha_Baja;
    }

    public void setFecha_Baja(Date fecha_Baja) {
        Fecha_Baja = fecha_Baja;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) {
        Codigo_Postal = codigo_Postal;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getPaís() {
        return País;
    }

    public void setPaís(String país) {
        País = país;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public List<Fintech> getCuentas_fintech() {
        return cuentas_fintech;
    }

    public void setCuentas_fintech(List<Fintech> cuentas_fintech) {
        this.cuentas_fintech = cuentas_fintech;
    }

   

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Identificacion == null) ? 0 : Identificacion.hashCode());
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
		Cliente other = (Cliente) obj;
		if (Identificacion == null) {
			if (other.Identificacion != null)
				return false;
		} else if (!Identificacion.equals(other.Identificacion))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Cliente{" +
                "Identificacion=" + Identificacion +
                ", Tipo_Cliente='" + Tipo_Cliente + '\'' +
                ", Estado='" + Estado + '\'' +
                ", Fecha_Alta=" + Fecha_Alta +
                ", Fecha_Baja=" + Fecha_Baja +
                ", Direccion='" + Direccion + '\'' +
                ", Codigo_Postal=" + Codigo_Postal +
                ", Ciudad='" + Ciudad + '\'' +
                ", País='" + País + '\'' +
                ", bloqueado=" + bloqueado +
                '}';
    }
}


