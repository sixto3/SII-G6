package es.uma.g6;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    @Column(name= "Identificacion", unique = true, nullable = false)
    private int Identificacion;

    @Column(name= "Nombre", nullable = false)
    private int Nombre;

    @Column(name= "Contrasenia", nullable = false)
    private int Contraseña;

    public Administrador(int id, int identificacion, int nombre, int contraseña) {
        super(id);
        Identificacion = identificacion;
        Nombre = nombre;
        Contraseña = contraseña;
    }

    public Administrador() {
        super();
    }


    public int getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(int identificacion) {
        Identificacion = identificacion;
    }

    public int getNombre() {
        return Nombre;
    }

    public void setNombre(int nombre) {
        Nombre = nombre;
    }

    public int getContraseña() {
        return Contraseña;
    }

    public void setContraseña(int contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "Identificacion=" + Identificacion +
                ", Nombre=" + Nombre +
                ", Contraseña=" + Contraseña +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Administrador that = (Administrador) o;

        return Identificacion == that.Identificacion;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Identificacion;
        return result;
    }
}
