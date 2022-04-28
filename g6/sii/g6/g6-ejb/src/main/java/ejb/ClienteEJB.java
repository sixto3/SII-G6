package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.g6.Cuenta;
import es.uma.g6.Fintech;
import es.uma.g6.Transaccion;
import exceptions.CuentaNoEncontradaException;
import exceptions.FaltaDeFondosException;

@Stateless
public class ClienteEJB  implements gestionCliente{

	private static final Logger LOG = Logger.getLogger(ClienteEJB.class.getCanonicalName());
	
	@PersistenceContext(name="Cliente")
	private EntityManager em;

	@Override
	public Transaccion transaccion(Cuenta cOrigen, Cuenta cDestino, float cantidad) throws CuentaNoEncontradaException, FaltaDeFondosException {
		Cuenta cuO = em.find(Cuenta.class, cOrigen.getIBAN());
		Cuenta cuD = em.find(Cuenta.class, cDestino.getIBAN());
		if(cuO == null) throw new CuentaNoEncontradaException();
		if(cuD == null) throw new CuentaNoEncontradaException();
		return null;
	}



	
}
