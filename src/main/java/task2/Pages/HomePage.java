package task2.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends AbstractPage {
    public static final String BASE_URL = "https://mail.ru";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(xpath = "//input[@id='mailbox__login']")
    private WebElement loginField;

    @FindBy(id = "mailbox__password")
    private WebElement passwordField;

    @FindBy(id = "mailbox__auth__button")
    private WebElement loginButton;

    public void openHomePage(String BASE_URL) {
        webDriver.get(BASE_URL);
    }

    public void loginInMailBox(String username, String password) {

        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Override
    public void openPage() {
        webDriver.get(BASE_URL);

    }


}
