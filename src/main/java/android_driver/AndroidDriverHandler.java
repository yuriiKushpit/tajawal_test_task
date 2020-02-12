package android_driver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;
import java.net.URL;

/**
 * A AndroidDriverClass.
 */
public class AndroidDriverHandler {

    private static DesiredCapabilities capabilities;
    private static AndroidDriver<MobileElement> driver;
    private static final String URL_STRING = "http://127.0.0.1:4723/wd/hub";

    private AndroidDriverHandler() {

    }

    /**
     * This method sets default capabilities from properties file
     */
    private static void setDefaultCapabilities() {
        try (InputStream input = AndroidDriverHandler.class.getClassLoader().getResourceAsStream("capabilities.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, please specify a correct file path");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            File app  = new File("src/main/resources/tajawal.apk");
            //get the property value and print it out
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("platformVersion"));
            capabilities.setCapability(MobileCapabilityType.UDID, prop.getProperty("udid"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets the single instance of AndroidDriver.
     *
     * @return single instance of AndroidDriver
     */

    public static AndroidDriver<MobileElement> getDriverInstance() throws MalformedURLException {
        if (driver == null) {
            setDefaultCapabilities();
            URL url = new URL(URL_STRING);
            driver = new AndroidDriver<>(url, capabilities);
        }

        return driver;
    }
}
