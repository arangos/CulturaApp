package owlig.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class LugarServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public LugarServiceApplication() {
		singletons.add(new LugarRestService(null, null, null));
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
