package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SampleDriverFactory {
	public static WebDriver driver = null;

	public SampleDriverFactory() {
		super();
	}

	public static WebDriver getDriver(DriverType type) {
		return getDriver(type,  new DesiredCapabilities());
	}

	public static WebDriver getDriver(DriverType driverType, DesiredCapabilities cap) {
		switch (driverType) {
		case CHROME:
			ChromeDesktop chrome = new ChromeDesktop(cap);
			driver = chrome.getDriver(cap);
			break;
		case FIREFOX:
			FirefoxDesktop firefox = new FirefoxDesktop(cap);
			driver = firefox.getDriver(cap);
			break;
		case CHROME_IPAD:
			ChromeIpad ipad = new ChromeIpad(cap);
			driver = ipad.getDriver();
			break;
		case CHROME_NEXUS5:
			ChromeNexus5 nexus5 = new ChromeNexus5(cap);
			driver = nexus5.getDriver();
			break;
		default:
			throw new IllegalArgumentException("DriverType " + driverType + " has not been supported.");
		}

		return driver;
	}

	public static void dispose() {
		driver.quit();
	}

}
