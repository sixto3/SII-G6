package es.uma.g6.ejb;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.mail.imap.protocol.ListInfo;

import es.uma.g6.Cuenta;
import es.uma.g6.DepositadaEn;
import es.uma.g6.Fintech;
import es.uma.g6.Pooled;
import es.uma.g6.Referencia;
import es.uma.g6.Segregada;
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
		String tipoOrigen = tipoDeCuenta(cOrigen);
		String tipoDestino = tipoDeCuenta(cDestino);
	
		if(tipoOrigen == null)throw new CuentaNoEncontradaException();
		if(tipoDestino == null)throw new CuentaNoEncontradaException();
		Transaccion t = new Transaccion();
		
		List<Transaccion> tsOrigen = cOrigen.getTransacciones_origen();
		List<Transaccion> tsDestino = cDestino.getTransacciones_destino();
		
		if(tipoOrigen.equals("segregada")) {
			Segregada cOsg = em.find(Segregada.class, cOrigen.getIBAN());
			Referencia rDestino = em.find(Referencia.class, cDestino.getIBAN());
			
			if(cOsg.getRef().getSaldo() < cantidad) throw new FaltaDeFondosException();
			t.setCantidad(cantidad);
			t.setComision((float) cOsg.getComision());
			
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			t.setFechaEjecucion(date);
			t.setFechaInstruccion(date);
			
			t.setId_unq((int) Math.random());
			t.setTipo("De "+tipoOrigen+" a "+tipoDestino);
			
			if(!cOsg.getRef().getPais().equals(rDestino.getPais())) t.setInternacional("internacional");
			else t.setInternacional("nacional");
			
			tsOrigen.add(t);
			tsDestino.add(t);
			
			if(cOsg.getRef().getDivisas().equals(rDestino.getDivisas())) {
				cOsg.getRef().setSaldo((float) (cOsg.getRef().getSaldo() - cantidad));
				rDestino.setSaldo((float) (rDestino.getSaldo() + cantidad));
			}else {
				cOsg.getRef().setSaldo((float) (cOsg.getRef().getSaldo() - cantidad));
				rDestino.setSaldo((float) (rDestino.getSaldo() + (cantidad * rDestino.getDivisas().getCambioEuro())));
			}
			em.merge(cOsg);
			em.merge(rDestino);
			
		}if(tipoOrigen.equals("pooled")) {
			Pooled cOpl = em.find(Pooled.class, cOrigen.getIBAN());
			Referencia rDestino = em.find(Referencia.class, cDestino.getIBAN());
		
			DepositadaEn dep = hayDineroSuficienteEnAlMenosUna(cOpl.getDepositada_pooled(), (int) cantidad);
			
			if(dep == null) throw new FaltaDeFondosException();
			
			t.setCantidad(cantidad);
			t.setComision(0);
			
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			t.setFechaEjecucion(date);
			t.setFechaInstruccion(date);
			
			t.setId_unq((int) Math.random());
			t.setTipo("De "+tipoOrigen+" a "+tipoDestino);
			
			if(!dep.getReferencia().getPais().equals(rDestino.getPais())) t.setInternacional("internacional");
			else t.setInternacional("nacional");
			
			tsOrigen.add(t);
			tsDestino.add(t);
			
			if(dep.getReferencia().getDivisas().equals(rDestino.getDivisas())) {
				dep.getReferencia().setSaldo((float) (dep.getReferencia().getSaldo() - cantidad));
				rDestino.setSaldo((float) (rDestino.getSaldo() + cantidad));
			}else {
				dep.getReferencia().setSaldo((float) (dep.getReferencia().getSaldo() - cantidad));
				rDestino.setSaldo((float) (rDestino.getSaldo() + (cantidad * rDestino.getDivisas().getCambioEuro())));
			}
			em.merge(dep);
			em.merge(cOpl);
			em.merge(rDestino);
			
		}if(tipoOrigen.equals("externa")) {
			Cuenta cOe = em.find(Cuenta.class, cOrigen.getIBAN());
			Referencia rOrigen = em.find(Referencia.class, cOrigen.getIBAN());
			Referencia rDestino = em.find(Referencia.class, cDestino.getIBAN());
			
			if(rOrigen.getSaldo() < cantidad) throw new FaltaDeFondosException();
			
			t.setCantidad(cantidad);
			t.setComision(0);
			
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			t.setFechaEjecucion(date);
			t.setFechaInstruccion(date);
			
			t.setId_unq((int) Math.random());
			t.setTipo("De "+tipoOrigen+" a "+tipoDestino);
			
			if(rOrigen.getPais().equals(rDestino.getPais())) t.setInternacional("internacional");
			else t.setInternacional("nacional");
			
			tsOrigen.add(t);
			tsDestino.add(t);
			
			if(rOrigen.getDivisas().equals(rDestino.getDivisas())) {
				rOrigen.setSaldo((float) (rOrigen.getSaldo() - cantidad));
				rDestino.setSaldo((float) (rDestino.getSaldo() + cantidad));
			}else {
				rOrigen.setSaldo((float) (rOrigen.getSaldo() - cantidad));
				rDestino.setSaldo((float) (rDestino.getSaldo() + (cantidad * rDestino.getDivisas().getCambioEuro())));
			}
			em.merge(rOrigen);
			em.merge(rDestino);
			
		}
		
		
		
		
		
		em.merge(tsOrigen);
		em.merge(tsDestino);
		em.persist(t);
		return t;
	}
	
	private String tipoDeCuenta(Cuenta c) {
		String res = "";
		if(em.find(Segregada.class, c.getIBAN()) != null) res = "segregada";
		else if(em.find(Pooled.class, c.getIBAN()) != null) res = "pooled";
		else if(em.find(Cuenta.class, c.getIBAN()) != null) res = "externa";
		else res = null;
		return res;
	}
	
	private DepositadaEn hayDineroSuficienteEnAlMenosUna(List<DepositadaEn> lista, int cantidad) {
		DepositadaEn d = null;
		int i = 0;
		for (DepositadaEn depositada : lista) {
			if(depositada.getSaldo()>= cantidad && i==0) {
				d = depositada;
				i++;
			}
		}
		return d;
	}
	
}


