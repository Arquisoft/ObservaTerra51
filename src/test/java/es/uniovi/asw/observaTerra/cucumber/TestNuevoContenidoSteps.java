package es.uniovi.asw.observaTerra.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;

public class TestNuevoContenidoSteps {

	private List<Resource> listaInicial;
	private List<Resource> listaDeControl;

	@Cuando("^añado un recurso \"([^\"]*)\" a la lista de recursos con nivel de acceso (\\d+)$")
	public void añado_un_recurso_a_la_lista_de_recursos_con_nivel_de_acceso(
			String arg1, int arg2) throws Throwable {
		listaInicial.add(new Resource(arg1, (double) arg2));
	}

	@Entonces("^obtengo la nueva lista:$")
	public void obtengo_la_nueva_lista(List<Resource> arg1) throws Throwable {
		listaDeControl = arg1;

		for (int i = 0; i < listaInicial.size(); i++) {

			assertEquals(listaDeControl.get(i), listaInicial.get(i));
		}
	}

	@Dada("^una lista de recursos inicial:$")
	public void una_lista_de_recursos_inicial(List<Resource> arg1)
			throws Throwable {
		listaInicial = new ArrayList<Resource>();
		listaInicial.addAll(arg1);
	}
}
