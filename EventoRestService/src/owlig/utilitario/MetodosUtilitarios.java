package owlig.utilitario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class MetodosUtilitarios {
	
	private static MetodosUtilitarios instance;
//	private static final Logger logger = Logger.getLogger(MetodosUtilitarios.class);
	private static Map<String,String> cacheQueriesSql = new HashMap<String, String>();
	
	public static synchronized MetodosUtilitarios getInstance(){
		if(instance == null){
			instance = new MetodosUtilitarios();
		}
		return instance;
	}
	
	private InputStream getFile(String File) throws IOException{
		URL url = MetodosUtilitarios.class.getResource(File);
		return url.openStream();
	}
	
	public String getStringArchivo(String Archivo) throws IOException{
		String sql = "";
		if(cacheQueriesSql.containsKey(Archivo)){
			sql = cacheQueriesSql.get(Archivo);
		}else{
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;
			try {
				
				inputStreamReader = new InputStreamReader(getFile(Archivo));
				reader = new BufferedReader(inputStreamReader);
				String temp;
				while((temp = reader.readLine()) != null){
					sql += temp + ""; 
				}
				cacheQueriesSql.put(Archivo, sql);
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(inputStreamReader != null){ inputStreamReader.close();	}
				if(reader != null){ reader.close(); }
			}
		}
		return sql;
	}
			
	
//	public static void main(String[] args) {
//			
//		
//	MetodosUtilitarios meto = new MetodosUtilitarios();
//	String Archivo = "../DB/INSERT_LUGAR.sql";
//	
//	try {
//		MetodosUtilitarios.getInstance().getStringArchivo(Archivo);
//	} catch (IOException e) {
//		// Auto-generated catch block
//		e.printStackTrace();
//	}
//	try {
//		String rutaEnEquipo = "/Users/Arangos/jboss-as-7.1.1.Final/standalone/deployments/LugarRestService.war/WEB-INF/classes/owlig/DB/INSERT_LUGAR.sql";
//		String rutaDespliegue = "/LugarRestService/";
//		meto.readFile("INSERT_LUGAR.sql");
//	} catch (IOException e) {
//		// Auto-generated catch block
//		e.printStackTrace();
//	}

//}

}
