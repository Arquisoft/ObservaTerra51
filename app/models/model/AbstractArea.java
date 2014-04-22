package models.model;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractArea extends Model implements Area {

    @Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
	public String name;
}