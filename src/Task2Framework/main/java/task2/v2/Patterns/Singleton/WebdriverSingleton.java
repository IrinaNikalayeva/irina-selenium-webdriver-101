package task2.v2.Patterns.Singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Iryna on 2/23/2016.
 */
public class WebdriverSingleton {
    private static WebDriver webDriver;

    private WebdriverSingleton() {}

    public static WebDriver getWebDriverInstance() {
        if (null == webDriver) {
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    public static void closeWebBrowser(){
        webDriver.quit();
        webDriver = null;
    }
}
