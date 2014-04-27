package controllers;

import parsers.WhoParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index2;

public class Application extends Controller {
    private static String  URL = "http://apps.who.int/gho/athena/data/GHO/WHOSIS_000002,WHOSIS_000001,WHOSIS_000015.html?profile=ztable&filter=COUNTRY:*;REGION:AFR;REGION:AMR;REGION:SEAR;REGION:EUR;REGION:EMR;REGION:WPR;SEX:*";

    //He cambiado el index. render antiguo por index2.render
    public static Result home(){
        return ok(index2.render());
    }

    public static Result parserTest(){

        WhoParser parser = new WhoParser(URL);
        parser.parseAndUpload();

        return ok(index2.render());
    }

}
