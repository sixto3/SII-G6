package tests;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.g6.Cliente;

public class BaseDeDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Cliente cliente = new Cliente(1,"individual", "bloqueado", Date.valueOf("27-04-2022"),Date.valueOf("27-04-2023"),
				"abds",29009,"malaga", "españa", true);
		
		em.persist(cliente);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
