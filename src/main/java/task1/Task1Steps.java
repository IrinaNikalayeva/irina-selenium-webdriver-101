package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Task1Steps {
    static final String USERNAME = "maven.test";
    static final String PASSWORD = "Qwerty123";
    String to = "maven.test@mail.ru";
    String suject = "Test email";
    String messageBody = "Hello Maven!";


    static {
        //System.setProperty("webdriver.chrome.driver",
        // "D:\\TA\\Module5\\chromedriver.exe");

        System.setProperty("webdriver.chrome.driver",
                "D:\\TA\\chromedriver.exe");
    }

    WebDriver webDriver = new ChromeDriver();

    @Test(priority = 0)
    public void signIn() {
        webDriver.get("https://mail.ru");
        webDriver.findElement(By.id("mailbox__login")).sendKeys(USERNAME);
        webDriver.findElement(By.id("mailbox__password")).sendKeys(PASSWORD);
        webDriver.findElement(By.id("mailbox__auth__button")).click();
    }

    @Test(priority = 1)
    public void createAnEmail() {
        webDriver.findElement(By.cssSelector("span.b-toolbar__btn__text.b-toolbar__btn__text_pad")).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//textarea[2]")).sendKeys(to);
        webDriver.findElement(By.name("Subject")).sendKeys(suject);

        webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[starts-with(@id, 'compose_')]")));
        webDriver.findElement(By.id("tinymce")).sendKeys(messageBody);
        webDriver.switchTo().defaultContent();
    }

    @Test(priority = 2)
    public void saveDraft() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@data-name='saveDraft']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void navigateToDraftEmail() throws InterruptedException {

        webDriver.findElement(By.xpath("//a[contains(@href, '/messages/drafts/')]")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String email = (webDriver.findElement(By.xpath("//a[starts-with(@href, 'https://e.mail.ru/compose/')]")).getAttribute("href").toString());
        webDriver.get(email);
    }

    @Test(priority = 4)
    public void sendAnEmail() {
        webDriver.findElement(By.xpath("//div[@data-name='send']")).click();
    }

    @AfterSuite
    public void quitBrowser() {
        webDriver.close();
    }
}
