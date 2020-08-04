package Utility;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils extends BasePage {
    static WebDriverWait wait;
    public static WebElement getElement(By locator){
        return waitForElement(locator);
    }
    public static void setText(By locator, String text, String fieldName){
        WebElement  element = getElement(locator);
        element.sendKeys(text);
    }
    public static void click(By locator, String text){
        WebElement element = getElement(locator);
        element.click();
    }
    public static WebElement waitForElement(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
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
