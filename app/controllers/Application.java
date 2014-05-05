package controllers;

import models.Observacion;
import parsers.WhoParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {
    private static String  URL = "http://apps.who.int/gho/athena/data/GHO/WHOSIS_000002,WHOSIS_000001,WHOSIS_000015.html?profile=ztable&filter=COUNTRY:*;REGION:AFR;REGION:AMR;REGION:SEAR;REGION:EUR;REGION:EMR;REGION:WPR;SEX:*";

    public static Result home(){
        session().put("language","joder");

        if(!(session().get("login") == ""))
            return ok(index2.render());
        else{
            session().put("login","");
            return ok(index2.render());
        }
    }

    public static Result parserTest(){

        WhoParser parser = new WhoParser(URL);
        parser.parseAndUpload();

        return ok(index2.render());
    }

    /**
     * Display the paginated list of observations.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on area names
     */
    public static Result listObservations(int page, String sortBy, String order, String filter) {

        return ok(
                list.render(
                        Observacion.page(page, 10, sortBy, order, filter),sortBy, order, filter
                )
        );
    }

    public static Result switchLanguage(String lang){
        if(!lang.equals("es") && !lang.equals("en"))
            return ok(error403.render("403 Forbidden"));

        //The language introduced is one of the available ones
        changeLang(lang);
        return ok(index2.render());
    }

    public static Result statistics(){
    	/*
    	 * Este retorno me da error de compilacion [DANIEL]
    	 */
//        return ok(views.html.statisticsMain.render());
    	return TODO; // Esto dice: NotImplementedYet
    }
    
    public static Result showProfile() {
    	
    	if (session().get("login") == null || session().get("login").compareToIgnoreCase("") == 0)
    		return ok(error403.render("403 Forbidden"));
    	else
    		return ok(profile.render(session().get("login")));
    }

}
