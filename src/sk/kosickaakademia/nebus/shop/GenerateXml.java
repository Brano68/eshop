package sk.kosickaakademia.nebus.shop;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.kosickaakademia.nebus.shop.cart.Cart;
import sk.kosickaakademia.nebus.shop.counable.CountItem;
import sk.kosickaakademia.nebus.shop.service.ServiceInterface;
import sk.kosickaakademia.nebus.shop.uncountable.WeightItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GenerateXml {
    public static final String xmlFilePath = "output/xmlfile.xml";


    public static void generateFileXML(Cart cart){
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("bill");
            document.appendChild(root);

            // date element
            Element date = document.createElement("date");
            LocalDate localDate = LocalDate.now();//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            String formattedString = localDate.format(formatter);
            root.appendChild(date).setTextContent(formattedString);

            //System.out.println(formattedString);

            // time element
            Element time = document.createElement("time");
            LocalTime localTime = LocalTime.now();
            String hodiny = String.valueOf(localTime.getHour());
            String minuty = String.valueOf(localTime.getMinute());
            String sekundy = String.valueOf(localTime.getSecond());
            root.appendChild(time).setTextContent(hodiny + ":" + minuty + ":" + sekundy);

            //System.out.println(hodiny + ":" + minuty + ":" + sekundy);

            Element items = document.createElement("items");
            Attr attr = document.createAttribute("count");
            attr.setValue(String.valueOf(cart.getList().size()));
            items.setAttributeNode(attr);
            root.appendChild(items);

            for(int i = 0; i < cart.getList().size(); i++){
                Element item = document.createElement("item");
                if(cart.getList().get(i) instanceof CountItem){
                    Attr attr1 = document.createAttribute("type");
                    attr1.setValue("count");
                    item.setAttributeNode(attr1);
                    items.appendChild(item);
                    //teraz pridat nazov pocet cena za kilo a vysledna cena
                    Element i1 = document.createElement("name");
                    item.appendChild(i1).setTextContent(cart.getList().get(i).getName());

                    Element i2 = document.createElement("count");
                    int count = ((CountItem) cart.getList().get(i)).getCount();
                    item.appendChild(i2).setTextContent(String.valueOf(count));

                    Element i3 = document.createElement("pricePerUnit");
                    double pricePerUnit = cart.getList().get(i).getPrice();
                    item.appendChild(i3).setTextContent(String.valueOf(pricePerUnit));

                    Element i4 = document.createElement("priceUnit");
                    Attr attr2 = document.createAttribute("unit");
                    attr2.setValue("eur");
                    i4.setAttributeNode(attr2);
                    double priceUnit = cart.getList().get(i).getItemPrice();
                    item.appendChild(i4).setTextContent(String.valueOf(priceUnit));
                }

                if(cart.getList().get(i) instanceof WeightItem){
                    Attr attr1 = document.createAttribute("type");
                    attr1.setValue("weight");
                    item.setAttributeNode(attr1);
                    items.appendChild(item);
                    //teraz pridat nazov pocet cena za kilo a vysledna cena
                    Element i1 = document.createElement("name");
                    item.appendChild(i1).setTextContent(cart.getList().get(i).getName());

                    Element i2 = document.createElement("weight");
                    double weight = ((WeightItem) cart.getList().get(i)).getWeight();
                    item.appendChild(i2).setTextContent(String.valueOf(weight));

                    Element i3 = document.createElement("pricePerKg");
                    double pricePerKg = cart.getList().get(i).getPrice();
                    item.appendChild(i3).setTextContent(String.valueOf(pricePerKg));

                    Element i4 = document.createElement("priceUnit");
                    Attr attr2 = document.createAttribute("unit");
                    attr2.setValue("eur");
                    i4.setAttributeNode(attr2);
                    double priceUnit = cart.getList().get(i).getItemPrice();
                    item.appendChild(i4).setTextContent(String.valueOf(priceUnit));
                }

                if(cart.getList().get(i) instanceof ServiceInterface){
                    Attr attr1 = document.createAttribute("type");
                    attr1.setValue("service");
                    item.setAttributeNode(attr1);
                    attr1.setValue("2.99");
                    items.appendChild(item);
                }
            }


            //total price
            Element totalPrice = document.createElement("totalPrice");
            double totalCena = cart.getTotalPrice();
            root.appendChild(totalPrice).setTextContent(String.valueOf(totalCena));





            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }


}
