package task2.v2;

import org.openqa.selenium.By;

public class EmailPage {

    protected final By toField = By.xpath("//textarea[2]");
    protected final By subjectField = By.name("Subject");
    protected final By bodyFrame = By.xpath("//iframe[starts-with(@id, 'compose_')]");
    protected final By bodyField = By.id("tinymce");
    protected final By sendBttn = By.xpath("//div[@data-name='send']");
    protected final By sentFolder = By.xpath("//a[contains(@href, '/messages/sent/')]");
    protected final By saveDraftBttn = By.xpath("//div[@data-name='saveDraft']");
}
