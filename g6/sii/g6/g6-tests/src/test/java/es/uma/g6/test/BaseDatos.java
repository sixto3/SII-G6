package es.uma.g6.test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.g6.*;
public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia, Map<String, String> properties) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia, properties);
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		

		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}