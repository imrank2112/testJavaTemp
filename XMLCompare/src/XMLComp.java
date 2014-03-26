


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
 
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 





import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.ElementNameQualifier;
import org.custommonkey.xmlunit.MatchTracker;
import org.custommonkey.xmlunit.NodeDetail;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
//first java
 
public class XMLComp {
 
	
public static Logger log = Logger.getLogger(
			XMLComp.class.getName());
   
    	public static void main(String[] args) {
    		JOptionPane pane =new JOptionPane();
    		
            FileReader fr1 = null;
            FileReader fr2 = null;
            try {
            	//C:\\Users\\Imran11296\\Desktop\\CapacityCheck.xml
            	String source =pane.showInputDialog("please enter the source xml");
                if(source.equals(""))
                {
                	log.error("Source Path is not entered");
                }
                else
                {
            	fr1 = new FileReader(source);
                }
                //C:\\Users\\Imran11296\\Desktop\\CapacityCheck1.xml
               
                String destination = pane.showInputDialog("please enter the destination xml");
                if(destination.equals(""))
                {
                	log.error("Destination is not entered");
                }
                else
                {
                	 fr2 = new FileReader(destination);
                }
               
                if(source.isEmpty()||destination.isEmpty())
                {
                	log.fatal("Both Source and Destination is empty");
                	log.fatal("Aborting the Execution");
                	System.exit(0);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
     
            try {
                Diff diff = new Diff(fr1, fr2);
//                System.out.println("Similar? " + diff.similar());
//                System.out.println("Identical? " + diff.identical());
                log.info("Similar? " + diff.similar());
                log.info("Identical? " + diff.identical());
                
     
                DetailedDiff detDiff = new DetailedDiff(diff);
//                detDiff.overrideMatchTracker(new MatchTrackerImpl());
//                detDiff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
     
                List differences = detDiff.getAllDifferences();
                for (Object object : differences) {
                    Difference difference = (Difference)object;
//                    System.out.println("***********************");
//                    System.out.println(difference);
//                    System.out.println("***********************");
                    log.info("****************************");
                    log.info(difference);
                    log.info("****************************");
                    pane.showMessageDialog(null, "Execution Completed");
                }
     
     
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
    }
 
    


class MatchTrackerImpl implements MatchTracker {
	 
    public void matchFound(Difference difference) {
        if (difference != null) {
            NodeDetail controlNode = difference.getControlNodeDetail();
            NodeDetail testNode = difference.getTestNodeDetail();
 
            String controlNodeValue = printNode(controlNode.getNode());
            String testNodeValue = printNode(testNode.getNode());
 
            if (controlNodeValue != null) {
//                System.out.println("####################");
//                System.out.println("Control Node: " + controlNodeValue);
           XMLComp.log.info("####################");
           XMLComp.log.info("Control Node: "+ controlNodeValue);
            }
            
            if (testNodeValue != null) {
//                System.out.println("Test Node: " + testNodeValue);
//                System.out.println("####################");
                
                XMLComp.log.info("Test Node: " + testNodeValue);
                XMLComp.log.info("####################");
            }
        }
    }


private static String printNode(Node node) {
    if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception");
            XMLComp.log.info("nodeToString Transformer Exception");
        }
        return sw.toString();

    }
    return null;
}
}

