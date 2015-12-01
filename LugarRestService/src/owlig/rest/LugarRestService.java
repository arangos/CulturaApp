package owlig.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import owlig.conexion.Conexion;
import owlig.lugar.LugarDTO;

@Path("/CulturaApp")
public class LugarRestService {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	Motel animalService;
	private Conexion conexion = new Conexion();
	
	public LugarRestService(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		animalService = new Motel("", "", 0, 0, "");
	}

	private static final Logger logger = Logger.getLogger(LugarRestService.class);

	// Por aca me llegan todos los Post de un submit
	@POST
	@Path("/crearlugar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response crearLugar(@FormParam("nombreLugar") String nombreLugar,
			@FormParam("direccion") String direccion,
			@FormParam("coordenadas") String coordenadas,
			@FormParam("foto") String foto,
			@FormParam("calificacion") int calificacion,
			@FormParam("cupos") int cupos,
			@Context HttpServletResponse servletResponse) throws IOException {
		
		boolean exito;
		LugarDTO lugar = new LugarDTO(nombreLugar, direccion, coordenadas, foto, calificacion, cupos);
		Connection conexion = Conexion.getSession();
		exito = this.conexion.crearLugar(conexion,lugar);
		
		
		return Response.ok("Estado creacion del lugar : "+exito).build();
	}

	// Metodo para listar lugares existentes
	@GET
	@Path("/consultarlugares")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarLugares(@Context ServletContext ctx) throws IOException {
				
		Connection conexion = Conexion.getSession();
		List<LugarDTO> listaLugares = new ArrayList<LugarDTO>();
		try {
			listaLugares = this.conexion.consultarLugares(conexion);
		} catch (SQLException e) {
			logger.info("Ocurrio un error consultando los lugares",e);
			e.printStackTrace();
		}

		return Response.ok(listaLugares).build();
	}
	
	@DELETE
	@Path("/eliminarevento/{id}")
	public Response eliminarLugar(@PathParam("id")String idLugar /*@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version*/) throws SQLException {
			
			Connection conexion = Conexion.getSession();
			boolean exitoso = this.conexion.eliminarLugar(conexion,idLugar);
		
		return Response.ok("Se elimino correctamente el lugar"+exitoso).build();
	}
	
	// Por aca me llegan todos los Post de un submit
		@POST
		@Path("/actualizarlugar")
		@Produces(MediaType.TEXT_HTML)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response actualizarLugar(@FormParam("nombreLugar") String nombreLugar,
				@FormParam("direccion") String direccion,
				@FormParam("coordenadas") String coordenadas,
				@FormParam("foto") String foto,
				@FormParam("calificacion") int calificacion,
				@FormParam("cupos") int cupos,
				@FormParam("idlugar") int idLugar,
				@Context HttpServletResponse servletResponse) throws IOException {
			
			boolean exito;
			LugarDTO lugar = new LugarDTO(nombreLugar, direccion, coordenadas, foto, calificacion, cupos);
			lugar.setIdLugar(idLugar);
			Connection conexion = Conexion.getSession();
			exito = this.conexion.actualizarLugar(conexion,lugar);
			
			
			return Response.ok("Estado actualizacion del lugar : "+exito).build();
		}
	
}
