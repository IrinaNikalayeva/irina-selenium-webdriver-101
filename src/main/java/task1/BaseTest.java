package task1;


import org.openqa.selenium.By;

public class BaseTest {

    protected final By loginField = By.id("mailbox__login");
    protected final By passwordField = By.id("mailbox__password");
    protected final By submitButton = By.id("mailbox__auth__button");
    protected final By createEmailBttn = By.cssSelector("span.b-toolbar__btn__text.b-toolbar__btn__text_pad");

    protected final By toField = By.xpath("//textarea[2]");
    protected final By subjectField = By.name("Subject");
    protected final By bodyFrame = By.xpath("//iframe[starts-with(@id, 'compose_')]");
    protected final By bodyField = By.id("tinymce");


    protected final By saveDraftBttn = By.xpath("//div[@data-name='saveDraft']");

    protected final By sentEmailSubj = By.className("b-letter__head__subj__text");
    protected final By draftsBttn = By.xpath("//a[contains(@href, '/messages/drafts/')]");
    protected final By lastCreatedEmailItem = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/compose/')]"); // need to get attribute href

    protected final By lastSentEmail = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/message/')]");

    protected final By chkBox = By.xpath("//div[@id='b-letters']/div/div[3]/div/div[2]/div/div/a/div/div");

    protected final By sendBttn = By.xpath("//div[@data-name='send']");

    protected final By sentFolder = By.xpath("//a[contains(@href, '/messages/sent/')]");

    protected final By inboxFolder = By.xpath("//a[contains(@href, '/messages/inbox/')]");
}
