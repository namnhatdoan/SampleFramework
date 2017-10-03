package factory;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox {
	private static Path pathToFirefox = Paths.get("webdriver", "geckodriver.exe");
	private DesiredCapabilities capabilities = null;

	Firefox() {
		setProperties();
	}

	public void setProperties() {
		System.setProperty("webdriver.gecko.driver", pathToFirefox.toString());
	}

	public WebDriver getDriver() {
		return this.getDriver(new DesiredCapabilities());
	}

	public WebDriver getDriver(DesiredCapabilities cap) {
		this.capabilities.merge(cap);
		return new FirefoxDriver(this.capabilities);
	}
}
