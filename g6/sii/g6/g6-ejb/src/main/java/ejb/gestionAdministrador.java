package ejb;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.ClienteNoEncontradoException;

public interface gestionAdministrador {

	
	public void modificarDatosACliente(Cliente cliente) throws AdministracionException;
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AdministracionException;
	public void abrirCuentaPooled(Pooled pooled) throws AdministracionException;
	public void abrirCuentaSegregada(Segregada segregada) throws AdministracionException;
	public void cerrarCuentaPooled(Pooled pooled) throws AdministracionException;
	public void cerrarCuentaSegregada(Segregada segregada) throws AdministracionException;
}
