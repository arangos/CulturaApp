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

import owlig.evento.EventoDTO;
import owlig.utilitario.MetodosUtilitarios;

/**
 * @author Arangos
 *
 */
public class Conexion {
	
	private static final Logger logger = Logger.getLogger(Conexion.class);
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
	public List<EventoDTO> consultarEventos(Connection conexion) throws SQLException{
		
		List<EventoDTO> listaLugares = new ArrayList<EventoDTO>();
        ResultSet rs;
        Statement statement = conexion.createStatement();
		try {
			rs = statement.executeQuery("SELECT * FROM  `Lugar`");
			while (rs.next()) {
				
				EventoDTO lugar = new EventoDTO();
				lugar.setIdEvento(rs.getInt("IDLUGAR"));
				lugar.setNombreEvento(rs.getString("NOMBRE"));
				lugar.setTipoEvento(rs.getString("DIRECCION"));
				lugar.setLugarEvento(rs.getString("COORDENADAS"));
				lugar.setCuposEvento(rs.getInt("FOTO"));
				lugar.setDescrpcionEvento(rs.getString("CALIFICACION"));
				lugar.setCalificacionEvento(rs.getInt("CUPOS"));
				lugar.setNumeroBusquedasEvento(rs.getInt("CUPOS"));
				lugar.setDescrpcionEvento(rs.getString("CALIFICACION"));
				listaLugares.add(lugar);
	        }
//			statement.close();

		} catch (SQLException e) {
			//aca logger de errores
			
			e.printStackTrace();
		}
		
		return listaLugares;
			
	}
	
	
	/**
	 * Metodo para insertar los nuevos lugares en BD
	 * @param statement
	 * @param lugar
	 * @return
	 */
	public boolean crearEvento(Connection conexion,EventoDTO evento){
		boolean exito = false;
				
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/INSERT_LUGAR.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
		      preparedStmt.setString (1, evento.getNombreEvento());
		      preparedStmt.setString (2, evento.getDescrpcionEvento());
		      preparedStmt.setString (3, evento.getFotoEvento());
		      preparedStmt.setString (4, evento.getLugarEvento());
		      preparedStmt.setInt    (5, evento.getCalificacionEvento());
		      preparedStmt.setInt    (6, evento.getCuposEvento());
		 
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
	
	public boolean actualizarLugar(Connection conexion,EventoDTO evento){
		boolean exito = false;
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/UPDATE_LUGAR.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
  			 preparedStmt.setString (1, evento.getNombreEvento());
		      preparedStmt.setString (2, evento.getDescrpcionEvento());
		      preparedStmt.setString (3, evento.getFotoEvento());
		      preparedStmt.setString (4, evento.getLugarEvento());
		      preparedStmt.setInt    (5, evento.getCalificacionEvento());
		      preparedStmt.setInt    (6, evento.getCuposEvento());
		      
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
	public boolean eliminarLugar(Connection conexion, String idLugar) throws SQLException{
		boolean exitoso = false;
		
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/DELETE_LUGAR.sql");
			PreparedStatement preparedStmt = conexion.prepareStatement(sql);
			preparedStmt.setString (1, idLugar);
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
