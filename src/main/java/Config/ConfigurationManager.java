package Config;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationManager {
    private static ConfigurationManager instance =  null;

    private AppSetting AppSetting;

    private ConfigurationManager()  {
        Yaml yaml = new Yaml();
        Path path = Paths.get(System.getProperty("user.dir"),"src","main","java","Config","config.yaml");
       try(InputStream in = Files.newInputStream(path)){
           AppSetting = yaml.loadAs(in, AppSetting.class);
       }
       catch (IOException io){
           throw new ExceptionInInitializerError(io);
       }
    }

    private static ConfigurationManager getInstance(){
        if(instance == null){
            synchronized(AppSetting.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public static AppSetting getAppdata(){
        return getInstance().AppSetting;
    }
}
