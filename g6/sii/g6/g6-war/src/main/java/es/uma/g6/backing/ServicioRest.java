package es.uma.g6.backing;



import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.uma.g6.Cliente;
import es.uma.g6.ejb.gestionAdministrador;
import es.uma.g6.ejb.gestionCliente;

import javax.ws.rs.core.UriInfo;



@Path("")
public class ServicioRest {
	
	
	@EJB
	private gestionAdministrador gestionAdmin;
	
	@EJB
	private gestionCliente gestionCliente;
	
	@Context
	private UriInfo uriInfo;
	
	private EntityManager em;
	
	
	
	@Path("/healthcheck") 
	@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@GET  
	public Response estadoServicio() {     
	        return Response.ok("OK").build();   
	    } 
	
	
	
	
	@POST
	@Path("/clients")
	@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	
	public Response getCustomers(Cliente cliente) {
		
		//query = em.createQuery("select I.IBAN ............ from  ...left join");
		return Response.ok("OK").build();   
}
	}

