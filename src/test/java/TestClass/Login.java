package TestClass;

import Base.BasePage;
import Pages.Cart;
import Pages.CourseDetails;
import Pages.Dashboard;
import Pages.loginPage;

import Utility.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Login extends BasePage {

    loginPage login;
    Dashboard dashboard;
    CourseDetails courseDetails;
    Cart cart;

    public Login() throws IOException {
        super();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        invokeBrowser();
        login = new loginPage();
        dashboard = new Dashboard();
        courseDetails = new CourseDetails();
        cart = new Cart();
    }
    @Test
    public void test001() throws IOException, InterruptedException {
        String Uname = getData("uname");
        String Pass = getData("pass");
        login.navigateToLoginScreen();
        login.logInToApplication(Uname,Pass)
                .SelectCources("Sentiment Analysis in Trading");
        String CourseTitle  = Utils.getText(courseDetails.CourseTitle);
        System.out.println("Cource Title is: "+CourseTitle);
        String CourseFee  = Utils.getText(courseDetails.CorseFee);
        System.out.println("Cource Fee is: "+CourseFee);
        courseDetails.EnrollCourse();
        boolean Flag = driver.getCurrentUrl().contains("cart");
        Assert.assertTrue(Flag,"User navigated to cart Page.");
        System.out.println("User navigated to cart Page.");
        List<WebElement> courses = cart.getCourses();
        Assert.assertEquals(courses.size(), Integer.parseInt(Utils.getText(cart.CartCount)),"Cart Count and Count of cources is equal");
        System.out.println("Cart Count is: "+courses.size());
        System.out.println("Cource Names Are:");
        courses.iterator().forEachRemaining(x->System.out.println(x.getText()));
        String BaseAmmount  = Utils.getText(cart.GetAmmount("Base Amount"));
        System.out.println("BaseAmmount:"+BaseAmmount);
        String AmmountPayable  =Utils.getText(cart.AmmountPayable);
        System.out.println("AmmountPayable:"+AmmountPayable);
        String currentHandle = driver.getWindowHandle();
        System.out.println("Navigating to View Details Page:");
        cart.clickOnviewDetails(1);
        Set<String> handeles = driver.getWindowHandles();
        Iterator<String> itr = handeles.iterator();
        while (itr.hasNext()){
            String hdl = itr.next();
            if(!hdl.equals(currentHandle)){
                driver.switchTo().window(hdl);
                driver.close();

            }
        }
        driver.switchTo().window(currentHandle);
        Thread.sleep(3000);
        cart.clickOnRemoveDetails(0);
        String PopupText= Utils.getElement(cart.PopUpText).getAttribute("innerText");
        System.out.println("Popup Text is:  "+PopupText);
        System.out.println("Entering  ABC in Coupon  Field");
        cart.enterCoupon("ABC");
        String  text=Utils.getText(cart.AlertText);
        System.out.println("Alert Text is: "+text);
        Thread.sleep(1000);
        Utils.clickjs(cart.CloseButton);
        login.LogOut();
    }
}
