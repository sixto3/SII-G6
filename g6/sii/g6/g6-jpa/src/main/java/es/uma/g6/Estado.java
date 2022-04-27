package es.uma.g6;

public enum Estado {
	
	 Estado ("baja","bloqueado","activo");
	
	private String Baja; 
	
	private String Bloqueado;
	
	private String Activo;

	private Estado(String baja, String bloqueado, String activo) {
		Baja = baja;
		Bloqueado = bloqueado;
		Activo = activo;
	}

	Estado(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getBaja() {
		return Baja;
	}

	public void setBaja(String baja) {
		Baja = baja;
	}

	public String getBloqueado() {
		return Bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		Bloqueado = bloqueado;
	}

	public String getActivo() {
		return Activo;
	}

	public void setActivo(String activo) {
		Activo = activo;
	}

	
	
	
	
	

}
