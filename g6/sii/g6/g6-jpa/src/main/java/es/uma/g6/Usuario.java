package es.uma.g6;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id @GeneratedValue
    @Column
    private int id;

    @Column(name= "Contrasenia",unique = true, nullable = false)
    private int Contraseña;


    public Usuario(int id, int contraseña) {
        this.id = id;
        Contraseña = contraseña;
    }

    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContraseña() {
        return Contraseña;
    }

    public void setContraseña(int contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Contraseña=" + Contraseña +
                '}';
    }
}

