package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
    * @author Osada
    * @Date
 */
public class DriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static String rootDirectory=System.getProperty("user.dir");
    private static String driverDirectory=rootDirectory+"/src/main/java/driver/drivers/";
    private static String configDirectory=rootDirectory+"/src/main/java/properties/";

    public static WebDriver getDriver(){
        if(webDriver.get() == null){
            webDriver.set((createWebDriver()));
        }
        return webDriver.get();
    }
    public static WebDriver createWebDriver(){

        WebDriver driver = null;

        switch (getBrowserType()){

            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", driverDirectory+"chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver",driverDirectory+"geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.addArguments("--remote-allow-origins=*");
                driver = new FirefoxDriver(options);
                break;
            }
        }
        driver.manage().window().maximize();
        return driver;
    }
    public static String getBrowserType() {
        String browserType = null;
        String browserTypeRemoteValue = System.getProperty("browserType");
        try{
            if(browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()){
                Properties prop = new Properties();
                FileInputStream file = new FileInputStream(configDirectory +"config.properties");
                prop.load(file);
                browserType = prop.getProperty("browser").toLowerCase().trim();
            }else{
                browserType = browserTypeRemoteValue;
            }
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
        return browserType;
    }
    public static void cleanupDriver(){
        webDriver.get().quit();
        webDriver.remove();
    }
}
