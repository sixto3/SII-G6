package es.uma.g6.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.security.auth.message.config.AuthConfig;
import javax.ws.rs.core.UriBuilder;

import es.uma.g6.*;
import es.uma.g6.Individual;
import es.uma.g6.Auxiliares.*;

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

import java.net.URI;
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
		List<DepositadaEn> list = pooled.getDepositada_pooled();
		double saldo=0;
		for(DepositadaEn cuenta : list) {
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

			emp.setLista_autorizados(lista_aut);
			autorizado.setLista_empresas(lista_emp);

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

	public Usuario login(String nombre_usuario, String contrasenia) throws AdministracionException {
        // TODO Auto-generated method stub
        Usuario u =em.find(Usuario.class, nombre_usuario);

        if(u==null) throw new UsuarioNoEncontradoException();

        if(u.getCliente().getTipo_Cliente().equalsIgnoreCase("Empresa")) throw new EmpresaNoPuedeHacerLogin();

        if(u.getContraseña().equals(contrasenia)) throw new ContraseniaIncorrectaException();

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

public List<Individual> clientesParametros(searchParameters p){
		
		
		Query query = em.createQuery("select i from Individual i where i.Nombre = ?1 AND i.Apellido =?2  AND i.Fecha_Alta = ?3 AND i.Fecha_Baja =? 4");  
		query.setParameter(1, p.getNombre().getFirstName());
		query.setParameter(2, p.getNombre().getLastName());
		query.setParameter(3, p.getStartPeriod());
		query.setParameter(4, p.getEndPeriod());
		List<es.uma.g6.Individual> individual  = query.getResultList();
		 return individual;
	}
	
	
	
	

public List <Autorizado> autorizadosParametros (searchParameters p){
		
	Query query = em.createQuery("select a from Autorizado a  where a.Nombre = ?1 AND a.Apellido =?2  AND a.Fecha_Alta = ?3 AND a.Fecha_Baja =? 4"); 
	query.setParameter(1, p.getNombre().getFirstName());
	query.setParameter(2, p.getNombre().getLastName());
	query.setParameter(3, p.getStartPeriod());
	query.setParameter(4, p.getEndPeriod());
	List<Autorizado> autorizado = query.getResultList();
		 return autorizado;
		}


public List<es.uma.g6.Auxiliares.Individual> individualParametros(searchParameters sp){
	
List<Individual> ind = this.clientesParametros(sp);
	
	List<es.uma.g6.Auxiliares.Individual> lista =null;
	
	List<Autorizado> aut= this.autorizadosParametros(sp);
	
	for (int i=0 ; i< ind.size(); i++) {
		
		es.uma.g6.Auxiliares.address direccion = new es.uma.g6.Auxiliares.address();
		es.uma.g6.Auxiliares.Individual nuevo= new es.uma.g6.Auxiliares.Individual();
		es.uma.g6.Auxiliares.name nombre = new es.uma.g6.Auxiliares.name();
		nuevo.setDateOfBirth(ind.get(i).getFecha_Nacimiento());
		nuevo.setIdentificationNumber(ind.get(i).getId());
		direccion.setCity(ind.get(i).getCiudad());
		direccion.setCountry(ind.get(i).getPaís());
		direccion.setPostalCode(ind.get(i).getCodigo_Postal());
		direccion.setStreetNumber(ind.get(i).getDireccion());
		nuevo.setDireccion(direccion);
		nombre.setFirstName(ind.get(i).getNombre());
		nombre.setLastName(ind.get(i).getApellido());
		nuevo.setName(nombre);
		
		List <es.uma.g6.Auxiliares.products> list_productos=null;
		
		
				for(int j=0; j<ind.get(i).getCuentas_fintech().size();j++ ) {
				
				es.uma.g6.Auxiliares.products producto = new products();
				
				producto.setProductNumber(ind.get(i).getCuentas_fintech().get(j).getIBAN());
				producto.setRelationship("propietaria");
				producto.setStatus(ind.get(i).getCuentas_fintech().get(j).getEstado());
				list_productos.add(producto);
					
			}
				
			
		nuevo.setProductos(list_productos);
		lista.add(nuevo);
		
	}
	
	for(int i = 0 ; i<aut.size(); i++) {
		
		List <es.uma.g6.Auxiliares.products> list_productos = null;
		es.uma.g6.Auxiliares.address direccion = new es.uma.g6.Auxiliares.address();
		es.uma.g6.Auxiliares.Individual nuevo= new es.uma.g6.Auxiliares.Individual();
		es.uma.g6.Auxiliares.name nombre = new es.uma.g6.Auxiliares.name();
		
		nuevo.setDateOfBirth(aut.get(i).getFecha_nacimiento());
		nuevo.setIdentificationNumber(aut.get(i).getId());
		direccion.setCity(aut.get(i).getCiudad());
		direccion.setCountry(aut.get(i).getPais());
		direccion.setPostalCode(aut.get(i).getCodigo_Postal());
		direccion.setStreetNumber(aut.get(i).getDireccion());
		nuevo.setDireccion(direccion);
		nombre.setFirstName(aut.get(i).getNombre());
		nombre.setLastName(aut.get(i).getApellido());
		nuevo.setName(nombre);
		
	
		
		
		for(int j=0; j<aut.size();j++ ) {
			
			
		
		for (int m =0 ; m<aut.get(i).getLista_empresas().size(); m++) {
			
			
			for (int h =0 ; h< aut.get(i).getLista_empresas().get(m).getEmpresa().getCuentas_fintech().size(); h++) {
				
	es.uma.g6.Auxiliares.products producto = new products();
	producto.setProductNumber(aut.get(i).getLista_empresas().get(m).getEmpresa().getCuentas_fintech().get(h).getIBAN());
	producto.setStatus(aut.get(i).getLista_empresas().get(m).getEmpresa().getCuentas_fintech().get(h).getEstado());
	producto.setRelationship("autorizada");
	list_productos.add(producto);
	
				
			}
			
		}	
	}
	
		nuevo.setProductos(list_productos);
		lista.add(nuevo);

}
		return lista;
	
	
}


	
	public List <products2> cuentasFintech(searchParameters2 p){
		
		Query query = em.createQuery("select fi from Fintech fi where fi.estado = ?1 OR fi.IBAN = ?2");  
		query.setParameter(1, p.getStatus());
		query.setParameter(2, p.getProductNumber());
		List<Fintech> fintech = query.getResultList();
		List<products2> list_prod = null;
		String aux = "Activo";
		String aux2= "Individual";
		
		for(int i=0; i<fintech.size();i++) {
			
			products2 producto = new products2();
			address direccion = new address();
			name nombre = new name();
			accountHolder dueño = new accountHolder();
			
			if (fintech.get(i).getDuenio().getEstado().equals(aux)) {
				
				dueño.setActiveCustomer(true);
			}else {
				
				dueño.setActiveCustomer(false);
			}
			
			if(fintech.get(i).getDuenio().getTipo_Cliente().equals(aux2)){
			
			dueño.setAccounttype("Física");
			Query query2 = em.createQuery("select i from Individual i where i.Identificación = ?1");  
			query2.setParameter(1, fintech.get(i).getDuenio().getIdentificacion());
			List<Individual> in = query2.getResultList();
			nombre.setFirstName(in.get(0).getNombre());
			nombre.setLastName(in.get(0).getApellido());
			dueño.setName(nombre);
			
			}else {
				
			dueño.setAccounttype(fintech.get(i).getDuenio().getTipo_Cliente());
			Query query3 = em.createQuery("select e from Empresa where e.Identificación = ?1");  
			query3.setParameter(1, fintech.get(i).getDuenio().getIdentificacion());
			List<Empresa> e = query3.getResultList();
			nombre.setFirstName(e.get(0).getRazon_social());
			nombre.setLastName("");
			dueño.setName(nombre);
				
			}
			
			direccion.setCity(fintech.get(i).getDuenio().getCiudad());
			direccion.setCountry(fintech.get(i).getDuenio().getPaís());
			direccion.setPostalCode(fintech.get(i).getDuenio().getCodigo_Postal());
			direccion.setStreetNumber(fintech.get(i).getDuenio().getDireccion());
			dueño.setDireccion(direccion);
			
			producto.setProductNumber(fintech.get(i).getIBAN());
			producto.setStatus(fintech.get(i).getEstado());
			producto.setStartDate(fintech.get(i).getFechaApertura());
			producto.setEndDate(fintech.get(i).getFechaCierre());
			
			producto.setAccountHolder(dueño);
			list_prod.add(producto);
		
		}
		return list_prod;
	
}

	 
	 @Override
	    public Usuario refrescarUsuario(Usuario u) throws AdministracionException {
		 	Usuario u2 =em.find(Usuario.class, u.getNombre());

	        if(u==null) throw new UsuarioNoEncontradoException();
	        Usuario user = em.find(Usuario.class, u.getNombre());
	        em.refresh(user);
	        return user;

	    }


}
