package task2.v2.Runner;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNG testng =new TestNG();

        try {
            for (XmlSuite suite : new Parser(args[0]).parseToList()) {
                testng.setXmlSuites((List<XmlSuite>) suite);
                testng.run();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }
}
