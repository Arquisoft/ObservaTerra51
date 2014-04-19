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

            //Login Test, if username is not equal to "admin" it rejects the submit petittion and shows the following message
            if(!filledForm.get().username.equals("admin")) {
                filledForm.reject("username", "User and password doesn't match");
                filledForm.reject("password", "User and password doesn't match");

            //On the contrary if the username equals "admin" the user is correctly added to the Session and logged in.
            }else{
                session("login", filledForm.get().username);
                return ok(index2.render());
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


