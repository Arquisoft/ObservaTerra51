package parsers;

import models.Country;
import models.Indicador;
import models.Observacion;
import models.Provider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fer on 24/04/2014.
 *
 * Prototype Parser of the World Health Organization HTML Data Tables
 */
public class WhoParser {

    private String URL;
    private String providerName="World Health Organization";
    private String measure="";
    private String indicatorName ="";
    private String countryName="";
    private int value;

    private Document document;
    private Set<String> countries = new HashSet<String>();
    private Set<String> indicators = new HashSet<String>();
    private Set<String> measures = new HashSet<String>();

    //Getter & Setter - URL
    public void setURL(String URL) {this.URL = URL;}
    public String getURL() {return URL;}
    //Getter & Setter - document
    public Document getDocument() {return document;}
    public void setDocument(Document document) {this.document = document;}

    public WhoParser(String URL){
        this.URL = URL;
    }

    /**
     *
     * @return
     * @throws PersistenceException : If there is some inconsistency in the database, the parser reports it.
     */
    public boolean parseAndUpload() throws PersistenceException {
        // Downloads the webpage and stores it in a 'Document' object
        try {

            document = Jsoup.connect(URL).get();

        } catch (IOException e) {
            return false;//An error occurred during parsing
        }

        //We are only interested in the table elements, in this case there is only one big table.
        Element table = document.select("tbody").first();

        this.createRelatedEntities(table);
        this.uploadObservations(table);

        return true;
    }

    private void createRelatedEntities(Element table) {
        //We iterate through the table, selecting every entire row
        for (Element row : table.select("tr")) {
            //Now we store all the columns and we manually iterate through them
            Elements rowColumns = row.select("td");
            //This if was only added because at the end of the table there is row that doesn't match the pattern (temporal solution)
            if (rowColumns.size() > 6) {
                //This is a prototype, we know the structure of the table, it follows a pattern, so we make use of it.
                //We store the country (It's always in the fifth column)
                this.countries.add(rowColumns.get(5).text());
                //Accessing first element. Special one, it contains 2 data
                Elements a = rowColumns.get(0).select("a");
                String s = a.get(0).text();
                //We use a utility method to split the data
                this.indicatorMeasureSplitter(s);
            }
        }

        //Now we create the new entities (always lowercase to avoid 'real' duplicates)
        //Countries
        for(String countryName:countries) {
            try {
                Country.create(new Country(countryName.toLowerCase()));
            }catch(PersistenceException p){}//We do nothing, we simply avoid that Country creation
        }
        //Indicators
        for(String indicatorName:indicators) {
            try{
                Indicador.create(new Indicador(indicatorName.toLowerCase()));
            }catch(PersistenceException p){}//We do nothing, we simply avoid that Indicator creation
        }
        try{
        //Provider (This parser is only for World Health)
        Provider.create(new Provider(providerName));
        }catch(PersistenceException p){}//We do nothing, we simply avoid that Provider creation
    }

    private void uploadObservations(Element table){
        //We iterate through the table, selecting every entire row
        for (Element row : table.select("tr")) {
            //Now we store all the columns and we manually iterate through them
            Elements rowColumns = row.select("td");
            //This if was only added because at the end of the table there is row that doesn't match the pattern (temporal solution)
            if (rowColumns.size() > 6) {
                //This is a prototype, we know the structure of the table, it follows a pattern, so we make use of it.

                Elements a = rowColumns.get(0).select("a");
                String indicatorPlusMeasure = a.get(0).text();
                indicatorMeasureSplitter(indicatorPlusMeasure);
                countryName=rowColumns.get(5).text();
                value = Integer.parseInt(rowColumns.get(7).text());

                //We create the observation an upload it to the DB
                try {
                    Observacion.create(providerName,indicatorName,new Country(countryName),measure,value);
                }catch(PersistenceException p){}//We do nothing, we simply avoid that Observation creation
            }
        }
    }

    /**
     * Utility method used to split the first column of the table. That column contains indicator data and measure data
     * @param indicatorMeasure
     */
    public void indicatorMeasureSplitter(String indicatorMeasure){

        String[] splitted = indicatorMeasure.split("\\(");
        for(int i=0;i<splitted.length;i++)
            splitted[i] = splitted[i].replace(")", "");//Notice that replace doesn't need to use '\\'

        //Pattern application:
        indicatorName=splitted[0];
        measure=splitted[splitted.length - 1];
        indicators.add(indicatorName);//We know that the first element is always the indicatorName
        measures.add(measure);//And the last the measure
    }




}
