package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
@DiscriminatorValue("provincia")
public class Province extends Area {

	private static final long serialVersionUID = 9L;

	public Province(String name) {
		this.name = name;
	}

	public static Finder<Long, Province> find = new Finder<Long, Province>(
			Long.class, Province.class);

    public String type(){
        return "Province";
    }

	// CRUD
	public static List<Province> all() {
		return find.all();
	}

	public static Province findById(Long id) {
		return Province.find.byId(id);
	}

    public static Province findByName(String name) throws PersistenceException {
        List<Province> list = new ArrayList<Province>(find.where().ilike("name", name).findList()); //insensitive search

        if(list.isEmpty())
            return null;

        if(list.size() > 1)
            throw new PersistenceException("Area Table is inconsistent: Province name is repeated");

        return list.get(0);
    }

	public static Province create(Province province) throws PersistenceException {
		if (Province.findByName(province.name) == null) {
			province.save();
			return province;
		} else
			throw new PersistenceException("Element already exists");
	}

	public static void remove(Long id) {
		find.ref(id).delete();
	}
}