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
//	    Statement statement = null;
	    
	    try{
	    	
	        Class.forName(driver).newInstance();
	        conn =  DriverManager.getConnection(url+dbName,userName,password);
//	        statement = conn.createStatement();
	      	        
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
				usurio.setIdUsuario(rs.getInt("IDusuario"));
				usurio.setPrimerNombre(rs.getString("Nombre"));
				usurio.setPrimerApellido(rs.getString("PrimerApellido"));
				usurio.setSegundoApellido(rs.getString("SegundoApellido"));
				usurio.setFoto(rs.getString("Foto"));
				usurio.setEmail(rs.getString("Email"));
				usurio.setCelular(rs.getInt("Celular"));
				usurio.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
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
		      preparedStmt.setString (1, usuario.getPrimerNombre());
		      preparedStmt.setString (2, usuario.getPrimerApellido());
		      preparedStmt.setString (3, usuario.getSegundoApellido());
		      preparedStmt.setString (4, usuario.getFoto());
		      preparedStmt.setString (5, usuario.getEmail());
		      preparedStmt.setInt    (6, usuario.getCelular());
		      preparedStmt.setString (7, usuario.getSegundoNombre());
		 
		      preparedStmt.execute();
		      exito = true;
		      logger.info("Lugar Insertado en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la insercion del Lugar "+e);			
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
		      preparedStmt.setString (1, lugar.getPrimerNombre());
		      preparedStmt.setString (2, lugar.getPrimerApellido());
		      preparedStmt.setString (3, lugar.getSegundoApellido());
		      preparedStmt.setString (4, lugar.getFoto());
		      preparedStmt.setString (5, lugar.getEmail());
		      preparedStmt.setInt    (6, lugar.getCelular());
		      preparedStmt.setString (7, lugar.getSegundoNombre());
		      preparedStmt.setInt    (8, lugar.getIdUsuario());
		      
		      preparedStmt.execute();
		      exito = true;
//		      conexion.close();
		      logger.info("Lugar Insertado en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la insercion del Lugar "+e);			
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
