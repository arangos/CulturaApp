package owlig.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class EventoRestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public EventoRestServiceApplication() {
		singletons.add(new EventoRestService());
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
