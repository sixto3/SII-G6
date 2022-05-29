package es.uma.g6.ejb;

import java.util.List;

import javax.ws.rs.core.UriBuilder;

import es.uma.g6.*;
import es.uma.g6.Auxiliares.searchParameters;
import es.uma.g6.Auxiliares.searchParameters2;
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
			throws ClienteNoEncontradoException, AutorizadoExistenteException, AutorizacionExistenteException,ClienteNoValidoException;
	public void bajaAutorizado(Autorizado autorizado) throws AdministracionException;
	public Usuario login(String nombre_usuario, String contrasenia) throws AdministracionException;
	public void bloquearCliente(Cliente cliente) throws AdministracionException;
	public void bloquearAutorizado(Autorizado autorizado) throws AdministracionException;
	public void desbloquearAutorizado(Autorizado autorizado) throws AdministracionException;
	public void desbloquearCliente(Cliente cliente) throws AdministracionException;
	public List<Individual> clientesParametros(searchParameters pa)throws AdministracionException;
	public List<Autorizado> autorizadosParametros(searchParameters pa)throws AdministracionException;
	public List<es.uma.g6.Auxiliares.Individual> individualParametros(searchParameters sp) throws AdministracionException;
	public List <es.uma.g6.Auxiliares.products2> cuentasFintech(searchParameters2 p) throws AdministracionException;
	public Usuario refrescarUsuario(Usuario u) throws AdministracionException;
}
