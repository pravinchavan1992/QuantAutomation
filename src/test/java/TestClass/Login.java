package TestClass;

import Base.BasePage;
import Pages.Cart;
import Pages.CourseDetails;
import Pages.Dashboard;
import Pages.loginPage;

import Utility.LoggerService;
import Utility.Utils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class Login extends BasePage {
    LoggerService logger = new LoggerService(Login.class);
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
        logger.StartTest("test001()");
        String Uname = getData("uname");
        String Pass = getData("pass");
        login.navigateToLoginScreen();
        logger.info("Navigating To Login Screen");
        login.logInToApplication(Uname,Pass)
                .SelectCources("Sentiment Analysis in Trading");
        logger.info("Navigating To Select Cources");
        String CourseTitle  = Utils.getText(courseDetails.CourseTitle);
        logger.info("Cource Title is: "+CourseTitle);
        String CourseFee  = Utils.getText(courseDetails.CorseFee);
        logger.info("Cource Fee is: "+CourseFee);
        courseDetails.EnrollCourse();
        logger.info("Enrolling Cource");
        boolean Flag = driver.getCurrentUrl().contains("cart");
        Assert.assertTrue(Flag,"User navigated to cart Page.");
        logger.info("User navigated to cart Page.");
        List<WebElement> courses = cart.getCourses();
        Assert.assertEquals(courses.size(), Integer.parseInt(Utils.getText(cart.CartCount)),"Cart Count and Count of cources is equal");
        logger.info("Cart Count is: "+courses.size());
        logger.info("Cource Names Are:");
        courses.iterator().forEachRemaining(x->System.out.println(x.getText()));
        String BaseAmmount  = Utils.getText(cart.GetAmmount("Base Amount"));
        logger.info("BaseAmmount:"+BaseAmmount);
        String AmmountPayable  =Utils.getText(cart.AmmountPayable);
        logger.info("AmmountPayable:"+AmmountPayable);
        String currentHandle = driver.getWindowHandle();
        logger.info("Navigating to View Details Page:");
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
        logger.info("Popup Text is:  "+PopupText);
        logger.info("Entering  ABC in Coupon  Field");
        cart.enterCoupon("ABC");
        String  text=Utils.getText(cart.AlertText);
        logger.info("Alert Text is: "+text);
        //Thread.sleep(1000);
        //Utils.clickjs(cart.CloseButton);
        //login.LogOut();
        logger.endTest("test001()");
    }

    @Test(enabled = false)
    public void test002() throws IOException, InterruptedException {
        logger.StartTest("test001()");
        String Uname = getData("uname");
        String Pass = getData("pass");
        login.navigateToLoginScreen();
        logger.info("Navigating To Login Screen");
        login.logInToApplication(Uname,Pass)
                .SelectCources("Sentiment Analysis in Trading");
        logger.info("Navigating To Select Cources");
        String CourseTitle  = Utils.getText(courseDetails.CourseTitle);
        logger.info("Cource Title is: "+CourseTitle);
        String CourseFee  = Utils.getText(courseDetails.CorseFee);
        logger.info("Cource Fee is: "+CourseFee);
        courseDetails.EnrollCourse();
        logger.info("Enrolling Cource");
        boolean Flag = driver.getCurrentUrl().contains("cart");
        Assert.assertTrue(Flag,"User navigated to cart Page.");
        logger.info("User navigated to cart Page.");
        List<WebElement> courses = cart.getCourses();
        Assert.assertEquals(courses.size(), Integer.parseInt(Utils.getText(cart.CartCount)),"Cart Count and Count of cources is equal");
        logger.info("Cart Count is: "+courses.size());
        logger.info("Cource Names Are:");
        courses.iterator().forEachRemaining(x->System.out.println(x.getText()));
        String BaseAmmount  = Utils.getText(cart.GetAmmount("Base Amount"));
        logger.info("BaseAmmount:"+BaseAmmount);
        String AmmountPayable  =Utils.getText(cart.AmmountPayable);
        logger.info("AmmountPayable:"+AmmountPayable);
        String currentHandle = driver.getWindowHandle();
        logger.info("Navigating to View Details Page:");
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
        logger.info("Popup Text is:  "+PopupText);
        logger.info("Entering  ABC in Coupon  Field");
        cart.enterCoupon("ABC");
        String  text=Utils.getText(cart.AlertText);
        logger.info("Alert Text is: "+text);
        //Thread.sleep(1000);
        //Utils.clickjs(cart.CloseButton);
        //login.LogOut();
        logger.endTest("test001()");
    }
}
