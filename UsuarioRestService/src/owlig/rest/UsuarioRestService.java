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

import owlig.conexion.Conexion;
import owlig.usuario.UsuarioDTO;


@Path("/CulturaApp")
public class UsuarioRestService {

	private static final Logger logger = Logger.getLogger(UsuarioRestService.class);
	private Conexion conexion = new Conexion();

// Por aca me llegan todos los Post de un submit
	@POST
	@Path("/crearusuario")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response crearLugar(@FormParam("idUsuario") long idUsuario,
			@FormParam("tipoDocumento") String tipoDocumento,
			@FormParam("primerNombre") String primerNombre,			
			@FormParam("segundoNombre") String segundoNombre,
			@FormParam("primerApellido") String primerApellido,
			@FormParam("segundoApellido") String segundoApellido,
			@FormParam("fotoUsuario") String foto,
			@FormParam("emailUsuario") String email,
			@FormParam("celular") String celular
			//@FormParam("fechaNacimineto") String fechaNacimineto
			//@Context HttpServletResponse servletResponse
			) throws IOException {
		
		boolean exito;
		UsuarioDTO usuario = new UsuarioDTO(idUsuario ,primerNombre, tipoDocumento, segundoNombre, primerApellido, segundoApellido, foto, email, celular);
		Connection conexion = Conexion.getSession();
		exito = this.conexion.crearUsuario(conexion,usuario);		
		
		return Response.ok("Estado creacion del lugar : "+exito).build();
	}
	
// Metodo para listar lugares existentes
	@GET
	@Path("/consultarusuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarLugares(/*@Context ServletContext ctx*/) throws IOException {
				
		Connection conexion = Conexion.getSession();
		List<UsuarioDTO> listausuarios = new ArrayList<UsuarioDTO>();
		try {
			listausuarios = this.conexion.consultarUsuarios(conexion);
		} catch (SQLException e) {
			logger.info("Ocurrio un error consultando los lugares",e);
			e.printStackTrace();
		}

		return Response.ok(listausuarios).build();
	}
	
	@DELETE
	@Path("/eliminarusuario/{id}")
	public Response eliminarLugar(@PathParam("id")String idUsuario /*@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version*/) throws SQLException {
			
			Connection conexion = Conexion.getSession();
			boolean exitoso = this.conexion.eliminarUsuario(conexion,idUsuario);
		
		return Response.ok("Se elimino correctamente el lugar "+exitoso).build();
	}
	
// Por aca me llegan todos los Post de un submit
		@POST
		@Path("/actualizarusuario")
		@Produces(MediaType.TEXT_HTML)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response actualizarLugar(@FormParam("idUsuario") int idUsuario,
				@FormParam("tipoDocumento") String tipoDocumento,
				@FormParam("primerNombre") String primerNombre,
				@FormParam("segundoNombre") String segundoNombre,
				@FormParam("primerApellido") String primerApellido,
				@FormParam("segundoApellido") String segundoApellido,
				@FormParam("fotoUsuario") String foto,
				@FormParam("emailUsuario") String emailUsuario,
				@FormParam("celular") String celular
				/*,
				@Context HttpServletResponse servletResponse*/) throws IOException {
			
			boolean exito;
			UsuarioDTO usuario = new UsuarioDTO(idUsuario ,primerNombre, tipoDocumento, segundoNombre, primerApellido, segundoApellido, foto, emailUsuario, celular);
			Connection conexion = Conexion.getSession();
			exito = this.conexion.actualizarUsuario(conexion,usuario);
			
			
			return Response.ok("Estado actualizacion del lugar : " +exito).build();
		}
		
}
