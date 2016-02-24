package TestsUsingPatterns;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;

import java.io.IOException;


public class SendAndVerifyTest extends BaseTestConf {
    @BeforeTest
    @Override
    public void setUp() {
        steps = new Steps();
        webDriver = steps.initBrowserWithSingleton();
        steps.login(USERNAME, PASSWORD);
    }

    @Test
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
