package task2.v2.Patterns.FactoryMethod;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ChromeDriverConf extends WebDriverConf {
    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString();

    @Override
    public WebDriver factoryMethod() {
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(path + "\\chromedriver.exe")).build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDriver webDriver = new ChromeDriver(service);
        return webDriver;
    }
}
