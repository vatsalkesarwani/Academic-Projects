import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import java.io.File;

import org.w3c.dom.DOMImplementationList;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

class DOMTest {
    static void print ( Node e ) {
	if (e instanceof Text)
	    System.out.print(((Text) e).getData());
	else {
	    NodeList c = e.getChildNodes();
	    System.out.print("<"+e.getNodeName()+">");
	    for (int k = 0; k < c.getLength(); k++)
		print(c.item(k));
	    System.out.print("</"+e.getNodeName()+">");
	}
    }

    static void query ( Node n ) {
	NodeList nl = ((Element) n).getElementsByTagName("gradstudent");
	for (int i = 0; i < nl.getLength(); i++) {
	    Element e = (Element) (nl.item(i));
	    if (e.getElementsByTagName("firstname").item(0)
		 .getFirstChild().getNodeValue().equals("Leonidas"))
		print(e);
	}
    }

    static Element createDoc ( Document doc ) {
	Element person = doc.createElement("person");
	person.appendChild(doc.createTextNode("John Smith"));
	Element root = doc.createElement("root");
	root.appendChild(person);
	return root;
    }

    public static void main ( String args[] ) throws Exception {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = dbf.newDocumentBuilder();
	Document doc = db.parse(new File("cs.xml"));
	Node root = doc.getDocumentElement();
	query(root);
	print(createDoc(db.newDocument()));
    }
}