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

import org.junit.Before;
import org.junit.Test;

import ejb.*;
import es.uma.g6.Cliente;
import exceptions.AdministracionException;
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
			
			Cliente c = new Cliente(5,Tipo_Cliente,"bloqueado",Fecha_Alta,Fecha_Baja,Direccion,Codigo_Postal,Ciudad,Pais,false);
			gestionAdministrador.modificarDatosACliente(c);

		} catch (AdministracionException e) {
			fail("Lanzó excepción al actualizar");
		}

		try {
			Cliente c = new Cliente(1,Tipo_Cliente,"bloqueado",Fecha_Alta,Fecha_Baja,Direccion,Codigo_Postal,Ciudad,Pais,false);
			gestionAdministrador.modificarDatosACliente(c);
		} catch (AdministracionException e) {
			fail("No debería lanzar excepción");
		}
	}

}
