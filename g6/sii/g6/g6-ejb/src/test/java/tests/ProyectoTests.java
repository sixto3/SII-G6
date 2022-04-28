package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.junit.Before;
import org.junit.Test;

import ejb.*;
import es.uma.g6.Autorizacion;
import es.uma.g6.Autorizado;
import es.uma.g6.Cliente;
import exceptions.AdministracionException;
import exceptions.AutorizadoNoEncontradoException;
import exceptions.ClienteNoEncontradoException;
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
	
	@Test
	public void testModificarDatosACliente() {
		final String Tipo_Cliente = "individual";
		final String Direccion = "Calle Palangreros nº 3";
		final int Codigo_Postal = 29640;
		final String Ciudad = "fuengirola";
		final String Pais ="españa";
		final Date Fecha_Alta = Date.valueOf("28-04-2022");
	    final Date Fecha_Baja = Date.valueOf("28-04-2023");

		try {
			Cliente c = new Cliente(1,Tipo_Cliente,"bloqueado",Fecha_Alta,Fecha_Baja,Direccion,Codigo_Postal,Ciudad,Pais,false);
			gestionAdministrador.modificarDatosACliente(c);
		} catch (AdministracionException e) {
			fail("No debería lanzar excepción");
		}
	}
	
	@Test
	public void testModificarDatosAClienteNoEncontrado() {
		final String Tipo_Cliente = "individual";
		final String Direccion = "Calle Palangreros nº 3";
		final int Codigo_Postal = 29640;
		final String Ciudad = "fuengirola";
		final String Pais ="españa";
		final Date Fecha_Alta = Date.valueOf("28-04-2022");
	    final Date Fecha_Baja = Date.valueOf("28-04-2023");
		try {
			Cliente c = new Cliente(5,Tipo_Cliente,"bloqueado",Fecha_Alta,Fecha_Baja,Direccion,Codigo_Postal,Ciudad,Pais,false);
			gestionAdministrador.modificarDatosACliente(c);
			fail("Debería lanzar excepción de cliente no encontrado");
		} catch (ClienteNoEncontradoException e) {
			// OK
		} catch (AdministracionException e) {
			fail("Debería lanzar excepción de cliente no encontrado");
		}
	}

	@Test
	public void testModificarDatosAAutorizado() {
		final String nombre = "Juan Manuel";
		final String apellido = "García";
		final String direccion = "Calle Ortega y Gasset nº 54";
		final Date fecha_nacimiento = Date.valueOf("2-02-1990");
		final String estado = "activo";
		final Date fechaInicio = Date.valueOf("25-02-2016");
		final Date fechaFin = Date.valueOf("25-02-2026");
		final boolean bloqueado = false;
		
		try {
			Autorizado a = new Autorizado(1, nombre, apellido, direccion, fecha_nacimiento, estado, fechaInicio, fechaFin, bloqueado);
			gestionAdministrador.modificarDatosAAutorizado(a);
		} catch (AdministracionException e) {
			fail("No debería lanzar excepción");
		}
	}
	
	@Test
	public void testModificarDatosAAutorizadoNoEncontrado() {
		final String nombre = "Juan Manuel";
		final String apellido = "García";
		final String direccion = "Calle Ortega y Gasset nº 54";
		final Date fecha_nacimiento = Date.valueOf("2-02-1990");
		final String estado = "activo";
		final Date fechaInicio = Date.valueOf("25-02-2016");
		final Date fechaFin = Date.valueOf("25-02-2026");
		final boolean bloqueado = false;
		try {
			Autorizado a = new Autorizado(5, nombre, apellido, direccion, fecha_nacimiento, estado, fechaInicio, fechaFin, bloqueado);
			gestionAdministrador.modificarDatosAAutorizado(a);
			fail("Debería lanzar excepción de autorizado no encontrado");
		} catch (AutorizadoNoEncontradoException e) {
			// OK
		} catch (AdministracionException e) {
			fail("Debería lanzar excepción de autorizado no encontrado");
		}
	}
	
}
