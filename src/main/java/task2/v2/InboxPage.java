package task2.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class InboxPage extends AbstractPage {
    protected final By createEmailBttn = By.cssSelector("span.b-toolbar__btn__text.b-toolbar__btn__text_pad");
    protected final By lastSentEmail = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/message/')]");
    protected final By inboxFolder = By.xpath("//a[contains(@href, '/messages/inbox/')]");

    public InboxPage(WebDriver webDriver) {
        super(webDriver);
    }



    @Override
    void openPage() {

    }
}
