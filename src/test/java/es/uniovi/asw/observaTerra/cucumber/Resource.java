package es.uniovi.asw.observaTerra.cucumber;

public class Resource {

	public String name;
	public Double access_level;

	public Resource(String name, Double access_level) {
		super();
		this.name = name;
		this.access_level = access_level;
	}

	public Resource() {
	}

	public String setName(String name) {
		this.name = name;
		return name;
	}

	public Double setAccess_level(Double access_level) {
		this.access_level = access_level;
		return access_level;
	}
	
	@Override
	public String toString() {
		String s  = "";
		
		s+="Recurso: "+name+" | AL: "+access_level;
		
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((access_level == null) ? 0 : access_level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (access_level == null) {
			if (other.access_level != null)
				return false;
		} else if (!access_level.equals(other.access_level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
