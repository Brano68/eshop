package sk.kosickaakademia.nebus.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Main {


    public static void main(String[] args){
        try {

            File fXmlFile = new File("resource/futbal.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());


            NodeList nList = doc.getElementsByTagName("datum");
            Node nNod = nList.item(0);
            System.out.println("Datum: " + nNod.getTextContent());
            System.out.println("Hrali: ");
            nList = doc.getElementsByTagName("domaci");
            nNod = nList.item(0);
            System.out.print(nNod.getTextContent() + " a ");
            nList = doc.getElementsByTagName("hostia");
            nNod = nList.item(0);
            System.out.print(nNod.getTextContent() + " vysledok ");
            nList = doc.getElementsByTagName("vysledok");
            nNod = nList.item(0);
            System.out.print(nNod.getTextContent());

            System.out.println();
            System.out.println("----------------------------");
            System.out.println("Goly");

            nList = doc.getElementsByTagName("gol");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //System.out.println("Meno : " + eElement.getAttribute("id"));
                    System.out.print("Meno: " + eElement.getElementsByTagName("meno").item(0).getTextContent());
                    System.out.print(" ");
                    System.out.print("Cas: " + eElement.getElementsByTagName("cas").item(0).getTextContent());
                    System.out.print(", ");
                    //System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    //System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

