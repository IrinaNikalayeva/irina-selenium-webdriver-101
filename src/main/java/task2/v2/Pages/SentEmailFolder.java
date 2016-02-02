package task2.v2.Pages;


import org.openqa.selenium.By;

public class SentEmailFolder {
    public final By lastSentEmail = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/message/')]");
}
