package ejb;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.ClienteNoEncontradoException;

public interface gestionAdministrador {

	
	public void modificarDatosACliente(Cliente cliente) throws AdministracionException;
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AdministracionException;
	
}
