package task2.v2;


import org.openqa.selenium.By;

public class SentEmailFolder {
    protected final By lastSentEmail = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/message/')]");
}
