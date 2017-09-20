package factory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SampleDriverFactory {
	public static WebDriver driver = null;
	private static Path pathToChrome = Paths.get("webdriver","chromedriver.exe");
	private static Path pathToFirefox = Paths.get("webdriver","geckodriver.exe");

	public SampleDriverFactory() {
		super();
	}

	public SampleDriverFactory(DriverType type) {
		switch (type) {
		case CHROME:
			break;
		case CHROME_IPAD:
			break;
		case CHROME_NEXUS5:
			break;
		case FIREFOX:
			break;
		
		default:

		}
	}
	
	private static void setProperties() {
		System.setProperty("webdriver.chrome.driver", pathToChrome.toString());
		System.setProperty("webdriver.gecko.driver", pathToFirefox.toString());
	}
	
	public static WebDriver getDriver(DriverType type) {
		return getDriver(type, new DesiredCapabilities());
	}
	
	public static WebDriver getDriver(DriverType type, DesiredCapabilities cap) {
		SampleDriverFactory.setProperties();
		
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		DesiredCapabilities capabilities = null;
		switch (type) {
		case CHROME:
			driver = new ChromeDriver(cap);
			break;
		case CHROME_IPAD:
			// Set capabilities for IPAD
			mobileEmulation.put("deviceName", "IPAD");
			chromeOptions.put("mobileEmulation", mobileEmulation);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			
			driver = new ChromeDriver(capabilities.merge(cap));
			break;
		case CHROME_NEXUS5:
			// Set capabilities for Nexus 5
			mobileEmulation.put("deviceName", "Nexus 5");
			chromeOptions.put("mobileEmulation", mobileEmulation);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			
			driver = new ChromeDriver(capabilities.merge(cap));
			break;
		case FIREFOX:
			driver = new FirefoxDriver(cap);
			break;
		
		default:
			throw new IllegalArgumentException("DriverType " + type + " has not been supported.");
		}
		
		return driver;
	}
	
	public static void dispose() {
		driver.quit();
	}

}
