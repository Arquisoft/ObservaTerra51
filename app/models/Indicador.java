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
public class Indicador extends Model{

	private static final long serialVersionUID = 5L;

	@Id @GeneratedValue
    public Long id;

    @Constraints.Required
	public String name;

	public Indicador(String name){
        this.name = name;
	}

    public static Finder<Long, Indicador> find = new Finder<Long, Indicador>(Long.class, Indicador.class);

    //CRUD
    public static List<Indicador> all(){
        return find.all();
    }

    public static Indicador findById(Long id){
        return Indicador.find.byId(id);
    }

    public static Indicador findByName(String name)throws PersistenceException{
        List<Indicador> list = new ArrayList<Indicador>(find.where().ilike("name", name).findList()); //insensitive search

        if(list.isEmpty())
            return null;

        if(list.size() > 1)
            throw new PersistenceException("Country Database is inconsistent: Indicator name is repeated");

        return list.get(0);
    }

    public static Indicador create(Indicador indicador) throws PersistenceException {
        if(Indicador.findByName(indicador.name) == null){
            indicador.save();
            return indicador;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }

    public static void removeAll(){
        for(Indicador ind : all())
            ind.delete();
    }
}