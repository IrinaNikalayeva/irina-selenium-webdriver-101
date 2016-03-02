package TestsUsingPatterns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;

import java.io.IOException;
import java.net.MalformedURLException;


public class CreateAndSendTest extends BaseTestConf {
    Logger logger = LogManager.getRootLogger();

    @BeforeTest(enabled = true)
    @Override
    public void setUp() throws MalformedURLException {
        steps = new Steps();
        steps.remoteDriverSetUp(); // if plan to use node
        try {
            webDriver = steps.initBrowserWithFactoryMethod();
            steps.login(USERNAME, PASSWORD);
        } catch (MalformedURLException e) {
            logger.error("Browser is not initialized");
            e.printStackTrace();
        }
    }

    @Test(enabled = true)
    public void createAndSend() throws InterruptedException, IOException {
        steps.createEmail();
        steps.fillEmail(text, to, subject, messageBody);
        steps.sendEmail();
        steps.navigatetoSentEmail();
        steps.chooseLastSentEmail();
        Assert.assertTrue(steps.getSentEmailSubj().contains(text), "Email doesn't exists!");


    }

}
