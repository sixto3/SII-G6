package es.uma.g6.ejb;

import es.uma.g6.*;
import exceptions.CuentaNoEncontradaException;
import exceptions.FaltaDeFondosException;

public interface gestionCliente {
	/*La aplicación permitirá a un cliente que sea persona física y un autorizado a una cuenta 
	  cuyo cliente sea persona jurídica realizar transacciones entre cuentas bancarias. El usuario 
	  necesita escoger una cuenta de origen (de aquellas a las que tiene acceso) e indicar una cuenta
	  de destino mediante su IBAN. Internamente Los saldos deberán actualizarse adecuadamente en las 
	  cuentas de eBury y en las cuentas asociadas a las de eBury.*/
	public Transaccion transaccion(Cuenta cOrigen, Cuenta cDestino, float cantidad) throws CuentaNoEncontradaException, FaltaDeFondosException;
}
