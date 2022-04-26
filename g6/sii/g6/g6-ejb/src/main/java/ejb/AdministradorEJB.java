package ejb;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.message.config.AuthConfig;

import es.uma.g6.*;
import exceptions.AdministracionException;
import exceptions.AutorizacionExistenteException;
import exceptions.AutorizadoExistenteException;
import exceptions.AutorizadoNoEncontradoException;
import exceptions.ClienteExistenteException;
import exceptions.ClienteNoEncontradoException;
import exceptions.ClienteNoValidoException;
import exceptions.CuentaNoACeroException;
import exceptions.CuentaNoEncontradaException;
import exceptions.FaltaDeFondosException;
import exceptions.PooledExistenteException;
import exceptions.SegregadaExistenteException;
import exceptions.PooledNoEncontradaException;
import exceptions.SegregadaNoEncontradaException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdministradorEJB implements gestionAdministrador{

	private static final Logger LOG = Logger.getLogger(AdministradorEJB.class.getCanonicalName());
	
	@PersistenceContext(name="Administración")
	private EntityManager em;
	
	@Override
	public void modificarDatosACliente(Cliente cliente) throws ClienteNoEncontradoException {
		Cliente cl = em.find(Cliente.class, cliente.getId());
		if(cl == null) throw new ClienteNoEncontradoException();
		em.merge(cliente);
	}

	@Override
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AutorizadoNoEncontradoException{
		Autorizado au = em.find(Autorizado.class, autorizado.getId());
		if(au == null) throw new AutorizadoNoEncontradoException();
		em.merge(autorizado);
		
	}

	@Override
	public void abrirCuentaPooled(Pooled pooled) throws PooledExistenteException {
		// TODO Auto-generated method stub
		Pooled pool = em.find(Pooled.class, pooled.getIBAN());
		if(pool != null) throw new PooledExistenteException();
		em.persist(pooled);
	}

	@Override
	public void abrirCuentaSegregada(Segregada segregada) throws SegregadaExistenteException {
		// TODO Auto-generated method stub
		Segregada seg = em.find(Segregada.class, segregada.getIBAN());
		if(seg != null) throw new SegregadaExistenteException();
		em.persist(segregada);
	}

	@Override
	public void cerrarCuentaPooled(Pooled pooled) throws PooledNoEncontradaException, CuentaNoACeroException{
		// TODO Auto-generated method stub
		Pooled pool = em.find(Pooled.class, pooled.getIBAN());
		if(pool == null) throw new PooledNoEncontradaException();
		List<Depositada_en> list = pooled.getDepositada_pooled();
		double saldo=0;
		for(Depositada_en cuenta : list) {
			saldo+=cuenta.getSaldo();
		}
		if(saldo != 0) throw new CuentaNoACeroException();
		Date fechaActual = new Date();
		pooled.setEstado("Cerrada");
		pooled.setFechaCierre(fechaActual);
		em.persist(pooled);
	}

	@Override
	public void cerrarCuentaSegregada(Segregada segregada) throws SegregadaNoEncontradaException, CuentaNoACeroException {
		// TODO Auto-generated method stub
		Segregada seg = em.find(Segregada.class, segregada.getIBAN());
		if(seg == null) throw new SegregadaNoEncontradaException();
		if(segregada.getRef().getSaldo() != 0) throw new CuentaNoACeroException();
		Date fechaActual = new Date();
		segregada.setEstado("Cerrada");
		segregada.setFechaCierre(fechaActual);
		em.persist(segregada);
	}

	@Override
	public void altaCliente(Cliente cliente) throws ClienteExistenteException, ClienteNoValidoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anadirAutorizadosCuentaPersonaJuridica(Autorizado autorizado, Cliente cliente,
			Autorizacion autorizacion)
			throws ClienteNoEncontradoException, AutorizadoExistenteException, AutorizacionExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaccion transaccion(Cuenta cOrigen, Cuenta cDestino, float cantidad)
			throws CuentaNoEncontradaException, FaltaDeFondosException {
		String tipoOrigen = tipoDeCuenta(cOrigen);
		String tipoDestino = tipoDeCuenta(cDestino);
	
		if(tipoOrigen == null)throw new CuentaNoEncontradaException();
		if(tipoDestino == null)throw new CuentaNoEncontradaException();
		
		if(tipoOrigen.equals("Segregada")) {
			
			if(tipoDestino.equals("Segregada")) {
				Segregada cDsg = em.find(Segregada.class, cOrigen.getIBAN());
				
				
			}else if(tipoDestino.equals("Pooled")) {
				
			}else {
				
			}
		}else if(tipoOrigen.equals("Pooled")) {
			if(tipoDestino.equals("Segregada")) {
				
			}else if(tipoDestino.equals("Pooled")) {
				
			}else {
				
			}
		}else {
			if(tipoDestino.equals("Segregada")) {
				
			}else if(tipoDestino.equals("Pooled")) {
				
			}else {
				
			}
		}

		return t;
	}
	
	private String tipoDeCuenta(Cuenta c){
		String res = "";
		if(em.find(Segregada.class, c.getIBAN()) != null) res = "Segregada";
		else if(em.find(Pooled.class, c.getIBAN()) != null) res = "Pooled";
		else if (em.find(Cuenta.class, c.getIBAN()) != null) res = "Externa";
		else res = null;
		return res;
	}
	

}
