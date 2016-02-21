package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import task2.v2.Runner.TestProperties;
import task2.v2.Steps.Steps;

import java.net.MalformedURLException;

public class BaseTestConf {
    protected WebDriver webDriver;
    protected Steps steps;
    static final String USERNAME = "maven.test";
    static final String PASSWORD = "Qwerty123";
    private static final String BASE_URL = TestProperties.getInstance().getBaseUrl();

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        steps = new Steps();
        steps.remoteDriverSetUp(); // if plan to use node
        webDriver = steps.initBrowser();
        this.webDriver.get("https://mail.ru");
    }

    @BeforeTest
    public void login() {

        steps.login(USERNAME, PASSWORD);
    }

    @AfterTest
    public void cleanUp() {
        steps.cleanUp();
    }
}
