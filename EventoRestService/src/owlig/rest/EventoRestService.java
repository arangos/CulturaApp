package owlig.rest;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import owlig.conexion.Conexion;
import owlig.evento.EventoDTO;


@Path("/CulturaApp")
public class EventoRestService {

	private static final Logger logger = Logger.getLogger(EventoRestService.class);
	private Conexion conexion = new Conexion();

	@GET
	@Path("/crearevento")
    @Produces(MediaType.TEXT_PLAIN)
	public Response crearLugar(@FormParam("nombreEvento") String nombreEvento,
			@FormParam("tipoEvento") String tipoEvento,
			@FormParam("lugarEvento") String lugarEvento,
			@FormParam("cuposEvento") int cuposEvento,
			@FormParam("descripcionEvento") String descripcionEvento,
			@FormParam("calificacion") int calificacionEvento,
			@FormParam("numeroBusquedas") int numeroBusquedas,
			@FormParam("fotoEvento") String fotoEvento,
			@FormParam("fechaEvento") DateTime fechaEvento,
			@Context HttpServletResponse servletResponse) throws IOException {
		
		boolean exito;
		EventoDTO evento = new EventoDTO(nombreEvento, tipoEvento, lugarEvento, cuposEvento, descripcionEvento, calificacionEvento, numeroBusquedas, fotoEvento, fechaEvento);
		
		Connection conexion = Conexion.getSession();
		exito = this.conexion.crearEvento(conexion,evento);
		
		
		return Response.ok("Estado creacion del evento : "+exito).build();
	}

	// Metodo para listar lugares existentes
		@GET
		@Path("/consultareventos")
		@Produces(MediaType.APPLICATION_JSON)
		public Response consultarLugares(@Context ServletContext ctx) throws IOException {
					
			Connection conexion = Conexion.getSession();
			List<EventoDTO> listaLugares = new ArrayList<EventoDTO>();
			try {
				listaLugares = this.conexion.consultarEventos(conexion);
			} catch (SQLException e) {
				logger.info("Ocurrio un error consultando los lugares",e);
				e.printStackTrace();
			}

			return Response.ok(listaLugares).build();
		}

	@PUT
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String putSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start putSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End putSomething");
        }
        return response;	
	}

	@DELETE
	@Path("/<add method name here>")
	public void deleteSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deleteSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}


        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("End deleteSomething");
        }
	}
}
