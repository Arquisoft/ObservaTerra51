package controllers;

import models.User;
import models.UserLogin;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index2;

import static play.data.Form.form;

/**
 * Created by Fer on 18/04/2014.
 */
public class Login extends Controller {

    final static Form<UserLogin> loginForm = form(UserLogin.class, UserLogin.All.class);

    public static Result blank() {
        return ok(views.html.login.loginform.render(loginForm));
    }

    public static Result submit() {
        Form<UserLogin> filledForm = loginForm.bindFromRequest();

        // Check if the username is valid
        if(!filledForm.hasErrors()) {

            User user = User.findByUsername(filledForm.get().username);

            if(user == null)
                filledForm.reject("username", "User and password doesn't match");

            else{
                if(user.password.equals(filledForm.get().password))
                {
                    //We put the user in session
                    session("login", filledForm.get().username);
                    return ok(index2.render());
                }else
                    filledForm.reject("username", "User and password doesn't match");
            }
        }

        // filledForm has errors...
        return badRequest(views.html.login.loginform.render(filledForm));
    }

    public static Result logoff(){
        session("login", null);
        //Way back HOME
        return ok(index2.render());
    }
}


