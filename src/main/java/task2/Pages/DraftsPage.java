package task2.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DraftsPage extends AbstractPage {

    public static final String BASE_URL = "https://e.mail.ru/messages/drafts/";

    public DraftsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(xpath = "//a[starts-with(@href, 'https://e.mail.ru/compose/')]")
    private WebElement lastCreatedEmail;

    public EmailDetailsPage chooseLastEmail() {
        String email = lastCreatedEmail.getAttribute("href").toString();
        webDriver.get(email);
        return PageFactory.initElements(webDriver, EmailDetailsPage.class);
    }

    @Override
    public void openPage() {
        webDriver.get(BASE_URL);

    }
}
