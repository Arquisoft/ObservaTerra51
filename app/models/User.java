package models;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User extends Model{
   
    public interface All {}

    @Id
    @Required(groups = {All.class})
    @MinLength(value = 4, groups = {All.class})
    public String username;
    
    @Required(groups = {All.class})
    @Email(groups = {All.class})
    public String email;
    
    @Required(groups = {All.class})
    @MinLength(value = 6, groups = {All.class})
    public String password;

    //-------Personal info-------
    @Required(groups = {All.class})
    public String country;

    public String address;

    @Min(value = 18, groups = {All.class}) @Max(value = 120, groups = {All.class})
    public Integer age;

    
    public User() {}
    
    public User(String username, String email, String password,String country,String address,Integer age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.country = country;
        this.age = age;
    }

    public static Model.Finder<String,User> find = new Model.Finder(String.class, User.class);

    public static List<User> all(){
        return find.all();
    }

    public static User create(User user)throws Exception{
        if (User.findByUsername(user.username) == null) {
            user.save();
            return user;
        }else
            throw new Exception("Element already exists");
    }

    public static User findByUsername(String username) {
        return find.byId(username);
    }

    public static void remove(String username) {
        find.ref(username).delete();
    }

}