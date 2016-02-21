package task2.v2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailPage extends AbstractPage {

    public final By toField = By.xpath("//textarea[2]");
    public final By subjectField = By.name("Subject");
    public final By bodyFrame = By.xpath("//iframe[starts-with(@id, 'compose_')]");
    public final By bodyField = By.id("tinymce");
    public final By sendBttn = By.xpath("//div[@data-name='send']");
    public final By sentFolder = By.xpath("//a[contains(@href, '/messages/sent/')]");
    public final By saveDraftBttn = By.xpath("//div[@data-name='saveDraft']");

    public EmailPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    void openPage() {

    }
}
