package ejb;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.AutorizacionExistenteException;
import exceptions.AutorizadoExistenteException;
import exceptions.ClienteExistenteException;
import exceptions.ClienteNoEncontradoException;
import exceptions.ClienteNoValidoException;
import exceptions.CuentaNoEncontradaException;
import exceptions.FaltaDeFondosException;
public interface gestionAdministrador {

	
	public void modificarDatosACliente(Cliente cliente) throws AdministracionException;
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AdministracionException;
	public void abrirCuentaPooled(Pooled pooled) throws AdministracionException;
	public void abrirCuentaSegregada(Segregada segregada) throws AdministracionException;
	public void cerrarCuentaPooled(Pooled pooled) throws AdministracionException;
	public void cerrarCuentaSegregada(Segregada segregada) throws AdministracionException;
	public void altaCliente(Cliente cliente) throws ClienteExistenteException, ClienteNoValidoException;
	public void bajaCliente(Cliente cliente) throws AdministracionException;
	public void anadirAutorizadosCuentaPersonaJuridica(Autorizado autorizado, Cliente cliente, Autorizacion autorizacion)
			throws ClienteNoEncontradoException, AutorizadoExistenteException, AutorizacionExistenteException;
	public Transaccion transaccion(Cuenta cOrigen, Cuenta cDestino, float cantidad) throws CuentaNoEncontradaException, FaltaDeFondosException;
	public void bajaAutorizado(Autorizado autorizado) throws AdministracionException;
	public Usuario login(String nombre_usuario, int contrasenia) throws AdministracionException;
	
}
