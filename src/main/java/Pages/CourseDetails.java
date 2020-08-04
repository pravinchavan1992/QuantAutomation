package Pages;

import Base.BasePage;
import Utility.Utils;
import org.openqa.selenium.By;

public class CourseDetails extends BasePage {
    public By CourseTitle = By.cssSelector("div.course-detail__left-view>h1");
    public By CorseFee = By.cssSelector("span.cd__data-unit__striked+span>span");
    public By EnrollNowButton = By.cssSelector("span.default-slot");
    public By CartTitle =   By.cssSelector("h3.section-title.cart-title");
    public CourseDetails() {
    }
    public void EnrollCourse(){
        Utils.click(EnrollNowButton,"Enroll Now Button");
        Utils.waitForElement(CartTitle);
    }

}
