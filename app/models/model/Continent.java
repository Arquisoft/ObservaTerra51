package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
@DiscriminatorValue("continente")
public class Continent extends Area {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Continent(String name){
        this.name = name;
	}

    public static Finder<Long,Continent> find = new Finder<Long, Continent>(Long.class, Continent.class);

    //CRUD
    public static List<Continent> all(){
        return find.all();
    }

    public static Continent findById(Long id){
        return Continent.find.byId(id);
    }

    public static Continent create(Continent continent) throws Exception {
        if(Continent.findById(continent.id) == null){
            continent.save();
            return continent;
        }else
            throw new Exception("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}