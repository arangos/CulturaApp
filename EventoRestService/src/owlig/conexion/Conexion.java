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
import org.joda.time.DateTime;

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
//	    String url = "jdbc:mysql://52.25.186.249:3306/";
	    String url = "jdbc:mysql://127.0.0.1:3306/";
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
		
		List<EventoDTO> listaEventos = new ArrayList<EventoDTO>();
        ResultSet rs;
        Statement statement = conexion.createStatement();
		try {
			rs = statement.executeQuery("SELECT * FROM  `Evento`");
			while (rs.next()) {
				
				EventoDTO evento = new EventoDTO();
				evento.setIdEvento(rs.getInt("ID"));
				evento.setNombreEvento(rs.getString("NOMBRE"));
				evento.setTipoEvento(rs.getString("TIPO"));
				evento.setLugarEvento(rs.getString("LUGAR"));
				evento.setCuposEvento(rs.getInt("CUPOS"));
				evento.setDescrpcionEvento(rs.getString("DESCRIPCION"));
				evento.setCalificacionEvento(rs.getInt("CALIFICACION"));
				evento.setNumeroBusquedasEvento(rs.getInt("NUMEROBUSQUEDAS"));
				evento.setFotoEvento(rs.getString("FOTO"));
				evento.setFechaEvento(new DateTime(rs.getDate("FECHAEVENTO").getTime()));
				listaEventos.add(evento);
	        }

		} catch (SQLException e) {				
			e.printStackTrace();
		}		
		return listaEventos;			
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
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/INSERT_EVENTO.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
		      preparedStmt.setString (1, evento.getNombreEvento());
		      preparedStmt.setString (2, evento.getTipoEvento());
		      preparedStmt.setString (3, evento.getLugarEvento());
		      preparedStmt.setInt 	 (4, evento.getCuposEvento());
		      preparedStmt.setString (5, evento.getDescrpcionEvento());
		      preparedStmt.setInt    (6, evento.getCalificacionEvento());
		      preparedStmt.setInt    (7, evento.getNumeroBusquedasEvento());
		      preparedStmt.setString (8, evento.getFotoEvento());
		      preparedStmt.setDate   (9, new java.sql.Date(evento.getFechaEvento().toDate().getTime()));
		    		 		 
		      preparedStmt.execute();
		      exito = true;
		      
		      logger.info("Evento Insertado en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la insercion del Lugar "+e);			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return exito;
	}
	
	public boolean actualizarEvento(Connection conexion,EventoDTO evento){
		boolean exito = false;
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/UPDATE_EVENTO.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
  			  preparedStmt.setString (1, evento.getNombreEvento());
		      preparedStmt.setString (2, evento.getTipoEvento());
		      preparedStmt.setString (3, evento.getLugarEvento());
		      preparedStmt.setInt 	 (4, evento.getCuposEvento());
		      preparedStmt.setString (5, evento.getDescrpcionEvento());
		      preparedStmt.setInt    (6, evento.getCalificacionEvento());
		      preparedStmt.setInt    (7, evento.getNumeroBusquedasEvento());
		      preparedStmt.setString (8, evento.getFotoEvento());
		      preparedStmt.setDate   (9, new java.sql.Date(evento.getFechaEvento().toDate().getTime()));
		      preparedStmt.setInt    (10, evento.getIdEvento());
		      
		      preparedStmt.execute();
		      exito = true;
		      logger.info("Evento actualizado en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la actualizacion del Evento "+e);			
		} catch (IOException e) {
			logger.info("Ocurrio una Exepcion en la lectura del archivo sql para actualizar el evento "+e);			
		}
		return exito;
	}
	
	//Metodo para eliminar lugares de la BD
	public boolean eliminarLugar(Connection conexion, String idEvento) throws SQLException{
		boolean exitoso = false;
		
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/DELETE_EVENTO.sql");
			PreparedStatement preparedStmt = conexion.prepareStatement(sql);
			preparedStmt.setString (1, idEvento);
			preparedStmt.execute();
			exitoso = true;
			logger.info("Se elimino el registro con ID : "+idEvento +" exitosamente de la BD");
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
