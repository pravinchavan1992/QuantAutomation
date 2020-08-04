package Pages;

import Base.BasePage;
import Utility.Utils;
import org.openqa.selenium.By;

public class loginPage extends BasePage {

    By LoginButton = By.cssSelector("li>button.vue-ui-button");
    By heading = By.cssSelector("h3.signup-heading");
    By uNameField = By.cssSelector("input[name=\'email\']");
    By passField = By.cssSelector("input[name=\'password\']");
    By loginButton = By.cssSelector("button[type='submit']");

    By profileLogo =  By.cssSelector("div.profile-pic-initials");
    By logoutLink = By.cssSelector("div.profile-pic-initials+div ul li.logout>a");
    public loginPage() {

    }

    public void navigateToLoginScreen(){
        Utils.click(LoginButton,"LoginButton");
        Utils.waitForElement(heading);
    }

    public Dashboard logInToApplication(String Uname, String Pass){
        Utils.setText(uNameField,Uname,"UserNameField");
        Utils.setText(passField,Pass,"PasswordField");
        Utils.click(loginButton,"LoginButton");
        return new Dashboard();
    }
    public void LogOut(){
        Utils.getElement(profileLogo).click();
        Utils.getElement(logoutLink).click();
    }
}
