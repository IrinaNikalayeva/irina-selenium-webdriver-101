package task2.v2.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentEmailDetailsPage extends AbstractPage {
    public final By sentEmailSubj = By.className("b-letter__head__subj__text");

    public SentEmailDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    void openPage() {

    }
}
