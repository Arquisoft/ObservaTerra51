package models;

/**
 * @version 1.0 with JPA
 */
public class Time extends Model {
	
	private static final long serialVersionUID = 1L;

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
    
    public Time(Date inicio, Date final) {
    	this.inicio = inicio;
    	this.fin = fin;
    }
    
    public Time() {
    	inicio = new Date();
    	fin = new Date();
    }
	
}