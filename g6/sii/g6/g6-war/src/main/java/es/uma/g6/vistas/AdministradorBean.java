package es.uma.g6.vistas;

import java.util.List;

import es.uma.g6.Cliente;


public class AdministradorBean {
	
    private Cliente cl;
    private String clElegido;

    public AdministradorBean(){
        cl = new Cliente();
    }
    public Cliente getCliente(){
        
    	return cl;
    }

    public String getClienteElegido() {
		return clElegido;
	}

	public void setClElegido(String contactoElegido) {
		this.clElegido = clElegido;
	}

    public void eliminar(Cliente c) {
		
	}

    public void aniadirCliente() {
		
		cl = new Cliente(); 
	}
    public List<Cliente> getTodosClientes() {
		return null;
	}
}
