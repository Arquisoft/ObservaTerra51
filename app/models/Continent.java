package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PersistenceException;

import models.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
@DiscriminatorValue("continente")
public class Continent extends Area {

	private static final long serialVersionUID = 3L;

	public Continent(String name){
        this.name = name;
	}

    public static Finder<Long,Continent> find = new Finder<Long, Continent>(Long.class, Continent.class);

    public String type(){
        return "Continent";
    }

    //CRUD
    public static List<Continent> all(){
    	return find.all();
    }

    public static Continent findById(Long id){
        return Continent.find.byId(id);
    }

    public static Continent findByName(String name) throws PersistenceException {
        List<Continent> list = new ArrayList<Continent>(find.where().ilike("name", name).findList()); //insensitive search

        if(list.isEmpty())
            return null;

        if(list.size() > 1)
            throw new PersistenceException("Country Database is inconsistent: Country name is repeated");

        return list.get(0);
    }

    public static Continent create(Continent continent) throws PersistenceException {
        if(Continent.findByName(continent.name) == null){
            continent.save();
            return continent;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}