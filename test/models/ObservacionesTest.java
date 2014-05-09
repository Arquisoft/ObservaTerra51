package models;

import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

/**
 * Created by Fer on 08/05/2014.
 */
public class ObservacionesTest extends WithApplication {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));

        Observacion.removeAll();
        Area.removeALlArea();
        Indicador.removeAll();
    }

    //We compare a normal observation with an observation that has been made by using the API
    @Test
    public void saveObservation() {

        Country country = new Country("Nicaragua");

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
            InitDate = textFormat.parse("1000-01-01");
            EndDate = textFormat.parse("1000-12-31");
        } catch (ParseException ex) { ex.printStackTrace();}

        Observacion obs = Observacion.create("World Health Organization", "test", country, "testYears", intValue, InitDate, EndDate);

        assertEquals(Observacion.all().size(),1);
        assertEquals(Observacion.allByArea("Nicaragua").size(), 1); //All the areas are searched in lowercase
        assertEquals(Observacion.findById((long)1),obs);
    }

}
