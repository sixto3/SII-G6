package es.uma.g6;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column(name = "Nombre", unique = true, nullable = false)
    private String Nombre;

    @Column(name = "Contrasenia", nullable = false)
    private int Contraseña;

    @Column(name = "Administrador", nullable = false)
    private boolean Administrador;

    @OneToOne(mappedBy = "usuarioAut")
    private Autorizado autorizado;

    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;




    public Usuario(int id, String nombre, int contraseña, boolean administrador) {
        this.id = id;
        Nombre = nombre;
        Contraseña = contraseña;
        Administrador = administrador;
    }

    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getContraseña() {
        return Contraseña;
    }

    public void setContraseña(int contraseña) {
        Contraseña = contraseña;
    }

    public boolean isAdministrador() {
        return Administrador;
    }

    public void setAdministrador(boolean administrador) {
        Administrador = administrador;
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
                ", Nombre='" + Nombre + '\'' +
                ", Contraseña=" + Contraseña +
                ", Administrador=" + Administrador +
                '}';
    }
}



