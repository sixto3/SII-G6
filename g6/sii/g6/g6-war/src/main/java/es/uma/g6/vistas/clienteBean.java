package es.uma.g6.vistas;
import javax.inject.Inject;

import es.uma.g6.ejb.gestionAdministrador;
import es.uma.g6.ejb.gestionCliente;
import exceptions.CuentaNoEncontradaException;
import exceptions.FaltaDeFondosException;
import es.uma.g6.*;

public class clienteBean {
    
    @Inject
    private gestionAdministrador ga;
    @Inject
    private gestionCliente gc;
    @Inject
    private Cuenta cuenta1;
    @Inject
    private Cuenta cuenta2;
    private float cantidad;
    private String ibanOrigen;
    private String ibanDestino;
    private long swiftOrigen;
    
    public clienteBean() {
    	cuenta1 = new Cuenta();
    	cuenta2 = new Cuenta();
    }

    public String getIbanOrigen() {
		return ibanOrigen;
	}

	public void setIbanOrigen(String ibanOrigen) {
		this.ibanOrigen = ibanOrigen;
	}

	public String getIbanDestino() {
		return ibanDestino;
	}

	public void setIbanDestino(String ibanDestino) {
		this.ibanDestino = ibanDestino;
	}

	public long getSwiftOrigen() {
		return swiftOrigen;
	}

	public void setSwiftOrigen(long swiftOrigen) {
		this.swiftOrigen = swiftOrigen;
	}

	public long getSwiftDestino() {
		return swiftDestino;
	}

	public void setSwiftDestino(long swiftDestino) {
		this.swiftDestino = swiftDestino;
	}

	private long swiftDestino;
    
 

    public void setCantidad(float cantidad){
        this.cantidad=cantidad;
    }

    public String transaccion(String cuentaOrigen,String cuentaDestino, float cantidad) throws CuentaNoEncontradaException, FaltaDeFondosException{
        gc.transaccion(cuenta1,cuenta2,cantidad);
        return "clienteVista.xhtml";
    }


}
