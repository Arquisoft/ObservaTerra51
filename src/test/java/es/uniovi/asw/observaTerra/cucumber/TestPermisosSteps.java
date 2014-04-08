package es.uniovi.asw.observaTerra.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;

public class TestPermisosSteps {

	private List<Resource> listaEntrada;
	private List<Resource> listaControl;

	@Dada("^una lista de recursos:$")
	public void una_lista_de_recursos(List<Resource> arg1) throws Throwable {
		listaEntrada = arg1;
	}

	@Cuando("^calculo la lista de recursos para nivel de acceso (\\d+)$")
	public void calculo_la_lista_de_recursos_para_nivel_de_acceso(int arg1)
			throws Throwable {
		int current_level = arg1;
		List<Resource> listaFiltrada = new ArrayList<Resource>();
		for (Resource res : listaEntrada) {
			if (res.access_level <= current_level)
				listaFiltrada.add(res);
		}
		listaEntrada = listaFiltrada;

	}

	@Entonces("^obtengo la lista:$")
	public void obtengo_la_lista(List<Resource> arg1) throws Throwable {

		listaControl = arg1;

		for (int i = 0; i < listaEntrada.size(); i++) {

			assertEquals(listaControl.get(i), listaEntrada.get(i));

		}
	}

}
