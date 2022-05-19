package es.uma.g6;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Transaccion{

	@GeneratedValue @Id
	@Column(name= "Propio")
	private int id_unq;

	@Column(name="fechaInstruccion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaInstruccion;
	@Column(name="cantidad", nullable = false)
	private float cantidad;

	@Temporal(TemporalType.DATE)
	private Date fechaEjecucion;
	@Column(name="Tipo", nullable=false)
	private String tipo;//LO PONGO COMO STRING O LO PONEMOS CON NUMEMEROS¿?
	private float comision;//LO PONGO COMO FLOAT O COMO INT¿?
	private String internacional;
	@ManyToOne
	private Divisa receptor;
	@ManyToOne
	private Divisa emisor;
	@ManyToOne
	private Cuenta destino;
	@ManyToOne
	private Cuenta origen;
	
	
	
	public Transaccion(int id_unq, Date fechaInstruccion, float cantidad, Date fechaEjecucion, String tipo, float comision, String internacional) {
		this.id_unq = id_unq;
		this.fechaInstruccion = fechaInstruccion;
		this.cantidad = cantidad;
		this.fechaEjecucion = fechaEjecucion;
		this.tipo = tipo;
		this.comision = comision;
		this.internacional = internacional;
	}
	public Transaccion() {
		super();
	}
	
	public int getId_unq() {
		return id_unq;
	}
	public void setId_unq(int id_unq) {
		this.id_unq = id_unq;
	}
	public Date getFechaInstruccion() {
		return fechaInstruccion;
	}
	public void setFechaInstruccion(Date fechaInstruccion) {
		this.fechaInstruccion = fechaInstruccion;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public String getInternacional() {
		return internacional;
	}
	public void setInternacional(String internacional) {
		this.internacional = internacional;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(cantidad);
		result = prime * result + Float.floatToIntBits(comision);
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((emisor == null) ? 0 : emisor.hashCode());
		result = prime * result + ((fechaEjecucion == null) ? 0 : fechaEjecucion.hashCode());
		result = prime * result + ((fechaInstruccion == null) ? 0 : fechaInstruccion.hashCode());
		result = prime * result + id_unq;
		result = prime * result + ((internacional == null) ? 0 : internacional.hashCode());
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
		result = prime * result + ((receptor == null) ? 0 : receptor.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		if (Float.floatToIntBits(cantidad) != Float.floatToIntBits(other.cantidad))
			return false;
		if (Float.floatToIntBits(comision) != Float.floatToIntBits(other.comision))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (emisor == null) {
			if (other.emisor != null)
				return false;
		} else if (!emisor.equals(other.emisor))
			return false;
		if (fechaEjecucion == null) {
			if (other.fechaEjecucion != null)
				return false;
		} else if (!fechaEjecucion.equals(other.fechaEjecucion))
			return false;
		if (fechaInstruccion == null) {
			if (other.fechaInstruccion != null)
				return false;
		} else if (!fechaInstruccion.equals(other.fechaInstruccion))
			return false;
		if (id_unq != other.id_unq)
			return false;
		if (internacional == null) {
			if (other.internacional != null)
				return false;
		} else if (!internacional.equals(other.internacional))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		if (receptor == null) {
			if (other.receptor != null)
				return false;
		} else if (!receptor.equals(other.receptor))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ID unico: "+this.id_unq+" fecha_Instruccion: "+this.fechaInstruccion+" cantidad: "+this.cantidad+" fecha_Ejecución: "+this.fechaEjecucion+" tipo: "+this.tipo+" comision: "+this.comision+" internacional: "+this.internacional;
	}
	
}