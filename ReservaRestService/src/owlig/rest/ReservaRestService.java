package owlig.rest;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import owlig.reserva.ReservaDTO;


@Path("/CulturaApp")
public class ReservaRestService {

	private static final Logger logger = Logger.getLogger(ReservaRestService.class);
	private Conexion conexion = new Conexion();

	@POST
	@Path("/crearreserva")
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response crearLugar(@FormParam("lugarReserva") String lugarReserva,
			@FormParam("fechaReserva") String fechaEnString,
			@FormParam("eventoReserva") String eventoReserva,
			@FormParam("usuarioReserva") String usuarioReserva,
			@FormParam("estadoreserva") String estadoreserva			
			/*@Context HttpServletResponse servletResponse*/) throws IOException {
				
		Random numeroAleatorio = new Random();
		int idReserva = numeroAleatorio.nextInt();
		DateTimeFormatter fomatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime fechareserva = new DateTime(fomatter.parseDateTime(fechaEnString));		
		boolean exito;
		ReservaDTO reserva = new ReservaDTO(idReserva, lugarReserva, fechareserva, eventoReserva, usuarioReserva, estadoreserva);
		
		Connection conexion = Conexion.getSession();
		exito = this.conexion.crearReserva(conexion,reserva);		
		
		return Response.ok("Estado creacion de la reserva : " + exito).build();
	}
	
	// Metodo para listar reservas existentes
	@GET
	@Path("/consultarreservas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarReservas(/*@Context ServletContext ctx*/) throws IOException {
				
		Connection conexion = Conexion.getSession();
		List<ReservaDTO> listaReservas = new ArrayList<ReservaDTO>();
		try {
			listaReservas = this.conexion.consultarReservas(conexion);
		} catch (SQLException e) {
			logger.info("Ocurrio un error consultando los lugares",e);
			e.printStackTrace();
		}

		return Response.ok(listaReservas).build();
	}
		
	//
	@DELETE
	@Path("/eliminarreserva/{id}")
	public Response eliminarEvento(@PathParam("id")String idReserva /*@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version*/) throws SQLException {
			
			Connection conexion = Conexion.getSession();
			boolean exitoso = this.conexion.eliminarReserva(conexion,idReserva);
		
		return Response.ok("Se elimino correctamente la reserva : " + exitoso).build();
	}
	
	// Metodo para actualizar una reserva	
	@POST
	@Path("/actualizarreserva")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response actualizarLugar(@FormParam("lugarReserva") String lugarReserva,
			@FormParam("fechaReserva") String fechaEnString,
			@FormParam("lugarEvento") String lugarEvento,
			@FormParam("eventoReserva") String eventoReserva,
			@FormParam("usuarioReserva") String usuarioReserva,
			@FormParam("estadoReserva") String estadoreserva,
			@FormParam("idReserva") int idReserva/*,
			@Context HttpServletResponse servletResponse*/) throws IOException {
		
		DateTimeFormatter fomatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime fechaReserva = new DateTime(fomatter.parseDateTime(fechaEnString));
		boolean exito;
		ReservaDTO reserva = new ReservaDTO(idReserva, lugarReserva, fechaReserva, eventoReserva, usuarioReserva, estadoreserva);
		Connection conexion = Conexion.getSession();
		exito = this.conexion.actualizarReserva(conexion,reserva);		
		
		return Response.ok("Estado actualizacion del evento : " + exito).build();
	}
	
}
