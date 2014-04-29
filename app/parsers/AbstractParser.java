package parsers;

public class AbstractParser implements Parser {
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
}
