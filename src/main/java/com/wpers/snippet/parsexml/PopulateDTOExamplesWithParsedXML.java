package com.wpers.snippet.parsexml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PopulateDTOExamplesWithParsedXML
{
   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
   {
        List<Employee> employees = parseEmployeesXML();
        System.out.println(employees);
   }

   private static List<Employee> parseEmployeesXML() throws ParserConfigurationException, SAXException, IOException
   {
      //Initialize a list of employees
      List<Employee> employees = new ArrayList<Employee>();
      Employee employee = null;
      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File("employees.xml"));
      document.getDocumentElement().normalize();
      NodeList nList = document.getElementsByTagName("employee");
      for (int temp = 0; temp < nList.getLength(); temp++)
      {
         Node node = nList.item(temp);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            Element eElement = (Element) node;
            //Create new Employee Object
            employee = new Employee();
            employee.setId(Integer.parseInt(eElement.getAttribute("id")));
            employee.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
            employee.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
            employee.setLocation(eElement.getElementsByTagName("location").item(0).getTextContent());
            
            //Add Employee to list
            employees.add(employee);
         }
      }
      return employees;
   }
}
