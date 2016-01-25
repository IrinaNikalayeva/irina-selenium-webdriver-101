package task2.v2;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task1.Util;

public class Tests {
    private Steps steps;
    String text = Util.getRandomString(5);
    static final String USERNAME = "maven.test";
    static final String PASSWORD = "Qwerty123";
    String to = "maven.test@mail.ru";
    String suject = "Test email";
    String messageBody = "Hello Maven!";

    @BeforeSuite
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @BeforeTest
    public void login(){
        steps.login(USERNAME, PASSWORD);
    }

   @Test
    public void createAndSend() throws InterruptedException {
       // steps.login(USERNAME, PASSWORD);
        steps.createEmail();
        steps.fillEmail(text, to, suject, messageBody);
        steps.sendEmail();
        steps.navigatetoSentEmail();
        steps.chooseLastSentEmail();
        Assert.assertTrue(steps.getSentEmailSubj().contains(text), "Email doesn't exists!");

    }

   @Test
    public void sendAndVerify() throws InterruptedException {
        //steps.login(USERNAME, PASSWORD);
        steps.createEmail();
        steps.fillEmail(text, to, suject, messageBody);
        steps.sendEmail();
        steps.navigateToInboxFolder();
        steps.chooseLastRecievedEmail();
        Assert.assertTrue(steps.getLastRecievedEmailubj().contains(text), "Email doesn't exists!");
    }

    @Test
    public void saveAndDelete() throws InterruptedException {
       // steps.login(USERNAME, PASSWORD);
        steps.createEmail();
        steps.fillEmail(text, to, suject, messageBody);
        steps.saveToDrafts();
        steps.navigateToDraftsFolder();
        steps.deleteLastCreatedEmail();
        System.out.println(steps.getDraftEmailsubj());
        Assert.assertFalse(steps.getDraftEmailsubj().contains(text), "Email wasn't deleted!");
    }

    @AfterTest
    public void cleanUp(){
        steps.cleanUp();
    }




    /*@Test
    public void test() throws InterruptedException {
        InboxPage mainPage = new InboxPage(webDriver);
        mainPage.create("text", to, suject, messageBody);
    }*/

    /*@Test
    public void createAndSend() throws InterruptedException {
        create(text);
        webDriver.findElement(sendBttn).click();
        webDriver.findElement(sentFolder).click();
        Thread.sleep(1000);
        webDriver.navigate().refresh();
        webDriver.get(webDriver.findElement(lastSentEmail).getAttribute("href").toString());
        String sudj = webDriver.findElement(sentEmailSubj).getText();
        System.out.println("text " + text + " subj " + sudj);
        Assert.assertTrue(sudj.contains(text), "Email doesn't exists!");
    }

    @Test
    public void sendAndVerify() throws InterruptedException {
        create(text);
        Thread.sleep(500);
        webDriver.findElement(sendBttn).click();
        webDriver.findElement(inboxFolder).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.navigate().refresh();
        String email = webDriver.findElement(lastSentEmail).getAttribute("href").toString();
        webDriver.get(email);
        String sudj = webDriver.findElement(By.className("b-letter__head__subj__text")).getText();
        Assert.assertTrue(sudj.contains(text), "Email doesn't exists!");
    }

    @Test
    public void saveDraftAndRemove() throws InterruptedException {
        create(text);
        webDriver.findElement(saveDraftBttn).click();
        Thread.sleep(2000);
        webDriver.findElement(draftsBttn).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(chkBox).click();
        webDriver.findElement(lastCreatedEmailItem).sendKeys(Keys.DELETE);
    }

    @AfterTest
    public void cleanUp() {
        webDriver.close();
    }*/
}

