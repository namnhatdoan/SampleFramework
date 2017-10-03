package factory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Chrome {
	private static Path pathToChrome = Paths.get("webdriver", "chromedriver.exe");
	private DesiredCapabilities capabilities = DesiredCapabilities.chrome();

	Chrome() {
		this.setProperties();
	}

	Chrome(DesiredCapabilities cap) {
		this.setProperties();
		this.capabilities = cap;
	}

	private void setProperties() {
		System.setProperty("webdriver.chrome.driver", pathToChrome.toString());
	}

	public WebDriver getDriver() {
		return this.getDriver(new DesiredCapabilities());
	}

	public WebDriver getDriver(DesiredCapabilities cap) {
		this.capabilities.merge(cap);
		return new ChromeDriver(this.capabilities);
	}

	public void setCapabilitiesForEmulator(EmulatorType eType) {
		String deviceName = null;

		switch (eType) {
		case NONE:
			return;
		case IPAD:
			deviceName = "iPad";
			break;
		case NEXUS5:
			deviceName = "Nexus 5";
			break;
		default:
			throw new InvalidParameterException("EmulatorType is not supported");
		}

		Map<String, String> mobileEmulation = new HashMap<String, String>();
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		mobileEmulation.put("deviceName", deviceName);
		chromeOptions.put("mobileEmulation", mobileEmulation);

		this.capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	}
}
