package es.uma.g6;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Cliente extends Usuario{

    @Column(name= "Identificacion", unique = true, nullable = false)
    private int Identificacion;

    @Column(name= "Tipo_Cliente", nullable = false)
    private String Tipo_Cliente;

    @Column(name= "Estado", nullable = false)
    private String Estado;

    @Column(name= "Fecha_Alta", nullable = false)
    private Date Fecha_Alta;

    private Date Fecha_Baja;

    @Column(name= "Direccion", nullable = false)
    private String Direccion;

    @Column(name= "Codigo_Postal", nullable = false)
    private int Codigo_Postal;

    @Column(name= "Ciudad", nullable = false)
    private String Ciudad;

    @Column(name= "Pais", nullable = false)
    private String País;
    
    @Column (nullable = false)
    private boolean bloqueado;
    
    @OneToMany (mappedBy = "duenio")
    private List<Fintech> cuentas_fintech;
    
 
    public Cliente(int id, int identificacion, String tipo_Cliente, String estado, Date fecha_Alta, Date fecha_Baja,
			String direccion, int codigo_Postal, String ciudad, String país, boolean bloqueado,
			List<Fintech> cuentas_fintech) {
		super(id);
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
		this.cuentas_fintech = cuentas_fintech;
	}

	public Cliente() {

    }

    public int getIdentificacion() {
        return Identificacion;
    }

    public String getTipo_Cliente() {
        return Tipo_Cliente;
    }

    public String getEstado() {
        return Estado;
    }

    public Date getFecha_Alta() {
        return Fecha_Alta;
    }

    public Date getFecha_Baja() {
        return Fecha_Baja;
    }

    public String getDireccion() {
        return Direccion;
    }

    public int getCodigo_Postal() {
        return Codigo_Postal;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getPaís() {
        return País;
    }

    public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	

    @Override
	public String toString() {
		return "Cliente [Identificacion=" + Identificacion + ", Tipo_Cliente=" + Tipo_Cliente + ", Estado=" + Estado
				+ ", Fecha_Alta=" + Fecha_Alta + ", Fecha_Baja=" + Fecha_Baja + ", Direccion=" + Direccion
				+ ", Codigo_Postal=" + Codigo_Postal + ", Ciudad=" + Ciudad + ", País=" + País + ", bloqueado="
				+ bloqueado + ", cuentas_fintech=" + cuentas_fintech + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(Identificacion);
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
		Cliente other = (Cliente) obj;
		return Identificacion == other.Identificacion;
	}


}


