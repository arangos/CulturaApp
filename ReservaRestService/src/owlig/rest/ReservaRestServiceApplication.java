package owlig.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class ReservaRestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public ReservaRestServiceApplication() {
		singletons.add(new ReservaRestService());
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
