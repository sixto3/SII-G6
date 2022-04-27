package tests;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;

import ejb.*;
public class ProyectoTests {
	
	private static final Logger LOG = Logger.getLogger(ProyectoTests.class.getCanonicalName());

	private static final String ADMINISTRADOR_EJB = "java:global/classes/AdministradorEJB";
	private static final String CLIENTE_EJB = "java:global/classes/ClienteEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "sii-g6Test";
	
	private gestionAdministrador gestionAdministrador;
	private gestionCliente gestionCliente;
	
	@Before
	public void setup() throws NamingException  {
		gestionAdministrador = (gestionAdministrador) SuiteTest.ctx.lookup(ADMINISTRADOR_EJB);
		gestionCliente = (gestionCliente) SuiteTest.ctx.lookup(CLIENTE_EJB);
		BaseDeDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	

}
