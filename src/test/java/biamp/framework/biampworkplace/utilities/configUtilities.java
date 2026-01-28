package biamp.framework.biampworkplace.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configUtilities {

    private static final Properties properties = new Properties();

    static {
        // Load properties from the config file if provided
        String configFilePath = System.getProperty("configFile");
        if (configFilePath != null) {
            try (FileInputStream input = new FileInputStream(configFilePath)) {
                properties.load(input);
                System.out.println("Loaded configuration from file: " + configFilePath);
            } catch (IOException e) {
                System.err.println("Failed to load configuration file: " + e.getMessage());
            }
        }
    }

    public static final String BASE_URL =
            System.getProperty("baseUrl", properties.getProperty("baseUrl", "https://test.app.com"));

    public static final String BROWSER_TYPE =
            System.getProperty("browserType", properties.getProperty("browserType", "chrome"));

    public static final boolean HEADLESS =
            Boolean.parseBoolean(System.getProperty("headless", properties.getProperty("headless", "true")));

    public static final String USERNAME =
            System.getProperty("username", properties.getProperty("username", "defaultUser"));

    public static final String PASSWORD =
            System.getProperty("password", properties.getProperty("password", "defaultPassword"));

    public static final String STORAGE_STATE_PATH =
            System.getProperty("storageStatePath", properties.getProperty("storageStatePath", "biamp/framework/biampworkplace/artifacts/auth.json"));

    public static final String SCREENSHOT_PATH =
            System.getProperty("screenshotPath", properties.getProperty("screenshotPath", "biamp/framework/biampworkplace/artifacts/"));

}

/* Important Concepts:
1. Why the static block is defined?
Ans: The block is kept static to ensure that the configuration file is loaded once when the configUtilities class is first loaded into memory. Here's why:
Class-Level Initialization: A static block is executed when the class is loaded, before any instance is created or any static method is called. This ensures the properties are loaded and ready to use as soon as the class is accessed.
Global Availability: The properties object is static, meaning it is shared across all instances of the class. The static block initializes this shared object.
Efficiency: By loading the configuration file in a static block, the file is read only once, avoiding redundant I/O operations.
Default Behavior: If no configFile is provided, the block is skipped, and the class relies on default values or system properties without causing errors.

2. Why are we using System.getProperty with a fallback to properties.getProperty?
Ans: properties.getProperty() is used to retrieve values from a Properties object, which typically loads configuration values from a file (e.g., config.properties). This allows you to define default values in a file and manage them externally.
System.getProperty() retrieves values from system properties, which are typically passed at runtime (e.g., via -D options in Maven).
Why use both?
Flexibility: System.getProperty() allows overriding values at runtime, while properties.getProperty() provides default values from a file.
Fallback Mechanism: If a value is not provided via System.getProperty(), the code falls back to properties.getProperty() to use the default or file-based value.

3. mvn test -DconfigFile=src/test/resources/config.properties -DbaseUrl=https://custom.app.com -Dheadless=false
What does this command actually do?
Ans:
-DconfigFile=src/test/resources/config.properties:
Specifies the path to the configuration file.
The configUtilities class will load properties from this file.
-DbaseUrl=https://custom.app.com:
Overrides the baseUrl property with the value https://custom.app.com.
This value takes precedence over the value in the config file and the default value.
-Dheadless=false:
Overrides the headless property with the value false.
This value also takes precedence over the value in the config file and the default value.
Final Behavior:
The configUtilities class will load the config file and use its values for all properties except baseUrl and headless, which will be taken from the -D options.

 */