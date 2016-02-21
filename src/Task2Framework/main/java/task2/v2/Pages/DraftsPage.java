package task2.v2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DraftsPage extends AbstractPage {
    protected final By sentEmailSubj = By.className("b-letter__head__subj__text");
    public final By draftsFolderBttn = By.xpath("//a[contains(@href, '/messages/drafts/')]");
    public final By chkBox = By.xpath("//div[@id='b-letters']/div/div[3]/div/div[2]/div/div/a/div/div");
    public final By lastCreatedEmailItem = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/compose/')]");
    public final By dropDown = By.xpath("//div[@id='b-toolbar__right']/div[2]/div/div[2]/div[5]/div/div[2]/div/span");

    public DraftsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    void openPage() {

    }
}
