package task2.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailDetailsPage extends AbstractPage {

    public EmailDetailsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(xpath = "//div[@data-name='send']")
    private WebElement sendButton;

    @FindBy(css = "compose__labels__label__text js-label-text")
    private WebElement toFieldAfterInput;

    @FindBy(name = "Subject")
    private WebElement subjectField;

    @FindBy(xpath = "//iframe[starts-with(@id, 'compose_')]")
    private WebElement bodyFrame;

    public void sendEmail() {
        sendButton.click();

    }

    @Override
    void openPage() {

    }
}
