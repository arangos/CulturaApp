package owlig.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class UsuarioRestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public UsuarioRestServiceApplication() {
		singletons.add(new UsuarioRestService());
	}
 
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return null;
	}

}
