package es.uniovi.asw.model;

/**
 * @version 1.0
 * @created 02-abr-2014 10:00:58
 */
public abstract class AbstractArea implements Area {

	private String name;

	public AbstractArea(){

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void finalize() throws Throwable {

	}

}