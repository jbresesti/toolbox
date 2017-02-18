package com.wpers.snippet.parsexml;

import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ParseKnownXMLStructure {
	public static void main(String[] args) throws Exception {
		String xml =
		"<employees>" + 
				"<employee id=\"111\">" +
        			"<firstName>Juan</firstName>" +
        			"<lastName>Lopez</lastName>" +
        			"<location>India</location>" +
        		"</employee>" +
        		"<employee id=\"222\">" +
        			"<firstName>Alex</firstName>" +
        			"<lastName>Gussin</lastName>" +
        			"<location>Russia</location>" +
        		"</employee>" +
        		"<employee id=\"333\">" +
        			"<firstName>David</firstName>" +
        			"<lastName>Feezor</lastName>" +
        			"<location>USA</location>" +
        		"</employee>" +
        "</employees>";
       
		//Get Docuemnt Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		/*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();*/
	    InputSource is = new InputSource(new StringReader(xml));
	    
		
		//Build Document
		//Document document = builder.parse(new File("employees.xml"));
		Document document = builder.parse(is);
	 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		 
		//Get all employees
		NodeList nList = document.getElementsByTagName("employee");
		System.out.println("============================");
		 
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 System.out.println("");    //Just a separator
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    //Print each employee's detail
		    Element eElement = (Element) node;
		    System.out.println("Employee id : "    + eElement.getAttribute("id"));
		    System.out.println("First Name : "  + eElement.getElementsByTagName("firstName").item(0).getTextContent());
		    System.out.println("Last Name : "   + eElement.getElementsByTagName("lastName").item(0).getTextContent());
		    System.out.println("LOcation : "    + eElement.getElementsByTagName("location").item(0).getTextContent());
		 }
		}
	}
}
