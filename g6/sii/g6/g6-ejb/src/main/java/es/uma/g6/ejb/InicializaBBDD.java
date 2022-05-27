package es.uma.g6.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class InicializaBBDD {
	@PersistenceContext(unitName = "sii-g6")
	private EntityManager em;
	
	@PostConstruct
	public void inicializar() {
		//em.find(null, em);
	}
}
