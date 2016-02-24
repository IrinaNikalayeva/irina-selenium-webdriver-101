package TestsUsingPatterns;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import task2.v2.Steps.Steps;
import task2.v2.Utils.RandomGenerator;

import java.net.MalformedURLException;

public abstract class BaseTestConf {
    Steps steps = new Steps();
    protected WebDriver webDriver;
    static final String USERNAME = "maven.test";
    static final String PASSWORD = "Qwerty123";
    String to = "maven.test@mail.ru";
    String subject = "Test email";
    String messageBody = "Hello Maven!";
    String text = RandomGenerator.getRandomString(5);

    @BeforeTest(alwaysRun = true)
    public abstract void setUp() throws MalformedURLException;

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        steps = new Steps();
        steps.cleanUp();
    }
}
