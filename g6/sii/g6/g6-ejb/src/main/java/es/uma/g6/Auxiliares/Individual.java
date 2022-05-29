package es.uma.g6.Auxiliares;

import java.util.List;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Individual {

	
	private List<products> productos;
	
	private String activeCustomer;
	
	private int identificationNumber;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	private name name;
	
	
	private address direccion;


	public List<products> getProductos() {
		return productos;
	}


	public void setProductos(List<products> productos) {
		this.productos = productos;
	}


	public String getActiveCustomer() {
		return activeCustomer;
	}


	public void setActiveCustomer(String activeCustomer) {
		this.activeCustomer = activeCustomer;
	}


	public int getIdentificationNumber() {
		return identificationNumber;
	}


	public void setIdentificationNumber(int i) {
		this.identificationNumber = i;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public name getName() {
		return name;
	}


	public void setName(name name) {
		this.name = name;
	}


	public address getDireccion() {
		return direccion;
	}


	public void setDireccion(address direccion) {
		this.direccion = direccion;
	}
	
	
	
	
	
	
	
	
}
