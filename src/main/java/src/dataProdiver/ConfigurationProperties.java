package src.dataProdiver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {

    private static final String propertyFilePath = "src/main/resources/configuration.properties";

    private static String errorMessage(String propertyName) {
        return propertyName + " was not specified in the Configuration.properties file.";
    }

    private static Properties readConfigFile() {
        Properties properties;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
        return properties;
    }

    public static String readProperty(String property) {
        String prop = readConfigFile().getProperty(property);

        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException(errorMessage(property));
        }
    }

    public static String getRemoteUrl() {
        return readProperty("remoteDriverUrl");
    }

    public static String getTestUrl() {
        return readProperty("testPageUrl");
    }

    public static EnvironmentType getEnvironment() {
        String env = readConfigFile().getProperty("environment");
        if (env.equalsIgnoreCase("local"))
            return EnvironmentType.LOCAL;
        else if (env.equalsIgnoreCase("remote"))
            return EnvironmentType.REMOTE;
        else
            throw new RuntimeException(errorMessage("environment"));
    }

    public static DriverType getDriverType() {
        String driverType = readConfigFile().getProperty("driverType");
        if (driverType.equalsIgnoreCase("firefox"))
            return DriverType.FIREFOX;
        else if (driverType.equalsIgnoreCase("chrome"))
            return DriverType.CHROME;
        else
            throw new RuntimeException(errorMessage("driverType"));
    }
}
