package tests;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.g6.*;

public class BaseDeDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Cliente cliente = new Cliente(1,"individual", "bloqueado", Date.valueOf("27-04-2022"),Date.valueOf("27-04-2023"),
				"abds",29009,"malaga", "españa", true);

		Autorizado autorizado = new Autorizado(1, "Juan", "García", "Calle Serrato nº 8", Date.valueOf("2-02-1990"), "activo", Date.valueOf("25-02-2017"), Date.valueOf("25-02-2025"), false);
		
		List<Depositada_en> lista = new ArrayList<>();
		Referencia ref = new Referencia((long)2,(long)2,"Caja Rural","málaga","españa",3500,Date.valueOf("5-05-2005"), "activo",new Divisa("eu","euro" ,'€', 0), lista);
		Segregada segre = new Segregada((long)2,(long)2, "activo", Date.valueOf("5-05-2005"), Date.valueOf("5-05-2025"), "",0.1, null);
		
		Pooled cuentaPooled1 = new Pooled((long)1, (long)1, "abierta", Date.valueOf("2022-04-28"), null, null);
		
		em.persist(cuentaPooled1);
		em.persist(cliente);
		em.persist(autorizado);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
