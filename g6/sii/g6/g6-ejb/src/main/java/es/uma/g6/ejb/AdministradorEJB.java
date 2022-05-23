package es.uma.g6.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.message.config.AuthConfig;

import es.uma.g6.*;
import es.uma.g6.AutorizacionId;
import exceptions.AdministracionException;
import exceptions.AutorizacionExistenteException;
import exceptions.AutorizadoExistenteException;
import exceptions.AutorizadoNoEncontradoException;
import exceptions.ClienteExistenteException;
import exceptions.ClienteNoEncontradoException;
import exceptions.ClienteNoValidoException;
import exceptions.ContraseniaIncorrectaException;
import exceptions.CuentaNoACeroException;
import exceptions.CuentaNoEncontradaException;
import exceptions.EmpresaNoPuedeHacerLogin;
import exceptions.FaltaDeFondosException;
import exceptions.PooledExistenteException;
import exceptions.SegregadaExistenteException;
import exceptions.PooledNoEncontradaException;
import exceptions.SegregadaNoEncontradaException;
import exceptions.UsuarioNoActivoException;
import exceptions.UsuarioNoEncontradoException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.*;
@Stateless
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

	public boolean esPersonaJuridica(String cad) {
        boolean enc = false;
        cad.toUpperCase();
        if(cad.compareTo("Empresa") ==0) {
            enc = true;
        }
        return enc;
    }

	public boolean esPersonaFisica(String cad) {
        boolean enc = false;
        cad.toUpperCase();
        if(cad.compareTo("Individual") ==0) {
            enc = true;
        }
        return enc;
    }
	
	@Override
	public void altaCliente(Cliente cliente) throws ClienteExistenteException, ClienteNoValidoException {
		// TODO Auto-generated method stub
		 Cliente cl = em.find(Cliente.class, cliente.getIdentificacion());
	     if(cl != null) throw new ClienteExistenteException();

	     String tipo = cl.getTipo_Cliente();

	     if(!esPersonaJuridica(tipo) || !esPersonaFisica(tipo)) throw new ClienteNoValidoException();
	     em.persist(cl);
	}

	@Override
	public void anadirAutorizadosCuentaPersonaJuridica(Autorizado autorizado, Cliente cliente, Autorizacion autorizacion)
			throws ClienteNoEncontradoException, AutorizadoExistenteException, AutorizacionExistenteException, ClienteNoValidoException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		if (!(esPersonaJuridica(cliente.getTipo_Cliente()))) {
			throw new ClienteNoEncontradoException();
		} else {

			Empresa emp = em.find(Empresa.class, cliente.getIdentificacion());
			List<Autorizacion> lista_aut = emp.getLista_autorizados();
			List<Autorizacion> lista_emp = autorizado.getLista_empresas();

			autorizacion.setAutorizado(autorizado);
			autorizacion.setEmpresa(emp);

			lista_aut.add(autorizacion);
			lista_emp.add(autorizacion);

			em.merge(emp);
			em.merge(autorizado);
			em.persist(autorizacion);
		}
	}

		@Override
	public void bajaCliente(Cliente cliente) throws AdministracionException {
		
		Cliente c = em.find(Cliente.class, cliente.getIdentificacion());
		if (c==null) {
			throw new AdministracionException("Cuenta no encontrada");
			
		}else {
			
			cliente.setBloqueado(false);
			cliente.setEstado("Cerrada");
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
			
	
			autorizado.setEstado("Cerrada");
		}
		em.merge(autorizado);
	}

	public Usuario login(String nombre_usuario, int contrasenia) throws AdministracionException {
        // TODO Auto-generated method stub
        Usuario u =em.find(Usuario.class, nombre_usuario);

        if(u==null) throw new UsuarioNoEncontradoException();

        if(u.getCliente().getTipo_Cliente().equalsIgnoreCase("Empresa")) throw new EmpresaNoPuedeHacerLogin();

        if(u.getContraseña() != contrasenia) throw new ContraseniaIncorrectaException();

        if(u.getAutorizado()!=null) {
            if(!u.getAutorizado().getEstado().equalsIgnoreCase("Activo")) throw new UsuarioNoActivoException();
        }

        if(u.getCliente()!=null){
            if(!u.getCliente().getEstado().equalsIgnoreCase("Activo")) throw new UsuarioNoActivoException();
        }

        return new Usuario(nombre_usuario, contrasenia, u.isAdministrador(), u.getAutorizado(), u.getCliente());
    }
	
	@Override
	public void bloquearCliente(Cliente cliente) throws AdministracionException {
		
		if(!(cliente.getTipo_Cliente().equalsIgnoreCase("Empresa")) &&
				(!(cliente.getTipo_Cliente().equalsIgnoreCase("Individual")))){
			
			throw  new AdministracionException("Tipo no válido");
		}
		
		
		if(cliente.getTipo_Cliente().equalsIgnoreCase("Empresa")) {
			
			
			Empresa emp = em.find(Empresa.class,cliente.getIdentificacion() );
			
			if(emp==null) {
				throw  new AdministracionException("Cuenta no encontrada");
				
			}else {
				
				List<Autorizacion> c = emp.getLista_autorizados();
				for (Autorizacion aux : c) {
					
					aux.getAutorizado().setBloqueado(true);
					aux.getAutorizado().setEstado("Bloqueado");
					boolean encontrado = false;
					List<Autorizacion> lista=  aux.getAutorizado().getLista_empresas();
					Iterator<Autorizacion> it = lista.iterator();
					em.merge(aux);
					while(encontrado!=true &&  it.hasNext()) {
						
						if (it.next().getAutorizado().isBloqueado()!=true) {
						
						encontrado=true;
						}
					
					}
				if( encontrado!=true) {
					
					aux.getAutorizado().setBloqueado(true);
					aux.getAutorizado().setEstado("Bloqueado");
					em.merge(aux.getAutorizado());
				}
				}
			}
			
		}else {
			
			Individual ind = em.find(Individual.class, cliente.getIdentificacion());
			
			if(ind==null) {
				
				throw  new CuentaNoEncontradaException();
			}else {
				
				ind.setBloqueado(true);
				ind.setEstado("Bloqueado");
				
			}
			
			em.merge(ind);
		}
		
		
	}
	@Override
	public void bloquearAutorizado(Autorizado autorizado) throws AdministracionException {
		Autorizado aut= em.find(Autorizado.class, autorizado.getIdentificacion());
		if(aut==null) {
			
			throw new AdministracionException("Cuenta no encontrada");
		}
		
		autorizado.setBloqueado(true);
		autorizado.setEstado("Bloqueado");
		
		em.merge(autorizado);	
	}
	
	
	@Override
	public void desbloquearAutorizado(Autorizado autorizado) throws AdministracionException {
		Autorizado aut= em.find(Autorizado.class, autorizado.getIdentificacion());
		if(aut==null) {
			
			throw new AdministracionException("Cuenta no encontrada");
		}
		
		autorizado.setBloqueado(false);
		autorizado.setEstado("activo");
		
		em.merge(autorizado);	
		
	}
	
	
	
	@Override
	public void desbloquearCliente(Cliente cliente) throws AdministracionException {
		
		
		if(!(cliente.getTipo_Cliente().equalsIgnoreCase("empresa")) && 
				(!(cliente.getTipo_Cliente().equalsIgnoreCase("individual")))){
			
			throw  new AdministracionException("Tipo no válido");
		}
		
		
		if(cliente.getTipo_Cliente().equalsIgnoreCase("empresa")) {
			
			
			Empresa emp = em.find(Empresa.class,cliente.getIdentificacion() );
			
			if(emp==null) {
				throw  new AdministracionException("Cuenta no encontrada");
				
			}else {
				
				List<Autorizacion> c = emp.getLista_autorizados();
				for (Autorizacion aux : c) {
					
					aux.getAutorizado().setBloqueado(false);
					
					List<Autorizacion> lista=  aux.getAutorizado().getLista_empresas();
					Iterator<Autorizacion> it = lista.iterator();
					em.merge(aux);
					while( it.hasNext()) {
						
						it.next().getAutorizado().setBloqueado(false);
					
					}
				
					
					aux.getAutorizado().setBloqueado(false);
					aux.getAutorizado().setEstado("Activo");
					em.merge(aux.getAutorizado());
				
				}
			}
			
		}else {
			
			Individual ind = em.find(Individual.class, cliente.getIdentificacion());
			
			if(ind==null) {
				
				throw  new CuentaNoEncontradaException();
			}else {
				
				ind.setBloqueado(false);
				ind.setEstado("Activo");
				
			}
			
			em.merge(ind);
		}	
	}

	

}
