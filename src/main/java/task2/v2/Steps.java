package task2.v2;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Steps {

    private WebDriver webDriver;

    public void initBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\TA\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void login(String USERNAME, String PASSWORD) {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.findElement(mainPage.loginField).sendKeys(USERNAME);
        webDriver.findElement(mainPage.passwordField).sendKeys(PASSWORD);
        webDriver.findElement(mainPage.submitButton).click();
    }

    public EmailPage createEmail() {
        InboxPage inboxPage = new InboxPage(webDriver);
        webDriver.findElement(inboxPage.createEmailBttn).click();
        return PageFactory.initElements(webDriver, EmailPage.class);
    }

    public void fillEmail(String text1, String to, String suject, String messageBody) {
        EmailPage emailPage = new EmailPage();
        webDriver.findElement(emailPage.toField).sendKeys(to);
        webDriver.findElement(emailPage.subjectField).sendKeys(suject + text1);
        webDriver.switchTo().frame(webDriver.findElement(emailPage.bodyFrame));
        webDriver.findElement(emailPage.bodyField).sendKeys(messageBody);
        webDriver.switchTo().defaultContent();
    }

    public void sendEmail() throws InterruptedException {
        EmailPage emailPage = new EmailPage();
        webDriver.findElement(emailPage.sendBttn).click();
        Thread.sleep(1000);
    }

    public void navigatetoSentEmail() {
        EmailPage emailPage = new EmailPage();
        webDriver.findElement(emailPage.sentFolder).click();
        webDriver.navigate().refresh();
    }

    public void chooseLastSentEmail() {
        SentEmailFolder sentEmailFolder = new SentEmailFolder();
        String email = webDriver.findElement(sentEmailFolder.lastSentEmail).getAttribute("href").toString();
        webDriver.get(email);
    }

    public String getSentEmailSubj() {
        SentEmailDetailsPage sentEmailDetailsPage = new SentEmailDetailsPage();
        String subj = webDriver.findElement(sentEmailDetailsPage.sentEmailSubj).getText();
        return subj;
    }

    public void navigateToInboxFolder() {
        InboxPage inboxPage = new InboxPage(webDriver);
        webDriver.findElement(inboxPage.inboxFolder).click();
        webDriver.navigate().refresh();
    }

    public void chooseLastRecievedEmail() {
        InboxPage inboxPage = new InboxPage(webDriver);
        String email = webDriver.findElement(inboxPage.lastSentEmail).getAttribute("href").toString();
        webDriver.get(email);

    }

    public String getLastRecievedEmailubj() {
        String subj = webDriver.findElement(By.className("b-letter__head__subj__text")).getText();
        return subj;
    }

    public void saveToDrafts() throws InterruptedException {
        EmailPage emailPage = new EmailPage();
        webDriver.findElement(emailPage.saveDraftBttn).click();
        Thread.sleep(2000);
    }

    public void navigateToDraftsFolder() {
        task2.v2.DraftsPage draftsPage = new task2.v2.DraftsPage();
        webDriver.findElement(draftsPage.draftsFolderBttn).click();
    }

    public String getDraftEmailsubj() {
        DraftsPage draftsPage = new DraftsPage();
        String subj = webDriver.findElement(draftsPage.lastCreatedEmailItem).getAttribute("data-subject").toString();
        return subj;
    }

    public void deleteLastCreatedEmail() throws InterruptedException {
        DraftsPage draftsPage = new DraftsPage();
        Thread.sleep(1000);
        webDriver.findElement(draftsPage.chkBox).click();
        webDriver.findElement(draftsPage.lastCreatedEmailItem).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
    }

    public void cleanUp() {
        webDriver.close();
    }

}
