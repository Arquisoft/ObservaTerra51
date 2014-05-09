package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.API;
import models.*;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static play.test.Helpers.*;

/**
 * Created by Fer on 08/05/2014.
 */
public class ObservacionApiTest {

    ObjectMapper jsonMapper = new ObjectMapper();

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));

        Observacion.removeAll();
        Area.removeALlArea();
        Indicador.removeAll();

    }

    @Test
    public void addObservation(){

        Country country = new Country("Spain");

        try {
            Country.create(country);//We need to have that country created in the DB
        }catch(PersistenceException p){}//We do nothing, we simply avoid that Country creation

        Provider provider = new Provider("World Health Organization");
        try {
            Provider.create(provider);
        }catch(PersistenceException e){}//We do nothing, we simply avoid that creation

        Indicador indicador = new Indicador("test");
        try{
            Indicador.create(indicador);
        }catch(PersistenceException e){}//We do nothing, we simply avoid that creation

        //Value
        int intValue = 100;//Will throw Exception if the value is not a real integer...

        //Date
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date InitDate = null;
        Date EndDate = null;

        try {
            InitDate = textFormat.parse("1999-01-01");
            EndDate = textFormat.parse("1999-12-31");
        } catch (ParseException ex) { ex.printStackTrace();}

        Observacion obs = new Observacion("World Health Organization", "test", country, "testYears", intValue, InitDate, EndDate);

        assertEquals(Observacion.all().size(),0);
        Result result = API.addObservation("World Health Organization","test","Spain","testYears","100","1999-01-01","1999-12-31");
        assertEquals(Observacion.all().size(),1);
        assertNotEquals(result,null);

        Observacion obser = Observacion.findById((long)1);

        assertEquals(Json.toJson(obs),Json.toJson(obser));
        assertNotEquals(Json.toJson(obs),null);

    }


}
