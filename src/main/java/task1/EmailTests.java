package task1;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class EmailTests extends BaseTest {
    private WebDriver webDriver;
    BaseTest baseTest = new BaseTest();
    String text = Util.getRandomString(5);
    static final String USERNAME = "maven.test";
    static final String PASSWORD = "Qwerty123";
    String to = "maven.test@mail.ru";
    String suject = "Test email";
    String messageBody = "Hello Maven!";

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\TA\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }


    @BeforeTest
    public void login() {
        webDriver.get("https://mail.ru");
        webDriver.findElement(loginField).sendKeys(USERNAME);
        webDriver.findElement(passwordField).sendKeys(PASSWORD);
        webDriver.findElement(submitButton).click();
    }

    public void create(String text1) throws InterruptedException {
        webDriver.findElement(createEmailBttn).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(toField).sendKeys(to);
        webDriver.findElement(subjectField).sendKeys(suject + text1);
        webDriver.switchTo().frame(webDriver.findElement(bodyFrame));
        webDriver.findElement(bodyField).sendKeys(messageBody);
        webDriver.switchTo().defaultContent();
    }

    @Test
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
    }
}
