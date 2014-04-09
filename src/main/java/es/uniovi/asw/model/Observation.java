package es.uniovi.asw.model;

/**
 * @version 1.0
 * @created 02-abr-2014 10:00:59
 */
public class Observation {

	private Provider provider;
	private User user;
	private int value;

	public Observation(){

	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void finalize() throws Throwable {

	}

}