package factory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDesktop {
	private static Path pathToChrome = Paths.get("webdriver", "chromedriver.exe");
	private DesiredCapabilities capabilities = DesiredCapabilities.chrome();

	ChromeDesktop() {
		this.setProperties();
	}

	ChromeDesktop(DesiredCapabilities cap) {
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

	protected void setCapabilitiesForEmulator(String deviceName) {
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		mobileEmulation.put("deviceName", deviceName);
		chromeOptions.put("mobileEmulation", mobileEmulation);

		this.capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	}
}
