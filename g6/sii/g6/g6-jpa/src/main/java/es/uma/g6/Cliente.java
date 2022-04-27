package es.uma.g6;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity

public class Cliente {

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

    @Id
    @JoinColumn(name = "ID")
    @OneToOne
    private Usuario usuario;

    public Cliente(int identificacion, String tipo_Cliente, String estado, Date fecha_Alta, Date fecha_Baja,
                   String direccion, int codigo_Postal, String ciudad, String país, boolean bloqueado) {
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

    public int getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(int identificacion) {
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

    public int getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(int codigo_Postal) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return Identificacion == cliente.Identificacion;
    }

    @Override
    public int hashCode() {
        return Identificacion;
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


