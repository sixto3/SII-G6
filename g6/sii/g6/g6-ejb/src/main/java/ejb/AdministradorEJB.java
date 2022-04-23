package ejb;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.message.config.AuthConfig;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.AutorizadoNoEncontradoException;
import exceptions.ClienteNoEncontradoException;

public class AdministradorEJB implements gestionAdministrador{

	private static final Logger LOG = Logger.getLogger(AdministradorEJB.class.getCanonicalName());
	
	@PersistenceContext(name="Administración")
	private EntityManager em;
	
	@Override
	public void modificarDatosACliente(Cliente cliente) throws ClienteNoEncontradoException {
		Cliente cl = em.find(Cliente.class, cliente.getId());
		if(cl == null) throw new ClienteNoEncontradoException();
		cliente.setId(cl.getId());
		cliente.setBloqueado(cl.isBloqueado());
		cliente.setContraseña(cl.getContraseña());
		em.persist(cliente);
	}

	@Override
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AutorizadoNoEncontradoException{
		Autorizado au = em.find(Autorizado.class, autorizado.getId());
		if(au == null) throw new AutorizadoNoEncontradoException();
		autorizado.setApellido(au.getApellido());
		autorizado.setContraseña(au.getContraseña());
		autorizado.setDireccion(au.getDireccion());
		autorizado.setEstado(au.getEstado());
		autorizado.setFecha_nacimiento(au.getFecha_nacimiento());
		autorizado.setFechaFin(au.getFechaFin());
		autorizado.setFechaInicio(au.getFechaInicio());
		autorizado.setId(au.getId());
		autorizado.setIdentificacion(au.getIdentificacion());
		autorizado.setNombre(au.getNombre());
		em.persist(autorizado);
		
	}

}
