package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

/**
 * @version 1.0 with JPA
 */

/**
 * Comentarios: Esta en pruebas, lo que se quiere conseguir es mediante anotacion JPA que esta clase sea "abstracta"
 * y sirva para todos los tipos de areas.
 *
 * No se como hacerlo con JPA
 *
 * -Fernando
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoArea", discriminatorType = DiscriminatorType.STRING)
public abstract class Area extends Model {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
	public String name;

    public String type(){
        return "";
    }
    
    
}