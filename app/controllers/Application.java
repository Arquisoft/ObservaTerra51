package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index2;

public class Application extends Controller {

    //He cambiado el index. render antiguo por index2.render
    public static Result home(){
        return ok(index2.render());
    }

}
