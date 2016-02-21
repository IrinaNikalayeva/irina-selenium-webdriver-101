package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;
import task2.v2.Utils.RandomGenerator;

import java.io.IOException;


public class CreateAndSendTest extends BaseTestConf{
    protected Steps steps;
    String to = "maven.test@mail.ru";
    String subject = "Test email";
    String messageBody = "Hello Maven!";
    String text = RandomGenerator.getRandomString(5);

   @Test
    public void createAndSend() throws InterruptedException, IOException {
        steps.createEmail();
        steps.fillEmail(text, to, subject, messageBody);
        steps.sendEmail();
        steps.navigatetoSentEmail();
        steps.chooseLastSentEmail();
        Assert.assertTrue(steps.getSentEmailSubj().contains(text), "Email doesn't exists!");

    }

}
