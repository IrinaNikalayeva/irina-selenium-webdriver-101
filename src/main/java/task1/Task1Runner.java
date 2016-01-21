package task1;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Task1Runner {
    public static void main(String[] args) throws InterruptedException, IOException, SAXException, ParserConfigurationException {
        TestNG testng = new TestNG();
        for (XmlSuite suite : new Parser("./testng.xml").parseToList()) {
            testng.setCommandLineSuite(suite);
        }
        testng.run();
    }
}
