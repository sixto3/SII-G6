package es.uma.g6.backing;



import java.util.List;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import es.uma.g6.Autorizado;


import es.uma.g6.ejb.gestionAdministrador;
import es.uma.g6.ejb.gestionCliente;
import exceptions.AdministracionException;
import es.uma.g6.Individual;
import es.uma.g6.Auxiliares.*;



import javax.ws.rs.core.UriInfo;



@Path("")
public class ServicioRest {
	

	@EJB
	private gestionAdministrador gestionAdmin;
	
	@EJB
	private gestionCliente gestionCliente;
	
	@Context
	private UriInfo uriInfo;
	

	
	
	
	@Path("/healthcheck") 
	@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@GET  
	public Response estadoServicio() {     
	        return Response.ok("OK").build();   
	       	        
	    } 
	
	
	
	
	@POST
	@Path("/clients")
	@Consumes ({MediaType.APPLICATION_JSON})
	@Produces ({MediaType.APPLICATION_JSON})
	
	public Response getCustomers(searchParameters sp) throws AdministracionException {
		
	List<es.uma.g6.Auxiliares.Individual> lista = gestionAdmin.individualParametros(sp);
		return Response.ok(lista).build();
		
}
	
	
	@POST
	@Path("/products")
	@Consumes ({MediaType.APPLICATION_JSON})
	@Produces ({MediaType.APPLICATION_JSON})
	public Response getCuentas(searchParameters2 p) throws AdministracionException {
		
		
		List<es.uma.g6.Auxiliares.products2> lista = gestionAdmin.cuentasFintech(p);
			

		return Response.ok(lista).build();
	}
	

	
	
	}

