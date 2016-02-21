package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;

import java.io.IOException;


public class SendAndVerifyTest extends BaseTestConf {
    protected Steps steps;
    String to = "maven.test@mail.ru";
    String subject = "Test email";
    String messageBody = "Hello Maven!";
    String text = task2.v2.Utils.RandomGenerator.getRandomString(5);

    @Test
    public void sendAndVerify() throws InterruptedException, IOException {
        try {
            steps.createEmail();
            steps.fillEmail(text, to, subject, messageBody);
            steps.sendEmail();
            steps.navigateToInboxFolder();
            steps.chooseLastRecievedEmail();
            Assert.assertTrue(steps.getLastRecievedEmailubj().contains(text), "Email doesn't exists!");
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
