package es.uma.g6.vistas;

import java.util.List;

import javax.inject.Inject;

import es.uma.g6.Cliente;
import es.uma.g6.ejb.gestionAdministrador;
import exceptions.AdministracionException;
import exceptions.ClienteExistenteException;
import exceptions.ClienteNoValidoException;


public class AdministradorBean {
	@Inject
	private gestionAdministrador ga;
	@Inject
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
    public void desbloquearCliente(String identificador) {
    	if(cl.getIdentificacion().compareToIgnoreCase(identificador)==0) {
    		if(cl.isBloqueado()){
			try {
				ga.desbloquearCliente(cl);
			} catch (AdministracionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	}
		
    	
	}
    
    public void eliminarCliente(Cliente c) {
    	try {
			ga.bajaCliente(c);
		} catch (AdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void aniadirCliente(Cliente c) {
    	try {
			ga.altaCliente(c);
		} catch (ClienteExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
