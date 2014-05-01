package parsers;

import java.util.*;

import models.Observacion;
import models.User;
/**
 * Observation structure:
 * 
 * provider | indicator | area | measure | value
 * ------------|---------------|-----------|---------------|---------
 * organizacion| Ideagenerica| aquí | loquesemide | 9001
 * 
 * 
 **/
public interface Parser {
	
	// basic method
	List<Observacion> parse();

	// these two might requiere parameters
	// such as the last entry in the database
	boolean isUpdated();

	List<Observacion> getUpdates();

	// this one is to allow multiple users uploading data
	User setUser(User user);
}
