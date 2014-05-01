package parsers;

import models.Country;
import models.Indicador;
import models.Observacion;
import models.Provider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import parsers.Parser;

import javax.persistence.PersistenceException;

import java.io.IOException;
import java.util.*;


public abstract class AbstractParser implements Parser {
	private String URL;
	private String providerName;
	private String measure;
	private String indicatorName;
	private String countryName;
	private int value;

	private Document document;
	private Set<String> countries = new HashSet<String>();
	private Set<String> indicators = new HashSet<String>();
	private Set<String> measures = new HashSet<String>();

	// Getter & Setter - URL
	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getURL() {
		return URL;
	}

	// Getter & Setter - document
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	// interface methods
	@Override
	public List<Observacion> parse(){
		
		return null;
	}
}
