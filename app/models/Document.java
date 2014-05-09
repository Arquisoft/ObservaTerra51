package models;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Document extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	public long id;
	
	@ManyToOne
	public User user;
	
	public String extension;
	
	public String name;
	
	public File file;
	
	public Document() {}
	
	public Document(File file, String ext, User u, String name) {
		this.user = u;
		this.file = file;
		extension = ext;
		this.name = name;
	}
}
