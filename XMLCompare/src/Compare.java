import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Compare {

	
	
	public static void main(String[] args)
	{
		try {
			compareXML();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void compareXML() throws ParserConfigurationException, SAXException, IOException
	{
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setCoalescing(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc1 = db.parse(new File("C:\\Users\\Imran11296\\Desktop\\CapacityCheck.xml"));
		doc1.normalizeDocument();

		Document doc2 = db.parse(new File("C:\\Users\\Imran11296\\Desktop\\CapacityCheck1.xml"));
		doc2.normalizeDocument();

		if(doc1.isEqualNode(doc2))
		{
			NodeList childnodes = doc1.getChildNodes();
			String abc = childnodes.item(1).getNodeValue();
			System.out.println(childnodes.getLength());
			if(doc1.getNodeValue().equals(doc2.getNodeValue()))
			{
				System.out.println("Node Value Matches");
			}
			else
			{
				System.out.println("Node Value doesn't Match");
			}
		System.out.println("Match Found");
		}
		else
		{
			System.out.println("Match not Found");
		}
//		Assert.assertTrue(doc1.isEqualNode(doc2));
	}
}
