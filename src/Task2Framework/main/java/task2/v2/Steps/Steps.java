package task2.v2.Steps;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task2.v2.Pages.*;
import task2.v2.Patterns.Decorator.Decorator;
import task2.v2.Patterns.FactoryMethod.ChromeDriverConf;
import task2.v2.Patterns.FactoryMethod.WebDriverConf;
import task2.v2.Patterns.Singleton.WebdriverSingleton;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Steps {

    protected WebDriver webDriver;
    private final String HUB = "http://localhost:4444/wd/hub";

    public void remoteDriverSetUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName("chrome");
        dc.setPlatform(Platform.ANY);
        webDriver = new RemoteWebDriver(new URL(HUB), dc);
        webDriver.manage().timeouts().pageLoadTimeout(20L, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    }

    public WebDriver initBrowser(){
        webDriver = new ChromeDriver();
        return webDriver;
    }
    public WebDriver initBrowserWithFactoryMethod() throws MalformedURLException {
        WebDriverConf webDriverConf = new ChromeDriverConf();
        webDriver = webDriverConf.factoryMethod();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        return webDriver;
    }

    public WebDriver initBrowserWithDecorator(){
        webDriver = new ChromeDriver();
        webDriver = new Decorator(webDriver);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        return webDriver;
    }

    public WebDriver initBrowserWithSingleton(){
        webDriver = WebdriverSingleton.getWebDriverInstance();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        return webDriver;
    }

    public void login(String USERNAME, String PASSWORD) {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.findElement(mainPage.loginField).sendKeys(USERNAME);
        webDriver.findElement(mainPage.passwordField).sendKeys(PASSWORD);
        webDriver.findElement(mainPage.submitButton).click();
    }

    public void createEmail() {
        InboxPage inboxPage = new InboxPage(webDriver);
        webDriver.findElement(inboxPage.createEmailBttn).click();
    }

    public void fillEmail(String text1, String to, String suject, String messageBody) throws IOException, InterruptedException {
        EmailPage emailPage = new EmailPage(webDriver);
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
        EmailPage emailPage = new EmailPage(webDriver);
        webDriver.findElement(emailPage.sendBttn).click();
    }

    public void navigatetoSentEmail() {
        EmailPage emailPage = new EmailPage(webDriver);
        webDriver.findElement(emailPage.sentFolder).click();
        contextClickToRefresh();
    }

    public void chooseLastSentEmail() {
        contextClickToRefresh();
        SentEmailFolder sentEmailFolder = new SentEmailFolder(webDriver);
        String email = webDriver.findElement(sentEmailFolder.lastSentEmail).getAttribute("href").toString();
        webDriver.get(email);
    }

    public String getSentEmailSubj() {
        SentEmailDetailsPage sentEmailDetailsPage = new SentEmailDetailsPage(webDriver);
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
        try {
            EmailPage emailPage = new EmailPage(webDriver);
            webDriver.findElement(emailPage.saveDraftBttn).click();

            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e) {

        }
    }

    public void navigateToDraftsFolder() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        webDriver.findElement(draftsPage.draftsFolderBttn).click();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    public String getDraftEmailsubj() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        String subj = webDriver.findElement(draftsPage.lastCreatedEmailItem).getAttribute("data-subject").toString();
        return subj;
    }

    public void deleteLastCreatedEmail() throws InterruptedException {
        DraftsPage draftsPage = new DraftsPage(webDriver);
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
        DraftsPage draftsPage = new DraftsPage(webDriver);
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
