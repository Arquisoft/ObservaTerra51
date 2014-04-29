package parsers;

/**
*	Observation structure:
*	
*	provider 	|	indicator 	|	area 	|	measure 	|	value
*	------------|---------------|-----------|---------------|---------
*	organizacion|	Ideagenerica|	aquí	|	loquesemide	|	9001
*
*
**/
public interface Parser {
	String getURL();

	String getOrganizationName();
}
