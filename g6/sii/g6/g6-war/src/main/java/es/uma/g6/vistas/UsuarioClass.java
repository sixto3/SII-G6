package es.uma.g6.vistas;
public class UsuarioClass {
    
    private String nombre;
    private String password;
    private boolean admnistrador;

    public String getNombre(){
        return nombre;
    }

    public String getPassword(){
        return password;
    }

    public boolean getAdministrador(){
        return admnistrador;
    }

    public void setnombre(String nombre){
        this.nombre = nombre;
    } 

    public void setPasswrod(String password){
        this.password = password;
    }

    public void setAdministrador(boolean administrador){
        this.admnistrador = administrador;
    }
}
