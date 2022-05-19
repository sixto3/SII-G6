package es.uma.g6;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import es.uma.g6.Depositada_enId;
@Embeddable
public class Depositada_enId implements Serializable{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		Referencia referencia;
		@ManyToOne
		Pooled pooled;
		
		
		public Depositada_enId() {
			
		}
		public Depositada_enId(Referencia referencia, Pooled pooled) {
			super();
			this.referencia = referencia;
			this.pooled = pooled;
		}
		
		public Referencia getReferencia() {
			return referencia;
		}
		public void setReferencia(Referencia referencia) {
			this.referencia = referencia;
		}
		public Pooled getPooled() {
			return pooled;
		}
		public void setPooled(Pooled pooled) {
			this.pooled = pooled;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(pooled, referencia);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Depositada_enId other = (Depositada_enId) obj;
			return Objects.equals(pooled, other.pooled) && Objects.equals(referencia, other.referencia);
		}
	}
	

