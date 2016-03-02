package TestsUsingPatterns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;

import java.io.IOException;


public class SendAndVerifyTest extends BaseTestConf {
    Logger logger = LogManager.getLogger(SendAndVerifyTest.class);
    @BeforeTest(enabled = true)
    @Override
    public void setUp() {
        steps = new Steps();
        webDriver = steps.initBrowserWithSingleton();
        steps.login(USERNAME, PASSWORD);
    }

    @Test(enabled = true)
    public void sendAndVerify() throws InterruptedException, IOException {
        try {
            steps.createEmail();
            steps.fillEmail(text, to, subject, messageBody);
            steps.sendEmail();
            steps.navigateToInboxFolder();
            steps.chooseLastRecievedEmail();
            Assert.assertTrue(steps.getLastRecievedEmailubj().contains(text), "Email doesn't exists!");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
