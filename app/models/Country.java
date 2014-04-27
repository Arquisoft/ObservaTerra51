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
@DiscriminatorValue("pais")
public class Country extends Area {

	private static final long serialVersionUID = 4L;

	public Country(String name){
        this.name = name;
	}

    public static Finder<Long,Country> find = new Finder<Long, Country>(Long.class, Country.class);

    public String type(){
        return "Country";
    }

    //CRUD
    public static List<Country> all(){
        return find.all();
    }

    public static Country findById(Long id){
        return find.byId(id);
    }

    public static Country findByName(String name) throws PersistenceException {
        List<Country> list = new ArrayList<Country>(find.where().ilike("name", name).findList()); //insensitive search

        if(list.isEmpty())
            return null;

        if(list.size() > 1)
            throw new PersistenceException("Area Table is inconsistent: Country name is repeated");

        return list.get(0);
    }

    public static Country create(Country country) throws PersistenceException{
        if(findByName(country.name) == null){
            country.save();
            return country;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }


}