package task2.v2;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

    public static final String BASE_URL = "https://mail.ru";

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    protected final By loginField = By.id("mailbox__login");
    protected final By passwordField = By.id("mailbox__password");
    protected final By submitButton = By.id("mailbox__auth__button");


    @Override
    void openPage() {
        webDriver.get(BASE_URL);
    }
}
