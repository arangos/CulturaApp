package owlig.rest;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import owlig.conexion.Conexion;
import owlig.evento.EventoDTO;


@Path("/CulturaApp")
public class EventoRestService {

	private static final Logger logger = Logger.getLogger(EventoRestService.class);
	private Conexion conexion = new Conexion();

	@POST
	@Path("/crearevento")
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response crearLugar(@FormParam("nombreEvento") String nombreEvento,
			@FormParam("tipoEvento") String tipoEvento,
			@FormParam("lugarEvento") String lugarEvento,
			@FormParam("cuposEvento") int cuposEvento,
			@FormParam("descripcionEvento") String descripcionEvento,
			@FormParam("calificacion") int calificacionEvento,
			@FormParam("numeroBusquedas") int numeroBusquedas,
			@FormParam("fotoEvento") String fotoEvento,
			@FormParam("fechaEvento") String fechaEnString/*,
			@Context HttpServletResponse servletResponse*/) throws IOException {
		
		DateTimeFormatter fomatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime fechaEvento = new DateTime(fomatter.parseDateTime(fechaEnString));
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
	public Response consultarEventos(/*@Context ServletContext ctx*/) throws IOException {
				
		Connection conexion = Conexion.getSession();
		List<EventoDTO> listaEventos = new ArrayList<EventoDTO>();
		try {
			listaEventos = this.conexion.consultarEventos(conexion);
		} catch (SQLException e) {
			logger.info("Ocurrio un error consultando los lugares",e);
			e.printStackTrace();
		}

		return Response.ok(listaEventos).build();
	}

	@DELETE
	@Path("/eliminarevento/{id}")
	public Response eliminarEvento(@PathParam("id")String idLugar /*@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version*/) throws SQLException {
			
			Connection conexion = Conexion.getSession();
			boolean exitoso = this.conexion.eliminarLugar(conexion,idLugar);
		
		return Response.ok("Se elimino correctamente el evento : "+exitoso).build();
	}
	
	// Por aca me llegan todos los Post de un submit
				
	@POST
	@Path("/actualizarevento")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response actualizarLugar(@FormParam("nombreEvento") String nombreEvento,
			@FormParam("tipoEvento") String tipoEvento,
			@FormParam("lugarEvento") String lugarEvento,
			@FormParam("cuposEvento") int cuposEvento,
			@FormParam("descripcionEvento") String descripcionEvento,
			@FormParam("calificacion") int calificacionEvento,
			@FormParam("numeroBusquedas") int numeroBusquedas,
			@FormParam("fotoEvento") String fotoEvento,
			@FormParam("fechaEvento") String fechaEnString,
			@FormParam("idEvento") int idEvento/*,
			@Context HttpServletResponse servletResponse*/) throws IOException {
		
		DateTimeFormatter fomatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime fechaEvento = new DateTime(fomatter.parseDateTime(fechaEnString));
		boolean exito;
		EventoDTO evento = new EventoDTO(nombreEvento, tipoEvento, lugarEvento, cuposEvento, descripcionEvento, calificacionEvento, numeroBusquedas, fotoEvento, fechaEvento);
		evento.setIdEvento(idEvento);
		Connection conexion = Conexion.getSession();
		exito = this.conexion.actualizarEvento(conexion,evento);		
		
		return Response.ok("Estado actualizacion del evento : "+exito).build();
	}
}
