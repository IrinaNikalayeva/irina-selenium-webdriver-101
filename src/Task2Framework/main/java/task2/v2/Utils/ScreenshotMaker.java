package task2.v2.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotMaker {
    Logger logger = LogManager.getRootLogger();

    public void makeScreenshot(WebDriver webDriver){
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("HH_mm_ssa yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            FileUtils.copyFile(scrFile, new File("./Screenshots/" + dateFormat.format(calendar.getTime()) + ".png" ));
            logger.info("screenshot is saved");
        } catch (IOException e) {
            logger.error("screenshot isn't saved!");
            e.printStackTrace();
        }

    }
}
