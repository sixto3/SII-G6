package tests;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
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
		
		Pooled cuentaPooled1 = new Pooled((long)1, (long)1, "abierta", Date.valueOf("2022-04-28"), null, null);
		em.persist(cuentaPooled1);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
