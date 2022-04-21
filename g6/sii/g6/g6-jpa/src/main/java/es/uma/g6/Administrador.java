package es.uma.g6;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    @Column(name= "Identificacion", unique = true, nullable = false)
    private int Identificacion;

    @Column(name= "Nombre", nullable = false)
    private int Nombre;


    public Administrador(int id, int contraseña, int identificacion, int nombre) {
        super(id, contraseña);
        Identificacion = identificacion;
        Nombre = nombre;
    }

    public Administrador(int identificacion, int nombre) {
        Identificacion = identificacion;
        Nombre = nombre;
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



    @Override
    public String toString() {
        return "Administrador{" +
                "Identificacion=" + Identificacion +
                ", Nombre=" + Nombre +
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
