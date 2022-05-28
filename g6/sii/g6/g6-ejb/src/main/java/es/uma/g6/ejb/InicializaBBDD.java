package es.uma.g6.ejb;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.g6.*;

@Singleton
@Startup
public class InicializaBBDD {
	@PersistenceContext(unitName = "sii-g6")
	private EntityManager em;
	
	@PostConstruct
	public void inicializar() throws ParseException {
		Usuario comprobacion = em.find(Usuario.class,"juan");
		
		if(comprobacion != null) {
			return;
		}
		
		Divisa dolar = new Divisa("dol", "dólar", '$', (float) 0.93); 
		Divisa euro = new Divisa("eu", "euro", '€', 1);
		Divisa libra = new Divisa("lib", "libra", '£', (float) 1.18);
		
		Empresa empresa = new Empresa(1,"P3310693A", "empresa", "activa",
				Date.valueOf("20-05-2017"), Date.valueOf("20-05-2027"),
				"Calle empresa", 29000, "Málaga", "España", false, "Razon social");
		
		List<Fintech> cuentasEmpresa = new ArrayList<>();
		//-----------------------------------
		Individual individual = new Individual(2, "63937528N", "individual", "activa", 
				Date.valueOf("10-04-2016") , Date.valueOf("10-04-2026"), 
				"Calle individual", 29500, "Álora", "España", false, "juan", "lópez", Date.valueOf("13-09-1947"));
		
		Usuario usuario1 = new Usuario(individual.getNombre(), "juan", false, null, individual);
		
		//-----------------------------------
		Autorizado autorizado = new Autorizado("Y4001267V", "ana", "sánchez", "Calle autorizado",
				Date.valueOf("26-03-1947"),"activa", Date.valueOf("03-08-2007"), 
				Date.valueOf("03-08-2027"), false);
		AutorizacionId aID = new AutorizacionId(autorizado.getId(), empresa.getIdentificacion());
		Autorizacion autorizacion = new Autorizacion(aID, autorizado, empresa);
		
		Usuario usuario2 = new Usuario(autorizado.getNombre(), "ana", false, autorizado, null);
		
		//-------------------------------
		
		
		Referencia ref1 = new Referencia("VG57DDVS5173214964983931", (long) 5432, 
				"unicaja", "málaga", "España", 0 ,Date.valueOf("16-11-2018"), "activa", dolar);

		Segregada segregada = new Segregada("NL63ABNA6548268733", 
				(long) 12345, empresa.getEstado(), empresa.getFecha_Alta(), empresa.getFecha_Baja(), null, 0, ref1, empresa);
		
		cuentasEmpresa.add(segregada);
		//-------------------------------		
		Referencia ref2 = new Referencia("HN47QUXH11325678769785549996", (long) 5342, 
				"unicaja", "málaga", "España", 0 ,Date.valueOf("09-12-2016"), "activa", dolar);
		
		Segregada segregada2 = new Segregada("FR5514508000502273293129K55",(long) 3213,"activa",
				empresa.getFecha_Alta(),empresa.getFecha_Baja(),null,0,ref2,empresa); 
		
		cuentasEmpresa.add(segregada2);
		//--------------------------
		
		
		Segregada segregada3 = new Segregada("DE31500105179261215675",(long) 6161,"cerrada",
				empresa.getFecha_Alta(),Date.valueOf("01-09-2021"),null,0,ref2,empresa);
		
		cuentasEmpresa.add(segregada3);
		empresa.setCuentas_fintech(cuentasEmpresa);
		//----------------------------
		Pooled pooled = new Pooled("ES8400817251647192321264", null, 
				individual.getEstado(), individual.getFecha_Alta(), individual.getFecha_Baja(), null, individual);
		
		Referencia ref3 = new Referencia("ES7121007487367264321882",(long) 6789,"unicaja","sucursal3","españa", 100, Date.valueOf("09-12-2016") ,"activa",euro);
		Referencia ref4 = new Referencia("VG88HBIJ4257959912673134",(long) 7896,"unicaja","sucursal4","españa", 200, Date.valueOf("09-12-2016") ,"activa",dolar);
		Referencia ref5 = new Referencia("GB79BARC20040134265953",(long) 8967,"unicaja","sucursal5","españa", 140, Date.valueOf("09-12-2016") ,"activa",libra);
		
		DepositadaEnId depId1 = new DepositadaEnId(pooled.getIBAN(), Integer.parseInt(ref3.getIBAN()));
		DepositadaEnId depId2 = new DepositadaEnId(pooled.getIBAN(), Integer.parseInt(ref4.getIBAN()));
		DepositadaEnId depId3 = new DepositadaEnId(pooled.getIBAN(), Integer.parseInt(ref5.getIBAN()));
		
		
		DepositadaEn dep1 = new DepositadaEn(depId1, pooled, ref3, 100);
		DepositadaEn dep2 = new DepositadaEn(depId2, pooled, ref4, 200);
		DepositadaEn dep3 = new DepositadaEn(depId3, pooled, ref5, 134);
		
		List<DepositadaEn> depositadas = new ArrayList<>();
		depositadas.add(dep1);
		depositadas.add(dep2);
		depositadas.add(dep3);
		pooled.setDepositada_pooled(depositadas);
	
		//------------------------------
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		
		Transaccion tranO = new Transaccion(1, date, 200, date, null, 0, null);
		Transaccion tranD = new Transaccion(1, date, 200, date, null, 0, null);
		
		List<Transaccion> origen = new ArrayList<>();
		List<Transaccion> destino = new ArrayList<>();
		
		origen.add(tranO);
		destino.add(tranD);
		
		segregada.setTransacciones_origen(origen);
		pooled.setTransacciones_destino(destino);
		
		segregada.getRef().setSaldo((float) segregada.getRef().getSaldo() - 200);
		pooled.getDepositada_pooled().get(1).setSaldo(pooled.getDepositada_pooled().get(1).getSaldo() + 200);
			
		//--------------
		
		Usuario administrador = new Usuario("ponciano", "ponciano", true, null, null);

		
		em.persist(administrador);
		
		em.persist(empresa);
		
		em.persist(usuario1);
		em.persist(individual);
		
		em.persist(usuario2);
		em.persist(autorizado);
		em.persist(autorizacion);
		em.persist(aID);
		
		em.persist(segregada);
		em.persist(ref1);
		
		em.persist(segregada2);
		em.persist(ref2);
		
		em.persist(segregada3);
		
		em.persist(pooled);
		em.persist(ref3);
		em.persist(ref4);
		em.persist(ref5);
		em.persist(depId1);
		em.persist(depId2);
		em.persist(depId3);
		em.persist(dep1);
		em.persist(dep2);
		em.persist(dep3);
		
		em.persist(tranO);
		em.persist(tranD);
		
		em.persist(euro);
		em.persist(dolar);
		em.persist(libra);
	}
}
