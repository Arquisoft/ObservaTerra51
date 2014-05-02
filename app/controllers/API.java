package controllers;

import models.Country;
import models.Observacion;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Observaterra's API (Only Json right now)
 *
 * Created by Fer on 02/05/2014.
 */
public class API extends Controller{

    public static Result countries() {
        return ok(Json.toJson(Country.all()));
    }

    public static Result country(String name) {
        return ok(Json.toJson(Country.findByName(name)));
    }

    public static Result observations(){
        return ok(Json.toJson(Observacion.all()));
    }

    public static Result observationsByArea(String areaName){
        return ok(Json.toJson(Observacion.allByArea(areaName)));
    }
}
