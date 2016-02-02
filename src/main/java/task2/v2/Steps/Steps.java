package task2.v2.Steps;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import task2.v2.Pages.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Steps {

    private WebDriver webDriver;

    public void initBrowser() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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

    public void fillEmail(String text1, String to, String suject, String messageBody) throws IOException, InterruptedException {
        EmailPage emailPage = new EmailPage();
        WebElement toField = webDriver.findElement(emailPage.toField);
        toField.sendKeys(to);
        toField.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", toField);
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("highlight.png"));
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
        contextClickToRefresh();
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
        contextClickToRefresh();

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
        DraftsPage draftsPage = new DraftsPage();
        webDriver.findElement(draftsPage.draftsFolderBttn).click();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

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
        contextClickToRefresh();
        checkEmailsAsUnread();
    }

    public void contextClickToRefresh() {
        Actions actions = new Actions(webDriver);
        actions.contextClick().sendKeys(Keys.chord(Keys.CONTROL, "r")).build().perform();
    }

    public void checkEmailsAsUnread() {
        DraftsPage draftsPage = new DraftsPage();
        webDriver.findElement(draftsPage.lastCreatedEmailItem).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        WebElement dropDown = webDriver.findElement(draftsPage.dropDown);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(dropDown).click().sendKeys(dropDown, Keys.DOWN).sendKeys(dropDown, Keys.DOWN).sendKeys(dropDown, Keys.ENTER);
        actions.build().perform();
    }

    public void cleanUp() {
        webDriver.close();
    }

}
