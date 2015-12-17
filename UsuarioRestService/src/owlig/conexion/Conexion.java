package owlig.conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import owlig.rest.UsuarioRestService;
import owlig.usuario.UsuarioDTO;
import owlig.utilitario.MetodosUtilitarios;

/**
 * @author Arangos
 *
 */
public class Conexion {
	
	private static final Logger logger = Logger.getLogger(UsuarioRestService.class);
	private static Connection conexion = null;
	
	//Para crear instacia unica de conexion a BD
	public static Connection getSession(){
		if(conexion == null){
			try{
				conexion = startConnection();
			}catch(Throwable e){
				e.printStackTrace();
			}
		}
		return conexion;
	}
	
	//Aca se establece la conexion llamada por el getSession
	public static Connection startConnection()	{
		
	    Connection conn = null;
	    String url = "jdbc:mysql://52.25.186.249:3306/";
	    String dbName = "CulturaApp";
	    String driver = "com.mysql.jdbc.Driver";
	    String userName = "root";
	    String password = "motelapp";
	    
	    try{
	    	
	        Class.forName(driver).newInstance();
	        conn =  DriverManager.getConnection(url+dbName,userName,password);
	      	        
	        System.out.println("Connected to the database");
	        logger.info("Connected to the database");
	    }catch (Exception e){
	    	logger.info("Connected to the database");
	    	System.out.println("No connected to the database");
	    	e.printStackTrace();
	    }
	    return conn;
	}		
	
	//Para consultas de lugares
	public List<UsuarioDTO> consultarUsuarios(Connection conexion) throws SQLException{
		
		List<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
        ResultSet rs;
        Statement statement = conexion.createStatement();
		try {
			rs = statement.executeQuery("SELECT * FROM  `Usuario`");
			while (rs.next()) {
				
				UsuarioDTO usurio = new UsuarioDTO();
				usurio.setIdUsuario(rs.getInt("IDUSUARIO"));
				usurio.setPrimerNombre(rs.getString("TTPO_DOCUMENTO"));
				usurio.setPrimerApellido(rs.getString("PRIMER_NOMBRE"));
				usurio.setSegundoApellido(rs.getString("SEGUNDO_NOMBRE"));
				usurio.setSegundoNombre(rs.getString("PRIMER_APELLIDO"));
				usurio.setSegundoNombre(rs.getString("SEGUNDO_APELLIDO"));
				usurio.setFoto(rs.getString("FOTO"));
				usurio.setEmail(rs.getString("EMAIL"));
				usurio.setCelular(rs.getString("CELULAR"));
				listaUsuarios.add(usurio);
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listaUsuarios;			
	}	
	
	/**
	 * Metodo para insertar los nuevos lugares en BD
	 * @param statement
	 * @param lugar
	 * @return
	 */
	public boolean crearUsuario(Connection conexion,UsuarioDTO usuario){
		boolean exito = false;
				
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/INSERT_USUARIO.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
  			  preparedStmt.setLong   (1, usuario.getIdUsuario());
  			  preparedStmt.setString (2, usuario.getTipoDocumento());
  			  preparedStmt.setString (3, usuario.getPrimerNombre());
  			  preparedStmt.setString (4, usuario.getSegundoNombre());
		      preparedStmt.setString (5, usuario.getPrimerApellido());
		      preparedStmt.setString (6, usuario.getSegundoApellido());
		      preparedStmt.setString (7, usuario.getFoto());
		      preparedStmt.setString (8, usuario.getEmail());
		      preparedStmt.setString  (9, usuario.getCelular());		      
		 
		      preparedStmt.execute();
		      exito = true;
		      logger.info("Evento Insertado en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la insercion del Evento "+e);			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return exito;
	}
	
	public boolean actualizarUsuario(Connection conexion,UsuarioDTO lugar){
		boolean exito = false;
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/UPDATE_USUARIO.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
  			  preparedStmt.setString (1, lugar.getTipoDocumento());
		      preparedStmt.setString (2, lugar.getPrimerNombre());
		      preparedStmt.setString (8, lugar.getSegundoNombre());
		      preparedStmt.setString (3, lugar.getPrimerApellido());
		      preparedStmt.setString (4, lugar.getSegundoApellido());
		      preparedStmt.setString (5, lugar.getFoto());
		      preparedStmt.setString (6, lugar.getEmail());
		      preparedStmt.setString (7, lugar.getCelular());		      
		      preparedStmt.setLong   (9, lugar.getIdUsuario());
		      
		      preparedStmt.execute();
		      exito = true;
		      logger.info("Evento actualizado en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la actualizacion del usuario "+e);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return exito;
	}
	
	//Metodo para eliminar lugares de la BD
	public boolean eliminarUsuario(Connection conexion, String idUsuario) throws SQLException{
		boolean exitoso = false;
		
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/DELETE_USUARIO.sql");
			PreparedStatement preparedStmt = conexion.prepareStatement(sql);
			preparedStmt.setString (1, idUsuario);
			preparedStmt.execute();
			exitoso = true;		
			logger.info("Se elimino el registro con ID : "+idUsuario +" exitosamente de la BD");
		} catch (IOException e) {
			logger.info("Ocurrio un error leyendo el archivo .SQL");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info("Ocurrio un error eliminando el registro de la base de datos : ", e);
			e.printStackTrace();
		}
		
		return exitoso;
	}
	
}
