package Config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
public class AppData {
    private String appURL;
    private String uname;
    private String pass;
    private String browser;
    private String chrome;
    private int implicitTimeOut;
    private int pageTimeout;
}
