package es.uniovi.asw.observaTerra.cucumber;

import org.apache.poi.ss.formula.eval.NotImplementedException;

import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;

/**
 * Como consumidor quiero acceder a la información del sistema ObservaTerra para obtener recursos.
 *
 * @author uo219560
 *
 */
public class AccesoDatosSteps {
	
	@Dada("la conexion al sistema$")
	public void la_conexion_al_sistema() throws Throwable{
		throw new NotImplementedException("Prueba");
	}
	
	@Entonces("recibimos respuesta en menos de (.+) segundos$")
	public void recibimos_respuesta_en_menos_de_segundos(double segundos) throws Throwable{
		throw new NotImplementedException("Prueba");
	}
}
