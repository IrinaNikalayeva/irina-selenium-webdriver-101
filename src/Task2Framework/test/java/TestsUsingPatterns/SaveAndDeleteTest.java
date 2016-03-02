package TestsUsingPatterns;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task2.v2.Steps.Steps;

import java.io.IOException;
import java.net.MalformedURLException;

public class SaveAndDeleteTest extends BaseTestConf {

    @BeforeTest(enabled = false)
    @Override
    public void setUp() {
        try {
            steps = new Steps();
            webDriver = steps.initBrowserWithFactoryMethod();
            steps.login(USERNAME, PASSWORD);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
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
