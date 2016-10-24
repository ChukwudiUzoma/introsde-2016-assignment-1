package introSDE;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HealthProfileReader {

    private Document document;
    private XPath xpath;

    public static final String MY_FILE = "people.xml";//Create file stream from people.xml

    public void insertXML() throws ParserConfigurationException, SAXException, IOException {
    	//DocumentBuilderFactory - Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents	
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();// creation of object of DocumentBuilderFactory
        domFactory.setNamespaceAware(true);//Specifies that the parser produced by this code will provide support for XML namespaces.
        DocumentBuilder builder = domFactory.newDocumentBuilder();//Defines the API to obtain DOM Document instances from an XML document
        System.out.println("Loading people.xml...");
        document = builder.parse(MY_FILE);//Create a Document from a file or stream
  
        createXPathObj(); //creating xpath object
    }

    //BUILD XPATH
    public XPath createXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }
     
     // GET WEIGHT WITH firstname AND lastname PROVIDED
    public Node getWeight(String firstname, String lastname) throws XPathExpressionException {
    	//Preparation of Path expression and evaluation
            XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/weight");
            Node node = (Node) expr.evaluate(document, XPathConstants.NODE);
            return node;
    }
        
    // GET HEIGHT WITH firstname AND lastname PROVIDED
    public Node getHeight(String firstname, String lastname) throws XPathExpressionException {
    	//Preparation of Path expression and evaluation
            XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/height");
            Node node = (Node) expr.evaluate(document, XPathConstants.NODE);
            return node;
    }

    //Q.2 - Make a function that prints all people in the list with detail
    public NodeList getAllPeople() throws XPathExpressionException {  

        XPathExpression expr = xpath.compile("/people/*");//Preparation of Path expression
        NodeList node = (NodeList) expr.evaluate(document, XPathConstants.NODESET);//evaluation
        return node;
    }

    //Q.3 A function that accepts id as parameter and prints the HealthProfile of the person with that id
    public Node printHealthProfileById(String id) throws XPathExpressionException {

            XPathExpression expr = xpath.compile("/people/person[@id='" + id + "']/healthprofile");//Preparation of Path expression
            Node nodes = (Node) expr.evaluate(document, XPathConstants.NODE);//Preparation of Path expression
            return nodes;
    }

    //Q.4 A function which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition
    public NodeList getPersonByWeightandParam(String param, int weight) throws XPathExpressionException {
       
        XPathExpression expr = xpath.compile("/people/person/healthprofile[weight" + param + weight + "]/../*[self::firstname or self::lastname]/text()");        
        NodeList nodes = (NodeList)expr.evaluate(document, XPathConstants.NODESET);
        return nodes;
    }
    
    private static void printNode(Node targetNode){				
		for(int l = 0; l < targetNode.getChildNodes().getLength(); l++){
				Node innerNode = targetNode.getChildNodes().item(l);	
				if(innerNode.getNodeType() == 1){
					if(innerNode.getNodeName().equals("healthprofile")){
						printNode(innerNode);
					} else {
						System.out.println(innerNode.getNodeName() + ": " + innerNode.getTextContent());
					}
				}				
		}		
		System.out.println();
	}

    public static void main(String[] args) throws ParserConfigurationException, SAXException,
		IOException, XPathExpressionException {
    	
        HealthProfileReader healthProfileReader = new HealthProfileReader();
        healthProfileReader.insertXML();//load xml file

        /*
         * To get health profile information of a person depending on the following variables: lastname, firstname, id, weight, lets initiate the ffg variables
         */
        String firstname = "Yahala R. P.";
        String lastname = "Martinolobdhe";
        String id = "0005";
        String param = ">";
        int weight = 90;
        
        //Q.1 Use xpath to implement methods like getWeight and getHeight (getWeight(personID) returns weight of a given person, the same for getHeight)
        //getWeight
        Node node = healthProfileReader.getWeight(firstname , lastname);        
        System.out.println("Weight and Height of the person with id "+ id);
        System.out.println(firstname +" weights " + node.getTextContent() + " kilograms.");
        node = healthProfileReader.getHeight(firstname , lastname);
        System.out.println(firstname +" is " + node.getTextContent() + " meters tall.");
        System.out.println();

        //Printing the health profile of the person with a given id
        node = healthProfileReader.printHealthProfileById (id);
		System.out.println("HEALTH PROFILE DETAIL OF PERSON WITH ID= " + id);
		printNode(node);

        //Printing the people with weight a given parameter (> 90kg)
        NodeList nodes = healthProfileReader.getPersonByWeightandParam(param, weight);
        System.out.println("LIST OF PEOPLE WITH WEIGHT " + param + " " + weight + "kg: ");
        
        if(nodes.getLength()==0)
        	{
        		System.out.println("OOOps, no one like that");
        	}else
        		{
        			for (int i = 0; i < nodes.getLength(); i += 2) {
        			System.out.println(nodes.item(i).getTextContent() + " " + nodes.item(i+1).getTextContent());
        		}
		}
	   
       System.out.println();

        //Getting all the people with health profile details from the list
        NodeList peopleNodes = healthProfileReader.getAllPeople();
        System.out.println("LIST OF EVERYONE WITH THEIR RESPECTIVE HEALTH PROFILE: ");   
               
		for (int i = 0; i < peopleNodes.getLength(); i ++) {
            printNode(peopleNodes.item(i));
		}
    }    
	   //PRINTING THE NAMES OF THE NODES OF THE LIST 
	
}