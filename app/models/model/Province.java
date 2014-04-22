package models.model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @version 1.0 with JPA
 */
@Entity
public class Province extends AbstractArea {

    /*@Id @GeneratedValue
    public Long id;*/

	public Province(String name){
        this.name = name;
	}

    public static Finder<Long,Province> find = new Finder(Long.class, Province.class);

    //CRUD
    public static List<Province> all(){
        return find.all();
    }

    public static Province findById(Long id){
        return Province.find.byId(id);
    }

    public static Province create(Province province) throws Exception {
        if(Province.findById(province.id) == null){
            province.save();
            return province;
        }else
            throw new Exception("Element already exists");
    }

    public static void remove(Long id){
        find.ref(id).delete();
    }
}