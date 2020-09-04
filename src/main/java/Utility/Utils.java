package Utility;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils extends BasePage {
    static WebDriverWait wait;
    static LoggerService logger = new LoggerService(Utils.class);
    public static WebElement getElement(By locator){
        WebElement element = null;
        try {
            element = waitForElement(locator);
        }catch (Exception e){
            logger.info("Could not locate element:-> "+e);
        }
        return element;
    }
    public static void setText(By locator, String text, String fieldName){
        WebElement element = getElement(locator);
        if(element!=null) {
            element.sendKeys(text);
            logger.info("Entering "+text+" in "+fieldName);
        }
    }
    public static void click(By locator, String text){
        WebElement element = getElement(locator);
        element.click();
        logger.info("Clicking on  "+element);
    }
    public static WebElement waitForElement(By locator){
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static String getText(By locator){
        WebElement element = getElement(locator);
        return element.getText();
    }
    public static void moveToElement(By locator){
        WebElement element = getElement(locator);
        Actions action =  new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }
    public static void clickjs(By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",getElement(locator));
    }
}
