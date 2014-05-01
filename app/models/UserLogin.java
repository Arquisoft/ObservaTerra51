package models;

import play.data.validation.Constraints.Required;
import javax.validation.Payload;

public class UserLogin {

    public interface All {}

    @Required(groups = {All.class})
    public String username;

    @Required(groups = {All.class})
    public String password;


    public UserLogin() {}

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
    
