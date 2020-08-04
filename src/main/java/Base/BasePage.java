package Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BasePage {
    public static WebDriver driver;
    public static void invokeBrowser() throws IOException {
        if(getData("browser").equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+getData("chrome"));
            driver = new ChromeDriver();
        }
        else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + getData("chrome"));
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.get(getData("appURL"));
    }
    @AfterTest
    public static void killDriver(){
        driver.quit();
    }
    public static String getData(String key) throws IOException {
        return LoadData().getProperty(key);
    }
    public static Properties LoadData() throws IOException {
        InputStream inputStream;
        Properties prop = new Properties();
        Path path = Paths.get("src", "main", "java", "config", "config.properties");
        inputStream = Files.newInputStream(path);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + path + "' not found in the classpath");
        }
        return prop;
    }

}
