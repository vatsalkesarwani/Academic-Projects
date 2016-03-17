package project6;

import java.util.Hashtable;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

class SAXTest {
    public static void main ( String args[] ) throws Exception {
	new QueryHandler("SigmodRecord.xml");
    }
}

class QueryHandler extends DefaultHandler {

    private boolean volumeBool = false;
	private boolean numberBool = false;
	private boolean authorBool = false;
	private boolean titleBool = false;
	private boolean articleBool = false;
	private boolean initBool = false;
	private boolean endBool = false;
	private String volumeTag = "";
	private String numberTag = "";
	private String authorTag = "";
	private String titleTag = "";
	private String articleTag = "";
	private String initTag = "";
	private String endTag = "";
	private String solution1 = "";
	private String solution2 = "";
	private String solution3 = "";

    public QueryHandler ( String file ) {
		super();
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

    public void startDocument() throws SAXException {
    }

    public void startElement ( String uri, String name, String tag, Attributes atts ) throws SAXException {
    	//System.out.println(uri);
    	//System.out.println(name);
    	//System.out.println(tag);
    	//System.out.println(atts);
  
    	if (tag.equals("volume"))
		    volumeBool = true;
    	else if (tag.equals("number"))
    		numberBool = true;
		else if (tag.equals("author"))
			authorBool = true;
		else if (tag.equals("title"))
			titleBool = true;
		else if (tag.equals("article"))
    		articleBool = true;
		else if (tag.equals("initPage"))
			initBool = true;
		else if (tag.equals("endPage"))
    		endBool = true;
    }

    public void characters ( char text[], int start, int length ) {
    	//System.out.println(new String(text,start,length));
    	//System.out.println(text);
    	//System.out.println(start);
    	//System.out.println(length);
    	
    	if (volumeBool == true)
		    volumeTag = new String(text,start,length);
    	else if (numberBool == true)
    		numberTag = new String(text,start,length);
    	else if (authorBool == true)
			authorTag = new String(text,start,length);
		else if (titleBool == true)
			titleTag = new String(text,start,length);
    	else if (articleBool == true)
    		articleTag = new String(text,start,length);
		else if (initBool == true)
			initTag = new String(text,start,length);
		else if (endBool == true)
    		endTag = new String(text,start,length);
    	
    	if(volumeTag.equals("13") && numberTag.equals("4") && authorTag.equals("David Maier")) {
    		solution1 = solution1 + titleTag + "\n";
    		//System.out.println("Solution 1");
    		//System.out.println(titleTag);
    	}
    	if((titleTag.contains("database") || titleTag.contains("Database")) && (authorBool == true)) {
    		solution2 = solution2 + "Author: " + authorTag + "\n";
    		//System.out.println("Solution 2");
    		//System.out.println("Author: " + authorTag);
    	}
    	if((titleTag.equals("Research in Knowledge Base Management Systems.")) && (initBool == true)) {
    		solution3 = solution3 + "Volume: " + volumeTag + " Number: " + numberTag + " InitPage: " + new String(text,start,length);
    		//System.out.println("Solution 3");
    		//System.out.println("Volume: " + volumeTag);
    		//System.out.println("Number: " + numberTag);
    		//System.out.println("InitPage: " + new String(text,start,length));
    	}
    	if((titleTag.equals("Research in Knowledge Base Management Systems.")) && (endBool == true)) {
    		solution3 = solution3 + " EndPage: " + new String(text,start,length) + "\n";
    		//System.out.println("EndPage: " + new String(text,start,length));
    	}
    }
    
    public void endElement ( String uri, String name, String tag ) {
    	//System.out.println(uri);
    	//System.out.println(tag);
    	//System.out.println(name);
   
    	if (tag.equals("volume"))
		    volumeBool = false;
    	else if (tag.equals("number"))
    		numberBool = false;
    	else if (tag.equals("author"))
			authorBool = false;
		else if (tag.equals("title"))
			titleBool = false;
    	else if (tag.equals("article"))
    		articleBool = false;
		else if (tag.equals("initPage"))
			initBool = false;
		else if (tag.equals("endPage"))
    		endBool = false;
    }
    
    public void endDocument() throws SAXException {
    	System.out.println("Solution 1: " + solution1);
    	System.out.println("Solution 2: " + solution2);
    	System.out.println("Solution 3: " + solution3);
    }
}