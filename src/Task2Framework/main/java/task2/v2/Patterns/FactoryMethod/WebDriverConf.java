package task2.v2.Patterns.FactoryMethod;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverConf {


    public abstract WebDriver factoryMethod();

    public abstract class WebDriverCreator {

        protected WebDriver webDriver;


           }
}
