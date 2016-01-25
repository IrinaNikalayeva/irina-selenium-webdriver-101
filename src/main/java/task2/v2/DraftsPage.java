package task2.v2;

import org.openqa.selenium.By;


public class DraftsPage {
    protected final By sentEmailSubj = By.className("b-letter__head__subj__text");
    protected final By draftsFolderBttn = By.xpath("//a[contains(@href, '/messages/drafts/')]");
    protected final By chkBox = By.xpath("//div[@id='b-letters']/div/div[3]/div/div[2]/div/div/a/div/div");
    protected final By lastCreatedEmailItem = By.xpath("//a[starts-with(@href, 'https://e.mail.ru/compose/')]");
}
