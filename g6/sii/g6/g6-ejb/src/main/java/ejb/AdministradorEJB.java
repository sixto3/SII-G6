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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdministradorEJB implements gestionAdministrador{

	private static final Logger LOG = Logger.getLogger(AdministradorEJB.class.getCanonicalName());
	
	@PersistenceContext(name="Administración")
	private EntityManager em;
	
	@Override
	public void modificarDatosACliente(Cliente cliente) throws ClienteNoEncontradoException {
		Cliente cl = em.find(Cliente.class, cliente.getIdentificacion());
		if(cl == null) throw new ClienteNoEncontradoException();
		em.merge(cliente);
	}
	@Override
	public void modificarDatosAAutorizado(Autorizado autorizado) throws AutorizadoNoEncontradoException{
		Autorizado au = em.find(Autorizado.class, autorizado.getIdentificacion());
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
		em.merge(pooled);
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
		em.merge(segregada);
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
	
	private String tipoDeCuenta(Cuenta c){
		String res = "";
		if(em.find(Segregada.class, c.getIBAN()) != null) res = "Segregada";
		else if(em.find(Pooled.class, c.getIBAN()) != null) res = "Pooled";
		else if (em.find(Cuenta.class, c.getIBAN()) != null) res = "Externa";
		else res = null;
		return res;
	}
	@Override
	public void bajaCliente(Cliente cliente) throws AdministracionException {
		
		Cliente c = em.find(Cliente.class, cliente.getIdentificacion());
		if (c==null) {
			throw new AdministracionException("Cuenta no encontrada");
			
		}else {
			
			cliente.setBloqueado(false);
			cliente.setEstado("baja");
			Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
			cliente.setFecha_Baja(date);
		     	
		}
		em.merge(cliente);
	}
	
	@Override
	public void bajaAutorizado(Autorizado autorizado) throws AdministracionException {
		
		Autorizado  c = em.find(Autorizado.class, autorizado.getIdentificacion());
		if (c==null) {
			throw new AdministracionException("Cuenta no encontrada");
			
		}else {
			
			autorizado.setBloqueado(false);
			
	
			autorizado.setEstado("baja");
		}
		em.merge(autorizado);
	}
	@Override
	public Usuario login(String nombre_usuario, int contrasenia) throws AdministracionException {
		// TODO Auto-generated method stub
		Usuario u =em.find(Usuario.class, nombre_usuario);
		if(u==null) throw new UsuarioNoEncontradoException();
		if(u.getCliente().getTipo_Cliente().equalsIgnoreCase("empresa")) throw new EmpresaNoPuedeHacerLogin();
		if(u.getContraseña() != contrasenia) throw new ContraseñaIncorrectaException();
		if(u.getAutorizado()!=null) {
			if(!u.getAutorizado().getEstado().equalsIgnoreCase("activo")) throw new UsuarioNoActivoException();
		}
		if(u.getCliente()!=null){
			if(!u.getCliente().getEstado().equalsIgnoreCase("activo")) throw new UsuarioNoActivoException();
		}
		
		return new Usuario(nombre_usuario, contrasenia, u.isAdministrador(), u.getAutorizado(), u.getCliente());
	}
	
	
	

}
