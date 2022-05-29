package es.uma.g6.Auxiliares;

public class accountHolder {
	
	
		private Boolean activeCustomer; 
		
		private String accounttype;
		
		private name name;
		
		private address direccion;
		
		
		
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
		public Boolean getActiveCustomer() {
			return activeCustomer;
		}
		public void setActiveCustomer(Boolean activeCustomer) {
			this.activeCustomer = activeCustomer;
		}
		public String getAccounttype() {
			return accounttype;
		}
		public void setAccounttype(String accounttype) {
			this.accounttype = accounttype;
		}
		
		

}
