package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SampleDriverFactory {
	public static WebDriver driver = null;

	public SampleDriverFactory() {
		super();
	}

	public static WebDriver getDriver(DriverType type) {
		return getDriver(type, EmulatorType.NONE, new DesiredCapabilities());
	}

	public static WebDriver getDriver(DriverType driverType, EmulatorType emulatorType) {
		return getDriver(driverType, emulatorType, new DesiredCapabilities());
	}

	public static WebDriver getDriver(DriverType driverType, EmulatorType eType, DesiredCapabilities cap) {
		switch (driverType) {
		case CHROME:
			Chrome chrome = new Chrome();
			chrome.setCapabilitiesForEmulator(eType);
			driver = chrome.getDriver(cap);
			break;
		case FIREFOX:
			Firefox firefox = new Firefox();
			driver = firefox.getDriver(cap);

		default:
			throw new IllegalArgumentException("DriverType " + driverType + " has not been supported.");
		}

		return driver;
	}

	public static void dispose() {
		driver.quit();
	}

}
