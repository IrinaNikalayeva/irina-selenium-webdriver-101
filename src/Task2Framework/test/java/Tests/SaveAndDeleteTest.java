package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;

import java.io.IOException;

public class SaveAndDeleteTest extends BaseTestConf {
    protected Steps steps;
    String to = "maven.test@mail.ru";
    String subject = "Test email";
    String messageBody = "Hello Maven!";
    String text = task2.v2.Utils.RandomGenerator.getRandomString(5);

    @Test
    public void saveAndDelete() throws InterruptedException, IOException {
        steps.createEmail();
        steps.fillEmail(text, to, subject, messageBody);
        steps.saveToDrafts();
        steps.navigateToDraftsFolder();
        steps.deleteLastCreatedEmail();
        System.out.println(steps.getDraftEmailsubj());
        Assert.assertFalse(steps.getDraftEmailsubj().contains(text), "Email wasn't deleted!");
    }

}
