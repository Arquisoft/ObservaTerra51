package controllers;

import models.User;
import models.UserLogin;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AES;
import views.html.index2;
import views.html.error403;

import static play.data.Form.form;

/**
 * Created by Fer on 18/04/2014.
 */
public class Login extends Controller {

    final static Form<UserLogin> loginForm = form(UserLogin.class, UserLogin.All.class);

    public static Result blank() {
    	if (session() == null)
    		return ok(error403.render("403 Forbidden"));
    	
        if(session().get("login") == null || session().get("login").compareToIgnoreCase("") == 0)
        	return ok(views.html.login.loginform.render(loginForm));
        else
        	return ok(error403.render("403 Forbidden"));
    }

    public static Result submit() {
        Form<UserLogin> filledForm = loginForm.bindFromRequest();

        // Check if the username is valid
        if(!filledForm.hasErrors()) {

            User user = User.findByUsername(filledForm.get().username);

            if(user == null)
                filledForm.reject("username", "User and password doesn't match");

            else{
                String decrytpedPassword = AES.decrypt(user.password);
                if(decrytpedPassword.equals(filledForm.get().password))//We check if decrypted password matches
                {
                    //We put the user in session
                    session().put("login", filledForm.get().username);
                    //Return home
                    return ok(index2.render());
                }else
                    filledForm.reject("username", "User and password doesn't match");
            }
        }

        // filledForm has errors...
        return badRequest(views.html.login.loginform.render(filledForm));
    }

    public static Result logoff(){
        //Login empty
        session().put("login","");
        //Way back HOME
        return ok(index2.render());
    }
}


