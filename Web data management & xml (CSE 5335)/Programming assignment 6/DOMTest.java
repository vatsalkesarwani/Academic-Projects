package project6;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

class DOMTest {
	
    public static void main ( String args[] ) throws Exception {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = dbf.newDocumentBuilder();
	Document doc = db.parse(new File("SigmodRecord.xml"));
	Node root = doc.getDocumentElement();
	System.out.println("Solution 1");
	query1(root);
	System.out.println("Solution 2");
	query2(root);
	System.out.println("Solution 3");
	query3(root);
    }
    
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
    static void query1 ( Node n ) {
		NodeList nl1 = ((Element) n).getElementsByTagName("issue");
		for (int i = 0; i < nl1.getLength(); i++) {
			Node n1 = nl1.item(i);
			if(n1.getNodeType() == Element.ELEMENT_NODE) {
				Element e1 = (Element) n1;
				if(e1.getElementsByTagName("volume").item(0).getTextContent().equals("13") && e1.getElementsByTagName("number").item(0).getTextContent().equals("4")) {
					NodeList nl11 = e1.getElementsByTagName("article");
					for(int j = 0; j < nl11.getLength(); j++) {
						Node n11 = nl11.item(j);
						if(n11.getNodeType() == Element.ELEMENT_NODE) {
							Element e11 = (Element) n11;
							if(e11.getElementsByTagName("author").item(0).getTextContent().equals("David Maier")) {
								System.out.println(e11.getElementsByTagName("title").item(0).getTextContent());
							}
						}
					}
				}
			}
		}
    }
    
    static void query2 ( Node n ) {
		NodeList nl2 = ((Element) n).getElementsByTagName("article");
		for (int i = 0; i < nl2.getLength(); i++) {
			Node n2 = nl2.item(i);
			if(n2.getNodeType() == Element.ELEMENT_NODE) {
				Element e2 = (Element) n2;
				if(e2.getElementsByTagName("title").item(0).getTextContent().contains("database") || e2.getElementsByTagName("title").item(0).getTextContent().contains("Database")) {
					NodeList nl21 = e2.getElementsByTagName("author");
					for(int j = 0; j < nl21.getLength(); j++) {
						Node n21 = nl21.item(j);
						Element e21 = (Element) n21;
						System.out.println(e21.getTextContent());
					}
				}
			}
		}
    }
    
    static void query3 ( Node n ) {
		NodeList nl3 = ((Element) n).getElementsByTagName("article");
		for (int i = 0; i < nl3.getLength(); i++) {
			Node n3 = nl3.item(i);
			if(n3.getNodeType() == Element.ELEMENT_NODE) {
				Element e3 = (Element) n3;
				if(e3.getElementsByTagName("title").item(0).getTextContent().equals("Research in Knowledge Base Management Systems.")) {
					Node n31 = n3.getParentNode().getParentNode();
					if(n31.getNodeType() == Element.ELEMENT_NODE) {
						Element e31 = (Element) n31;
						System.out.println("Volume: " + e31.getElementsByTagName("volume").item(0).getTextContent());
						System.out.println("Number: " + e31.getElementsByTagName("number").item(0).getTextContent());
					}
					System.out.println("Init Page: " + e3.getElementsByTagName("initPage").item(0).getTextContent());
					System.out.println("End Page: " + e3.getElementsByTagName("endPage").item(0).getTextContent());
				}
			}
		}
    }
}