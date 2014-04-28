package controllers;

import models.Observacion;
import parsers.WhoParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index2;
import views.html.list;

public class Application extends Controller {
    private static String  URL = "http://apps.who.int/gho/athena/data/GHO/WHOSIS_000002,WHOSIS_000001,WHOSIS_000015.html?profile=ztable&filter=COUNTRY:*;REGION:AFR;REGION:AMR;REGION:SEAR;REGION:EUR;REGION:EMR;REGION:WPR;SEX:*";

    public static Result home(){
        return ok(index2.render());
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

}
