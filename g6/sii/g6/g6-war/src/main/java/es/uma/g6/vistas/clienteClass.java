package es.uma.g6.vistas;
import java.util.Date;

public class clienteClass{
    
    private String nombre;
    private String tipo;
    private String estado;
    private Date fecha_baja;
    private Date fecha_alta; 
    private String direccion; 
    private String ciudad;
    private String codigo_postal; 
    private String pais;
    private boolean Administrador;

    public clienteClass(){

    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public void setEstado(String estado){
        this.estado=estado;
    }

    public void setFechaBaja(Date fecha){
        this.fecha_baja=fecha;
    }

    public void setFechaAlta(Date fecha){
        this.fecha_alta=fecha;
    }

    public void setDireccion(String direccion){
        this.direccion=direccion;
    }

    public void setCiudad(String Ciudad){
        this.ciudad=Ciudad;
    }
    public void setCodigo_postal(String codigo_postal){
        this.codigo_postal=codigo_postal;
    }
    public void setPais(String pais){
        this.pais=pais;
    }
    public void setAdministrador(boolean administrador){
        this.Administrador=administrador;
    }



    public String getNombre(){
        return this.nombre;
    }
    public String getTipo(){
        return this.tipo;
    }
    public String getEstado(){
    	return this.estado;
    }
    public Date getFechaBaja(){
    	return this.fecha_baja;
    }
    public Date getFechaAlta(){
    	return this.fecha_alta;
    }
    public String getDireccion(){
    	return this.direccion;
    }
    public String getCodigoPostal(){
    	return this.codigo_postal;
    }
    public String getciudad(){
        return this.ciudad;
    }
    public String getPais(){
        return this.pais;
    }
}
