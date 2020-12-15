package sk.kosickaakademia.nebus.shop;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXml {


    public static void readXmlAndWriteInConsole() {
        try {
            //creating a constructor of file class and parsing an XML file
            File file = new File("output/xmlfile.xml");
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            //vypis datumu
            NodeList nodeList = doc.getElementsByTagName("date");
            Node nNod = nodeList.item(0);
            System.out.println("Datum: " + nNod.getTextContent());

            //vypis casu
            nodeList = doc.getElementsByTagName("time");
            nNod = nodeList.item(0);
            System.out.println("Cas: " + nNod.getTextContent());

            //pocet poloziek
            //nodeList = doc.getElementsByTagName("items count");
            //Element eElement = (Element) nodeList;
            //System.out.println("Pocet poloziek: " + eElement.getAttribute("count"));

            //teraz tretreba vstupit do items count a tam cez for vypisovat
            System.out.println();
            nodeList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node nNode = nodeList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //System.out.println("Type : " + eElement.getAttribute("type"));
                    if(eElement.getAttribute("type").equals("weight")){
                        System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                        System.out.println("Weight: " + eElement.getElementsByTagName("weight").item(0).getTextContent());
                        System.out.println("PricePerKg: " + eElement.getElementsByTagName("pricePerKg").item(0).getTextContent());
                        System.out.println("PriceUnit: " + eElement.getAttribute("unit") + eElement.getElementsByTagName("priceUnit").item(0).getTextContent());

                        NodeList nodeeList = doc.getElementsByTagName("priceUnit");
                        Node nnNode = nodeeList.item(temp);
                        Element eeElement = (Element) nnNode;
                        System.out.println(eeElement.getAttribute("unit"));

                    }

                    if(eElement.getAttribute("type").equals("count")){
                        System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                        System.out.println("Count: " + eElement.getElementsByTagName("count").item(0).getTextContent());
                        System.out.println("PricePerUnit: " + eElement.getElementsByTagName("pricePerUnit").item(0).getTextContent());
                        System.out.println("PriceUnit: " + eElement.getAttribute("unit") + eElement.getElementsByTagName("priceUnit").item(0).getTextContent());

                        NodeList nodeeList = doc.getElementsByTagName("priceUnit");
                        Node nnNode = nodeeList.item(temp);
                        Element eeElement = (Element) nnNode;
                        System.out.println(eeElement.getAttribute("unit"));

                    }

                    if(eElement.getAttribute("type").equals("2.99")){
                        System.out.println("Dorucenie: 2,99");
                    }
                    //System.out.print("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    //System.out.print(" ");
                    //System.out.print("Cas: " + eElement.getElementsByTagName("cas").item(0).getTextContent());
                    //System.out.print(", ");
                    //System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    //System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
                System.out.println();
            }

            System.out.println("");
            nodeList = doc.getElementsByTagName("totalPrice");
            nNod = nodeList.item(0);
            System.out.println("Vysledna suma: " + nNod.getTextContent());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
