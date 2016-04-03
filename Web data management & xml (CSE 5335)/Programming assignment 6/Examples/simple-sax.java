import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import java.io.FileReader;

class QueryHandler extends DefaultHandler {
    int state;

    public QueryHandler ( String file ) {
	super();
	state = 0;
	try {
	    SAXParserFactory factory = SAXParserFactory.newInstance();
	    factory.setValidating(false);
	    factory.setNamespaceAware(false);
	    XMLReader xmlReader = factory.newSAXParser().getXMLReader();
	    xmlReader.setContentHandler(this);
	    xmlReader.parse(file);
	} catch (Exception e) {
	    throw new Error(e);
	}
    }

    public void startElement ( String uri, String name, String tag, Attributes atts ) {
	if (tag.equals("lastname"))
	    state = 1;
	else if (state == 2 && tag.equals("firstname"))
	    state = 3;
    }

    public void endElement ( String uri, String name, String tag ) {
	if (tag.equals("firstname"))
	    state = 0;
    }

    public void characters ( char text[], int start, int length ) {
	if (state == 1 && new String(text,start,length).equals("Finton"))
	    state = 2;
	else if (state == 3)
	    System.out.println(new String(text,start,length));
    }
}

class SAXTest {
    public static void main ( String args[] ) throws Exception {
	new QueryHandler("cs.xml");
    }
}