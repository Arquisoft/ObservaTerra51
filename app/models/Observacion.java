package models;

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
    public Indicador indicator;
    @ManyToOne
    public Area area;

    @Constraints.Required
    public String measure;
    @Constraints.Required
    public int value;



    //Faltaria el tiempo (instante inicio + instante final o Time con polimorfismo?)

	public Observacion(Provider provider, Indicador indicator, Area area, String measure, int value){
        this.provider = provider;
        this.indicator = indicator;
        this.area = area;
        this.measure = measure;
        this.value = value;
	}

    public Observacion(String providerName, String indicatorName, Area area, String measure, int value){
        provider = Provider.findByName(providerName);
        indicator = Indicador.findByName(indicatorName);
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

    //Todavia no esta terminado, se va a usar con el Parser
    public static Observacion create(String providerName, String indicatorName, Area area, String measure, int value) throws PersistenceException {

        String query="find observacion where provider.name=:providerName and indicator.name=:indicatorName and area.name=:areaName and measure=:measure and value=:value";
        Observacion observacion = find.setQuery(query)
                .setParameter("providerName",providerName)
                .setParameter("indicatorName",indicatorName)
                .setParameter("areaName",area.name)
                .setParameter("measure",measure)
                .setParameter("value",value)
                .findUnique();

        if(observacion == null) {
            observacion = new Observacion(providerName, indicatorName, area, measure, value);
            observacion.save();
            return observacion;
        }else
            throw new PersistenceException("Element already exists");
    }

    public static void remove(Long id){
       find.ref(id).delete();
    }
}