package ejb;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.message.config.AuthConfig;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.AutorizadoNoEncontradoException;
import exceptions.ClienteNoEncontradoException;
import exceptions.PooledExistenteException;
import exceptions.SegregadaExistenteException;
import exceptions.PooledNoEncontradaException;
import exceptions.SegregadaNoEncontradaException;

import java.text.SimpleDateFormat;
import java.util.Date;

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

	@Override
	public void abrirCuentaPooled(Pooled pooled) throws AdministracionException {
		// TODO Auto-generated method stub
		Pooled pool = em.find(Pooled.class, pooled.getIBAN());
		if(pool != null) throw new PooledExistenteException();
		em.persist(pooled);
	}

	@Override
	public void abrirCuentaSegregada(Segregada segregada) throws AdministracionException {
		// TODO Auto-generated method stub
		Segregada seg = em.find(Segregada.class, segregada.getIBAN());
		if(seg != null) throw new SegregadaExistenteException();
		em.persist(segregada);
	}

	@Override
	public void cerrarCuentaPooled(Pooled pooled) throws AdministracionException {
		// TODO Auto-generated method stub
		Pooled pool = em.find(Pooled.class, pooled.getIBAN());
		if(pool == null) throw new PooledNoEncontradaException();
		Date fechaActual = new Date();
		pooled.setEstado("Cerrada");
		pooled.setFechaCierre(fechaActual);
		em.persist(pooled);
	}

	@Override
	public void cerrarCuentaSegregada(Segregada segregada) throws AdministracionException {
		// TODO Auto-generated method stub
		Segregada seg = em.find(Segregada.class, segregada.getIBAN());
		if(seg == null) throw new SegregadaNoEncontradaException();
		Date fechaActual = new Date();
		segregada.setEstado("Cerrada");
		segregada.setFechaCierre(fechaActual);
		em.persist(segregada);
	}
	
		
	

}
