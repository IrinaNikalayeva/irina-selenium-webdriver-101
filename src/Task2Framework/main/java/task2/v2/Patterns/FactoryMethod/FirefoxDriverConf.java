package task2.v2.Patterns.FactoryMethod;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FirefoxDriverConf extends WebDriverConf{
    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString();

    @Override
    public WebDriver factoryMethod() {
        FirefoxBinary binary = new FirefoxBinary(new File(currentRelativePath+"\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver webDriver = new FirefoxDriver(binary, profile);
        return webDriver;
    }
}
