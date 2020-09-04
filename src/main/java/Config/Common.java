package Config;
public class Common {
    public enum DriverKey{
        Chrome{
            public String toString(){return "webdriver.chrome.driver";}
        },
        FF{
            public String toString(){return "webdriver.gecko.driver";}
        }
    }
    public enum Browser{
        Chrome{
            public String toString(){return "chrome";}
        }
    }
}
