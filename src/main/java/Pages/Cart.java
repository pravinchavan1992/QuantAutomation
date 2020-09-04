package Pages;

import Base.BasePage;
import Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class Cart extends BasePage {
    public By Cources  =  By.cssSelector("div.cart-items-section>div>div>div.cart-item h5");
    public By CartCount =   By.cssSelector("li.item.cart-unit.d-cart-unit>a>span");
    private List<WebElement> ListOfCartItems;

    public By GetAmmount(String type){return By.xpath("//div[text()='Base Amount']/following-sibling::div");}
    public By AmmountPayable = By.cssSelector("div.cart-summary-item.amt-payable-wrap  h5>span");
    public By cartItems =   By.xpath("//a[text()='View Details']");
    private List<WebElement> ListOfCourses;
    public By removeItems =   By.xpath("//a[text()='Remove']");
    private List<WebElement> removeitemes;
    public By PopUpText =   By.cssSelector("div.toasted-container.top-center>div");
    public By undo  = By.cssSelector("div.toasted-container.top-center>div>a");
    public By ApplyCouponeButton =  By.xpath("//span[text()='Apply Coupon']");
    public By ApplyButton =  By.xpath("//span[text()='Apply']");
    public By inputBox  =   By.cssSelector("div.coupon-form__unit>input");
    public By AlertText =   By.cssSelector("div.alert-danger span");
    public By closeAlertButton = By.cssSelector("h5.cart__session-message+div>button");
    public By CloseButton = By.cssSelector("button.close");
    public Cart() {
    }
    public List<WebElement> getCourses(){
        ListOfCourses  =  driver.findElements(Cources);
        return  ListOfCourses.stream().filter(x->!(x.getText().isEmpty())).collect(Collectors.toList());
    }
    public void clickOnviewDetails(int i){
        ListOfCartItems  =  driver.findElements(cartItems);
        ListOfCartItems.get(i).click();
    }
    public void clickOnRemoveDetails(int i){
        removeitemes  =  driver.findElements(removeItems);
        removeitemes.get(i).click();
    }
    public void enterCoupon(String text) throws InterruptedException {
        Thread.sleep(2000);
        Utils.getElement(ApplyCouponeButton).click();
        Utils.getElement(ApplyButton);
        Utils.getElement(inputBox).sendKeys(text);
        Utils.getElement(ApplyButton).click();

    }
}
