package es.uniovi.asw.model.areas;

import es.uniovi.asw.model.interfaces.Area;

/**
 * @version 1.0
 * @created 02-abr-2014 10:00:58
 */
public abstract class AbstractArea implements Area {

	private String name;

	public AbstractArea(){

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void finalize() throws Throwable {

	}

}