package commons;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParametersReaders {

    private ParametersReaders() {
    }


    /**
     * Static method for parsing and searching properties by name (xml tag) in file located in main directory of project
     * file called: "parameters.xml".
     *
     * @param name XML Tag name
     * @return Value of XML Tag name as String
     */
    public static String getPropertyByName(String name) {

        String prop = "";

        try {
            File fXmlFile = new File("parameters.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName(name);
            if (nList.getLength() > 1) {
                System.out.printf("There is more than one parameters with key: %s. Taking first one.\n", name);
            }
            prop = nList.item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Static method for parsing and searching properties when there is more than one properties with the same tag
     * Method return list of values with the same XML tag.
     *
     * @param name XML Tag name
     * @return Values of XML Tag as List<String>
     */
    public static List<String> getPropertyListByName(String name) {

        List<String> list = new ArrayList<>();

        try {
            File fXmlFile = new File("parameters.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName(name);

            for (int i = 0; i < nList.getLength(); i++) {
                list.add(nList.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
