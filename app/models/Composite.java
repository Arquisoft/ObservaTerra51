package models;

import javax.persistence.Entity;
import javax.persistence.PersistenceException;

import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
public class Composite extends Area {

	private static final long serialVersionUID = 2L;
	
    public List<Area> areas;

	public Composite(List<Area> areas){
        this.areas = areas;
	}

    public static Finder<Long,Composite> find = new Finder<Long, Composite>(Long.class, Composite.class);

    public String type(){
        return "composite";
    }

    //CRUD
    public static List<Composite> all(){
        return find.all();
    }

    public static Composite findById(Long id){
        return Composite.find.byId(id);
    }

    public static Composite create(Composite composite) throws PersistenceException {
        if(Composite.findById(composite.id) == null){
            composite.save();
            return composite;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}