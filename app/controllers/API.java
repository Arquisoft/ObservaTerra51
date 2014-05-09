package controllers;

import models.Country;
import models.Indicador;
import models.Observacion;
import models.Provider;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Result addObservation(String providerName, String indicatorName,String areaName,String measure,String value,String initialDate,String endDate) {
        //First we need to transform this Strings into their true types

        //Area (riht now we only accept countries, so every area is a country...)
        Country country = new Country(areaName.toLowerCase());
        try {
            Country.create(country);
        }catch(PersistenceException p){}//We do nothing, we simply avoid that Country creation

        try {
            Provider.create(new Provider(providerName));
        }catch(PersistenceException p){}//We do nothing, we simply avoid that creation

        try {
            Indicador.create(new Indicador(indicatorName));
        }catch(PersistenceException p){}//We do nothing, we simply avoid that creation

        //Value
        int intValue = Integer.parseInt(value);//Will throw Exception if the value is not a real integer...

        //Date
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date InitDate = null;
        Date EndDate = null;

        try {
            InitDate = textFormat.parse(initialDate);
            EndDate = textFormat.parse(endDate);
        } catch (ParseException ex) { ex.printStackTrace();} //We should notify the person who is adding Observations

        Observacion.create(providerName,indicatorName,country,measure,intValue,InitDate,EndDate);

        //We return de Json observations of that area
        return observationsByArea(areaName);
    }
}
