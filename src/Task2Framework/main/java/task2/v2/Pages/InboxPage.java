package task2.v2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class InboxPage extends AbstractPage {
    public final By createEmailBttn = By.cssSelector("span.b-toolbar__btn__text.b-toolbar__btn__text_pad");
    public final By lastSentEmail = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/message/')]");
    public final By inboxFolder = By.xpath("//a[contains(@href, '/messages/inbox/')]");

    public InboxPage(WebDriver webDriver) {
        super(webDriver);
    }



    @Override
    void openPage() {

    }
}
