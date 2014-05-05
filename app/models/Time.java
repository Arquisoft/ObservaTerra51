package models;
import play.db.ebean.Model;
import java.util.Date;
import play.data.validation.Constraints;
import javax.persistence.*;
/**
 * @version 1.0 with JPA
 */
@Entity
public class Time extends Model {
	
	private static final long serialVersionUID = 100L;

	@Id
    @GeneratedValue
    public Long id;
	
	@Constraints.Required
	public Date inicio;
	
	@Constraints.Required
	public Date fin;

    public String type(){
        return "";
    }
    
    public Time(Date inicio, Date fin) {
    	this.inicio = inicio;
    	this.fin = fin;
    }
    
    public Time() {
    	inicio = new Date();
    	fin = new Date();
    }

}