package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * @version 1.0 with JPA
 */

@Entity
public class Observacion extends Model{

	private static final long serialVersionUID = 7L;

	@Id @GeneratedValue
    Long id;

    @ManyToOne
	public Provider provider; //Se modificara, en vez de ser Providers, seran usuarios que pertenecen a un Provider?
    @ManyToOne
    public Indicador indicador;
    @ManyToOne
    public Area area;

    @Constraints.Required
    public String measure;
    @Constraints.Required
    public int value;



    //Faltaria el tiempo (instante inicio + instante final o Time con polimorfismo?)

	public Observacion(Provider provider, Indicador indicator, Area area, String measure, int value){
        this.provider = provider;
        this.indicador = indicator;
        this.area = area;
        this.measure = measure;
        this.value = value;
	}

    public Observacion(String providerName, String indicatorName, Area area, String measure, int value){
        provider = Provider.findByName(providerName);
        indicador = Indicador.findByName(indicatorName);
        this.measure = measure;
        this.value = value;
        switch(area.type()) {
            case "Country" : this.area = Country.findByName(area.name);break;
            case "Province" : this.area = Province.findByName(area.name);break;
            case "Continent" : this.area = Continent.findByName(area.name);break;
        }

    }

    public static Finder<Long, Observacion> find = new Finder<Long, Observacion>(Long.class, Observacion.class);

    //CRUD
    public static List<Observacion> all(){
        return find.all();
    }

    public static List<Observacion>allByArea(String areaName){
        return Ebean.find(Observacion.class)
                .where()
                .ilike("area.name",areaName)
                .findList();
    }

    public static Observacion findById(Long id){
        return Observacion.find.byId(id);
    }

    public static Observacion create(Observacion observation) throws PersistenceException {
        if(Observacion.findById(observation.id) == null){
            observation.save();
            return observation;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static Observacion create(String providerName, String indicatorName, Area area, String measure, int value) throws PersistenceException {

        String query="find observacion where provider.name=:providerName and indicador.name=:indicatorName and area.name=:areaName and measure=:measure and value=:value";
        String query2="find observacion where value=:value and area.name like :areaName";
        List<Observacion> observaciones = Ebean.find(Observacion.class)
                .where()
                .ilike("indicador.name",indicatorName)
                .ilike("area.name",area.name)
                .ilike("provider.name",providerName)
                .eq("measure",measure)
//                .eq("value",value)            //Lo dejamos comentado hasta que introduzcamos el tiempo como variable a tener en cuenta en nuestras observaciones
                .findList();

        if(observaciones.size() < 1) {
            Observacion observacion = new Observacion(providerName, indicatorName, area, measure, value);
            observacion.save();
            return observacion;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static void remove(Long id){
       find.ref(id).delete();
    }

    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of observations per page
     * @param sortBy Observation property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Observacion> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("area.name", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .getPage(page);
    }

}