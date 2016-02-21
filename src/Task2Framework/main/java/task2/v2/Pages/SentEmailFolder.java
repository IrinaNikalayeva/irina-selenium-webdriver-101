package task2.v2.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentEmailFolder extends AbstractPage{
    public final By lastSentEmail = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/message/')]");

    public SentEmailFolder(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    void openPage() {

    }
}
