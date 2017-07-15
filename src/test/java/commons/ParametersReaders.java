package commons;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParametersReaders {

    private ParametersReaders(){

    }

    public static String getPropByName(String name){

        String prop = "";

        try {
            File fXmlFile = new File("parameters.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName(name);
            if(nList.getLength()>1){
                System.out.printf("There is more than one parameters with key: %s. Taking first one.\n",name);
            }
            prop = nList.item(0).getTextContent();

        }   catch(Exception e){
            e.printStackTrace();
        }
        return prop;
    }

}
