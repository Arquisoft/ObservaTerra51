package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
public class Provider extends Model{

	private static final long serialVersionUID = 8L;

	@Id @GeneratedValue
    public Long id;

    @Constraints.Required
    public String name;

	public Provider(String name){
        this.name = name;
	}

    public static Finder<Long,Provider> find = new Finder<Long, Provider>(Long.class, Provider.class);

    public static List<Provider> all(){
        return find.all();
    }

    public static Provider findByName(String name) throws PersistenceException {
        List<Provider> list = new ArrayList<Provider>(find.where().like("name", name).findList()); //sensitive search

        if(list.isEmpty())
            return null;

        if(list.size() > 1)
            throw new PersistenceException("Country Database is inconsistent: Country name is repeated");

        return list.get(0);
    }

    public static Provider create (Provider provider){
        if(findByName(provider.name) == null){
            provider.save();
            return provider;
        }else
        throw new PersistenceException("Element already exists");
    }

}