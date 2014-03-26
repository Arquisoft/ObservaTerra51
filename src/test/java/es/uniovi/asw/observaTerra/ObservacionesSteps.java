package es.uniovi.asw.observaTerra;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.PendingException;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;

public class ObservacionesSteps {

	private ObservationList observationList;
	
	private Double media;

	@Dada("^una lista de observaciones:$")
	public void una_lista_de_observaciones(List<Observation> observations) throws Throwable {
		observationList = new ObservationList();
		for (Observation o : observations) {
            observationList.addObservation(new Country(o.country), o.value);
        }
	}

	@Cuando("^calculo la nota media$")
	public void calculo_la_nota_media() throws Throwable {
		media = observationList.average();
	}

	@Entonces("^obtengo el valor (.+)$") // (\\d\\.+\\d+)$")
	public void obtengo_el_valor(Double expected) throws Throwable {
		assertEquals(expected,media,0.001);
	}
	
	@Dada("^una observación de (.+) con valor (.+)$")
	public void una_observación_de_Chile_con_valor_(String nombre, Double valor) throws Throwable {
		observationList = new ObservationList();
		observationList.addObservation(new Country(nombre),valor);
		assertEquals(observationList.average(), valor);
	}

	// Esta clase se utiliza solamente para la conversión entre
	// los campos de la tabla y los valores de prueba
	public static class Observation {
        private String country;
        private Double value;
    }	
}