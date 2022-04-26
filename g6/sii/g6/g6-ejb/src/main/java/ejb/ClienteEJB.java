package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.g6.Cuenta;
import es.uma.g6.Fintech;
import exceptions.CuentaNoEncontradaException;

@Stateless
public class ClienteEJB  implements gestionCliente{

	private static final Logger LOG = Logger.getLogger(ClienteEJB.class.getCanonicalName());
	
	@PersistenceContext(name="Cliente")
	private EntityManager em;

	@Override
	public void transaccion(Cuenta cuentaOrigen, Cuenta cuentaDestino, int cantidad) throws CuentaNoEncontradaException {
		Cuenta cuO = em.find(Cuenta.class, cuentaOrigen.getIBAN());
		Cuenta cuD = em.find(Cuenta.class, cuentaDestino.getIBAN());
		if(cuO == null) throw new CuentaNoEncontradaException();
		if(cuD == null) throw new CuentaNoEncontradaException();
	}



	
}
