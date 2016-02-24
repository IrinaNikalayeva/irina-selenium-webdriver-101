package task2.v2.Runner;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestRunner {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNG testng = new TestNG();
        try {
            for (XmlSuite suite : new Parser("./src/Task2Framework/test/resources/TestSuite.xml").parseToList()) {
                testng.setCommandLineSuite(suite);
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
        testng.run();
    }
}

