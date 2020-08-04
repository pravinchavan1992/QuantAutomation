package Pages;

import Base.BasePage;
import Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Dashboard  extends BasePage {
    By BrowseCoursesLabel = By.cssSelector("li.item.menu__link--toggle>span>a");
    By GetCource(String Cource){  return By.xpath("//a/span[text()='"+Cource+"']"); }
    public Dashboard() {
    }
    public void SelectCources(String Course){
        Utils.moveToElement(BrowseCoursesLabel);
        Utils.moveToElement(GetCource(Course));
    }
}
