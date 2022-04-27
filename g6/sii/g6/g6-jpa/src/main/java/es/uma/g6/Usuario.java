package es.uma.g6;

import java.util.Objects;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @Column(name = "Nombre",nullable = false)
    private String Nombre;

    @Column(name = "Contrasenia", nullable = false)
    private int Contraseña;

    @Column(name = "Administrador", nullable = false)
    private boolean Administrador;

    @OneToOne(mappedBy = "usuarioAut")
    private Autorizado autorizado;

	@OneToOne(mappedBy = "usuario")
    private Cliente cliente;


    public Usuario(String nombre, int contraseña, boolean administrador, Autorizado autorizado, Cliente cliente) {
		super();
		Nombre = nombre;
		Contraseña = contraseña;
		Administrador = administrador;
		this.autorizado = autorizado;
		this.cliente = cliente;
	}


	public Usuario() {

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
    
    public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.autorizado = autorizado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(Nombre, other.Nombre);
	}

	@Override
	public String toString() {
		return "Usuario [Nombre=" + Nombre + ", Contraseña=" + Contraseña + ", Administrador=" + Administrador
				+ ", autorizado=" + autorizado + ", cliente=" + cliente + "]";
	}

   
}



