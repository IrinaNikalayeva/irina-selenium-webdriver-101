package task2.Steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import task2.Pages.DraftsPage;
import task2.Pages.EmailDetailsPage;
import task2.Pages.HomePage;
import task2.Pages.InboxPage;

import java.util.concurrent.TimeUnit;

public class TestSteps {
    private WebDriver webDriver;

    public void initBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\TA\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void login(String username, String passwrod) {
        HomePage homePage = new HomePage(webDriver);
        homePage.openPage();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.loginInMailBox(username, passwrod);

    }

    public boolean isLoggedIn(String username) {
        InboxPage inboxPage = new InboxPage(webDriver);
        return inboxPage.getUsername().contains(username);
    }

    public void createEmail(String to, String subject, String body) {
        InboxPage inboxPage = new InboxPage(webDriver);
        inboxPage.createEmail(to, subject, body);
    }

    public boolean isEmailCreating() {
        InboxPage inboxPage = new InboxPage(webDriver);
        return inboxPage.coposeEmailCheck().contains("compose");
    }


    public void saveAsDraft() throws InterruptedException {
        InboxPage inboxPage = new InboxPage(webDriver);
        inboxPage.saveAsDraft();
    }

    public Boolean isSavedToDrafts() {
        InboxPage inboxPage = new InboxPage(webDriver);
        return inboxPage.getSaveButtonStatus().equals("disabled");
    }

    public void goToDrafts() {
        InboxPage inboxPage = new InboxPage(webDriver);
        inboxPage.openDraftsPage();
    }

    public boolean isDraftsAreOpened() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        return webDriver.getCurrentUrl().contains("drafts");
    }

    public void chooseEmail() {
        DraftsPage draftsPage = new DraftsPage(webDriver);
        draftsPage.chooseLastEmail();
    }

    public boolean isEmailChosen() {
        return webDriver.getCurrentUrl().contains("compose");
    }

    public void sendEmail() throws InterruptedException {
        EmailDetailsPage emailDetailsPage = new EmailDetailsPage(webDriver);
        emailDetailsPage.sendEmail();
        webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        Thread.sleep(2000);
    }

    public boolean isEmailSent() {
        System.out.println(webDriver.getCurrentUrl());
        System.out.println("current: " + webDriver.getCurrentUrl());
        return webDriver.getCurrentUrl().contains("send");
    }

    public void closeDriver() {
        webDriver.close();
    }
}
