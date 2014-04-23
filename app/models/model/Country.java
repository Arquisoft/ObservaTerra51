package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
@DiscriminatorValue("pais")
public class Country extends Area {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Country(String name){
        this.name = name;
	}

    public static Finder<Long,Country> find = new Finder<Long, Country>(Long.class, Country.class);

    //CRUD
    public static List<Country> all(){
        return find.all();
    }

    public static Country findById(Long id){
        return Country.find.byId(id);
    }

    public static Country create(Country country) throws Exception {
        if(Country.findById(country.id) == null){
            country.save();
            return country;
        }else
            throw new Exception("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}