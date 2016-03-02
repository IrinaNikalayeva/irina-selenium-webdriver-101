package task2.v2.Steps;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import task2.v2.Utils.Highlighter;
import task2.v2.Utils.ScreenshotMaker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Steps {

    protected WebDriver webDriver;
    private final String HUB = "http://localhost:4444/wd/hub";
    Logger logger = LogManager.getRootLogger();
    Highlighter highlighter = new Highlighter();

    public void remoteDriverSetUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName("chrome");
        dc.setPlatform(Platform.ANY);
        webDriver = new RemoteWebDriver(new URL(HUB), dc);
        webDriver.manage().timeouts().pageLoadTimeout(20L, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        logger.info("Remote Browser is configured");
    }

    public WebDriver initBrowser() {
        webDriver = new ChromeDriver();
        logger.info("Browser is initialized");
        return webDriver;
    }

    public WebDriver initBrowserWithFactoryMethod() throws MalformedURLException {
        WebDriverConf webDriverConf = new ChromeDriverConf();
        webDriver = webDriverConf.factoryMethod();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        logger.info("Browser is initialized");
        return webDriver;
    }

    public WebDriver initBrowserWithDecorator() {
        webDriver = new ChromeDriver();
        webDriver = new Decorator(webDriver);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        logger.info("Browser is initialized");
        return webDriver;
    }

    public WebDriver initBrowserWithSingleton() {
        logger.debug("getting webDriver instance...");
        webDriver = WebdriverSingleton.getWebDriverInstance();
        logger.info("webDriver is created");
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        logger.info("Browser is initialized");
        return webDriver;
    }

    public void login(String USERNAME, String PASSWORD) {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        logger.info("Page is loaded");
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        logger.warn("looking for elements");
        webDriver.findElement(mainPage.loginField).sendKeys(USERNAME);
        webDriver.findElement(mainPage.passwordField).sendKeys(PASSWORD);
        webDriver.findElement(mainPage.submitButton).click();
        logger.info("User's signed in");
    }

    public void createEmail() {
        InboxPage inboxPage = new InboxPage(webDriver);
        webDriver.findElement(inboxPage.createEmailBttn).click();
        logger.info("Create button is clicked");
    }

    public void fillEmail(String text1, String to, String suject, String messageBody) throws IOException, InterruptedException {
        EmailPage emailPage = new EmailPage(webDriver);
        logger.warn("filling in email info");
        WebElement toField = webDriver.findElement(emailPage.toField);
        toField.sendKeys(to);
        logger.debug("getting background color...");
        toField.getCssValue("backgroundColor");
        logger.info("background color is get");
        logger.debug("taking screenshot...");
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", toField);
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("highlight.png"));
        logger.info("screenshot is taken");
        webDriver.findElement(emailPage.subjectField).sendKeys(suject + text1);
        logger.warn("switching to frame");
        webDriver.switchTo().frame(webDriver.findElement(emailPage.bodyFrame));
        webDriver.findElement(emailPage.bodyField).sendKeys(messageBody);
        logger.warn("switching to default content");
        webDriver.switchTo().defaultContent();
        logger.info("switched to default content");
    }

    public void sendEmail() throws InterruptedException {
        EmailPage emailPage = new EmailPage(webDriver);
        logger.debug("navigating to send emails...");
        webDriver.findElement(emailPage.sendBttn).click();
    }

    public void navigatetoSentEmail() throws InterruptedException {
        EmailPage emailPage = new EmailPage(webDriver);
        Highlighter highlighter = new Highlighter();
        logger.debug("highlighting element...");
        highlighter.highlight(webDriver, webDriver.findElement(emailPage.sentFolder));
        logger.debug("making screenshot");
        ScreenshotMaker screenshotMaker = new ScreenshotMaker();
        screenshotMaker.makeScreenshot(webDriver);
        logger.info("screenshot is taken");
        logger.debug("navigating to send emails...");
        webDriver.findElement(emailPage.sentFolder).click();
        contextClickToRefresh();
    }

    public void chooseLastSentEmail() {
        contextClickToRefresh();
        SentEmailFolder sentEmailFolder = new SentEmailFolder(webDriver);
        logger.debug("getting last email link...");
        String email = webDriver.findElement(sentEmailFolder.lastSentEmail).getAttribute("href").toString();
        logger.info("link is received");
        logger.debug("navigating to last send email..");
        webDriver.get(email);
    }

    public String getSentEmailSubj() {
        SentEmailDetailsPage sentEmailDetailsPage = new SentEmailDetailsPage(webDriver);
        logger.debug("getting last sent email subject...");
        String subj = webDriver.findElement(sentEmailDetailsPage.sentEmailSubj).getText();
        logger.info("subject received");
        return subj;
    }

    public void navigateToInboxFolder() {
        InboxPage inboxPage = new InboxPage(webDriver);
        webDriver.findElement(inboxPage.inboxFolder).click();
        logger.info("navigating to inbox folder");
        contextClickToRefresh();

    }

    public void chooseLastRecievedEmail() {
        InboxPage inboxPage = new InboxPage(webDriver);
        String email = webDriver.findElement(inboxPage.lastSentEmail).getAttribute("href").toString();
        webDriver.get(email);

    }

    public String getLastRecievedEmailubj() {
        logger.debug("Choosing last received item subject");
        String subj = webDriver.findElement(By.className("b-letter__head__subj__text")).getText();
        logger.info("subject received");
        return subj;
    }

    public void saveToDrafts() throws InterruptedException {
        try {
            EmailPage emailPage = new EmailPage(webDriver);
            logger.warn("saving email to drafts");
            webDriver.findElement(emailPage.saveDraftBttn).click();
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
            logger.info("email is saved");
        } catch (TimeoutException e) {
            logger.error("Email isn't saved!");

        }
    }

    public void navigateToDraftsFolder() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        webDriver.findElement(draftsPage.draftsFolderBttn).click();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        logger.debug("Redirect to Drafts page");

    }

    public String getDraftEmailsubj() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        logger.debug("getting last draft email subject...");
        String subj = webDriver.findElement(draftsPage.lastCreatedEmailItem).getAttribute("data-subject").toString();
        logger.info("subject is received");
        return subj;
    }

    public void deleteLastCreatedEmail() throws InterruptedException {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        logger.debug("check last draft email...");
        webDriver.findElement(draftsPage.chkBox).click();
        logger.warn("deleting email");
        webDriver.findElement(draftsPage.lastCreatedEmailItem).sendKeys(Keys.DELETE);
        contextClickToRefresh();
        logger.debug("checking email as unread...");
        checkEmailsAsUnread();
    }

    public void contextClickToRefresh() {
        Actions actions = new Actions(webDriver);
        actions.contextClick().sendKeys(Keys.chord(Keys.CONTROL, "r")).build().perform();
        logger.info("page is refreshed");
    }

    public void checkEmailsAsUnread() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        logger.debug("selecting all email...");
        webDriver.findElement(draftsPage.lastCreatedEmailItem).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        logger.debug("opening dropdown...");
        WebElement dropDown = webDriver.findElement(draftsPage.dropDown);
        Actions actions = new Actions(webDriver);
        logger.debug("choosing option from dropdown...");
        actions.moveToElement(dropDown).click().sendKeys(dropDown, Keys.DOWN).sendKeys(dropDown, Keys.DOWN).sendKeys(dropDown, Keys.ENTER);
        actions.build().perform();
        logger.info("emails are checked");
    }

    public void cleanUp(WebDriver webDriver) {
        logger.warn("closing browser");
        webDriver.close();
        logger.info("browser's closed");
    }

}
