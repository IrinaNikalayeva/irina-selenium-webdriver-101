package task2.v2.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Highlighter {

    Logger logger = LogManager.getRootLogger();

    public void highlight(WebDriver webDriver, WebElement webElement) throws InterruptedException {
        logger.debug("getting background color...");
        webElement.getCssValue("backgroundColor");
        logger.info("background color is get");
        logger.warn("highlighting element");
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", webElement);
        logger.info("elemant is highlighted");
    }
}
