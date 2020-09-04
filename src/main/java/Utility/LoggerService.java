package Utility;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.io.File;
import java.sql.Timestamp;
import java.util.Properties;

public class LoggerService {
    private static Logger logger = null;

    public LoggerService(java.lang.Class clazz){
        String log4jConfPath = System.getProperty("user.dir")+"\\src\\main\\resources\\log4j2.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger = logger.getLogger(clazz);
        logger.setLevel(Level.INFO);

    }
    public void StartTest(String TestMethodName){
        logger.info("========================= Test Started: -> "+TestMethodName+" =========================");
    }
    public void info(String info){
        logger.info(info);
    }
    public void endTest(String TestMethodName){
        logger.info("========================= Test ended: -> "+TestMethodName+" =========================");
    }

    public static void initializeLogger(String fileName) {

        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.setStatusLevel(org.apache.logging.log4j.Level.DEBUG);
        builder.setConfigurationName("DefaultLogger");

        // create a console appender
        AppenderComponentBuilder appenderBuilder = builder.newAppender("Console", "CONSOLE").addAttribute("target",
                ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "[%t] %-5p %c %x - %m%n"));
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(org.apache.logging.log4j.Level.DEBUG);
        rootLogger.add(builder.newAppenderRef("Console"));

        builder.add(appenderBuilder);

        // create a rolling file appender
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "1KB"));
        appenderBuilder = builder.newAppender("LogToRollingFile", "RollingFile")
                .addAttribute("fileName", fileName)
                .addAttribute("filePattern", fileName+"-%d{MM-dd-yy-HH-mm-ss}.log.")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(appenderBuilder);
        rootLogger.add(builder.newAppenderRef("LogToRollingFile"));
        builder.add(rootLogger);
        Configurator.initialize(builder.build());
    }
}
