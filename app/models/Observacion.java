package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.List;

/**
 * @version 1.0 with JPA
 */

@Entity
public class Observacion extends Model{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
    Long id;

    @ManyToOne
	public User user;

    @ManyToOne
    public Organization org;

    @ManyToOne
    public Indicador indicator;

    @ManyToOne
    public Area area;

    @Constraints.Required
    public String measure; //No se hasta que punto beneficiaria tener una clase measure.
    @Constraints.Required
    public int value;

    // Faltaria el tiempo (instante inicio + instante final o Time con polimorfismo?)

    @Constraint.Required
    public Time time;

	public Observacion(Provider provider, Indicador indicator, Area area, String measure, int value){
        this.provider = provider;
        this.indicator = indicator;
        /*this.area = area;*/
        this.measure = measure;
        this.value = value;

	}

    public static Finder<Long, Observacion> find = new Finder<Long, Observacion>(Long.class, Observacion.class);

    //CRUD
    public static List<Observacion> all(){
        return find.all();
    }

    public static Observacion findById(Long id){
        return Observacion.find.byId(id);
    }

    public static Observacion create(Observacion observation) throws Exception {
        if(Observacion.findById(observation.id) == null){
            observation.save();
            return observation;
        }else
            throw new Exception("Element already exists");
    }

    public static void remove(Long id){
       find.ref(id).delete();
    }
}