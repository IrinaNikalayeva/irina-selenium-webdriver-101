package task2.v2.Tests;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task1.Util;
import task2.v2.Steps.Steps;

import java.io.IOException;
import java.net.MalformedURLException;

public class Tests {
    private Steps steps;
    String text = Util.getRandomString(5);
    static final String USERNAME = "maven.test";
    static final String PASSWORD = "Qwerty123";
    String to = "maven.test@mail.ru";
    String suject = "Test email";
    String messageBody = "Hello Maven!";


    @BeforeSuite
    public void setUp() throws MalformedURLException {
        steps = new Steps();
        steps.remoteDriverSetUp(); // if plan to use node
        steps.initBrowser();
    }

    @BeforeTest
    public void login() {
        steps.login(USERNAME, PASSWORD);
    }

   @Test
    public void createAndSend() throws InterruptedException, IOException {
        steps.createEmail();
        steps.fillEmail(text, to, suject, messageBody);
        steps.sendEmail();
        steps.navigatetoSentEmail();
        steps.chooseLastSentEmail();
        Assert.assertTrue(steps.getSentEmailSubj().contains(text), "Email doesn't exists!");

    }

   @Test
    public void sendAndVerify() throws InterruptedException, IOException {
        steps.createEmail();
        steps.fillEmail(text, to, suject, messageBody);
        steps.sendEmail();
        steps.navigateToInboxFolder();
        steps.chooseLastRecievedEmail();
        Assert.assertTrue(steps.getLastRecievedEmailubj().contains(text), "Email doesn't exists!");
    }

    @Test
    public void saveAndDelete() throws InterruptedException, IOException {
        steps.createEmail();
        steps.fillEmail(text, to, suject, messageBody);
        steps.saveToDrafts();
        steps.navigateToDraftsFolder();
        steps.deleteLastCreatedEmail();
        System.out.println(steps.getDraftEmailsubj());
        Assert.assertFalse(steps.getDraftEmailsubj().contains(text), "Email wasn't deleted!");
    }

    @AfterTest
    public void cleanUp() {
        steps.cleanUp();
    }
}

