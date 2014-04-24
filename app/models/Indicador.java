package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

/**
 * @version 1.0 with JPA
 */

@Entity
public class Indicador extends Model{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

    public static Indicador create(Indicador indicador) throws Exception {
        if(Indicador.findById(indicador.id) == null){
            indicador.save();
            return indicador;
        }else
            throw new Exception("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}