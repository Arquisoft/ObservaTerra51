package models.model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
public class Composite extends AbstractArea {

    /*@Id @GeneratedValue
    Long id;
    */
    public List<Area> areas;

	public Composite(List<Area> areas){
        this.areas = areas;
	}

    public static Finder<Long,Composite> find = new Finder(Long.class, Composite.class);

    //CRUD
    public static List<Composite> all(){
        return find.all();
    }

    public static Composite findById(Long id){
        return Composite.find.byId(id);
    }

    public static Composite create(Composite composite) throws Exception {
        if(Composite.findById(composite.id) == null){
            composite.save();
            return composite;
        }else
            throw new Exception("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}