package ejb;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.AutorizacionExistenteException;
import exceptions.AutorizadoExistenteException;
import exceptions.ClienteExistenteException;
import exceptions.ClienteNoEncontradoException;
import exceptions.ClienteNoValidoException;

public interface gestionAdministrador {

	
	public void modificarDatosACliente(Cliente cliente) throws AdministracionException;
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AdministracionException;
	public void abrirCuentaPooled(Pooled pooled) throws AdministracionException;
	public void abrirCuentaSegregada(Segregada segregada) throws AdministracionException;
	public void cerrarCuentaPooled(Pooled pooled) throws AdministracionException;
	public void cerrarCuentaSegregada(Segregada segregada) throws AdministracionException;
	public void altaCliente(Cliente cliente) throws ClienteExistenteException, ClienteNoValidoException;
	public void anadirAutorizadosCuentaPersonaJuridica(Autorizado autorizado, Cliente cliente, Autorizacion autorizacion)
			throws ClienteNoEncontradoException, AutorizadoExistenteException, AutorizacionExistenteException;
}