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

import owlig.reserva.ReservaDTO;
import owlig.rest.ReservaRestService;
import owlig.utilitario.MetodosUtilitarios;

/**
 * @author Arangos
 *
 */
public class Conexion {
	
	private static final Logger logger = Logger.getLogger(ReservaRestService.class);
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
	
	//Para consultas de reservas
	public List<ReservaDTO> consultarReservas(Connection conexion) throws SQLException{
		
		List<ReservaDTO> listaReservas = new ArrayList<ReservaDTO>();
        ResultSet rs;
        Statement statement = conexion.createStatement();
		try {
			rs = statement.executeQuery("SELECT * FROM  `Reservas`");
			while (rs.next()) {
				
				ReservaDTO reserva = new ReservaDTO();
				reserva.setIdReserva(rs.getInt("ID"));
				reserva.setLugarReserva(rs.getString("LUGAR"));
				reserva.setFechareserva(new DateTime(rs.getDate("FECHA").getTime()));
				reserva.setEventoReserva(rs.getString("EVENTO"));
				reserva.setUsuario(rs.getString("USUARIO"));
				reserva.setEstado(rs.getString("ESTADO"));				
				listaReservas.add(reserva);
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listaReservas;			
	}	
	
	/**
	 * Metodo para insertar las nuevas reservas en BD
	 * @param statement
	 * @param reserva
	 * @return boolean
	 */
	public boolean crearReserva(Connection conexion,ReservaDTO reserva){
		boolean exito = false;
				
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/INSERT_RESERVA.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
  			  preparedStmt.setInt    (1, reserva.getIdReserva());
  			  preparedStmt.setString (2, reserva.getLugarReserva());
  			  preparedStmt.setDate   (3, new java.sql.Date(reserva.getFechareserva().toDate().getTime()));
  			  preparedStmt.setString (4, reserva.getEventoReserva());
		      preparedStmt.setString (5, reserva.getUsuario());
		      preparedStmt.setString (6, reserva.getEstado());
		      
		      preparedStmt.execute();
		      exito = true;
		      logger.info("Reserva Insertada en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la insercion de la reserva "+e);			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return exito;
	}
	
	//Metodo que se encarga de actulizar datos de las reservas
	public boolean actualizarReserva(Connection conexion,ReservaDTO reserva){
		boolean exito = false;
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/UPDATE_RESERVA.sql");
			
  			  PreparedStatement preparedStmt = conexion.prepareStatement(sql);
  			  preparedStmt.setString (1, reserva.getLugarReserva());
		      preparedStmt.setDate   (2, new java.sql.Date(reserva.getFechareserva().toDate().getTime()));
		      preparedStmt.setString (3, reserva.getEventoReserva());
		      preparedStmt.setString (4, reserva.getUsuario());
		      preparedStmt.setString (5, reserva.getEstado());
		      preparedStmt.setInt    (6, reserva.getIdReserva());
		     		      
		      preparedStmt.execute();
		      exito = true;
		      logger.info("Reserva actualizada en BD");
		} catch (SQLException e) {
			logger.info("Ocurrio una Exepcion en la actualizacion de la reserva " +e);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return exito;
	}
	
	//Metodo para eliminar reservas de la BD
	public boolean eliminarReserva(Connection conexion, String idReserva) throws SQLException{
		boolean exitoso = false;
		
		try {
			String sql = MetodosUtilitarios.getInstance().getStringArchivo("../DB/DELETE_RESERVA.sql");
			PreparedStatement preparedStmt = conexion.prepareStatement(sql);
			preparedStmt.setString (1, idReserva);
			preparedStmt.execute();
			exitoso = true;		
			logger.info("Se elimino el registro con ID : "+ idReserva +" exitosamente de la BD");
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
