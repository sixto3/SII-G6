package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ejb.AdministradorEJB;
import ejb.ClienteEJB;
import ejb.gestionAdministrador;
import ejb.gestionCliente;
import es.uma.g6.Cliente;
import tests.BaseDeDatos;
import tests.SuiteTest;
import tests.prueba;
import ejb.gestionCliente;



public class prueba {
	private static final Logger LOG = Logger.getLogger(prueba.class.getCanonicalName());

	private static final String CLIENTE_EJB = "java:global/classes/ClienteEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "sii-g6Test";
	
	private gestionCliente gestionCliente;
	
	@Before
	public void setup() throws NamingException  {
		gestionCliente = (gestionCliente) SuiteTest.ctx.lookup(CLIENTE_EJB);
		BaseDeDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	
}
