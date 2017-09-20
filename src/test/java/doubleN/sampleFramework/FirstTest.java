package doubleN.sampleFramework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import factory.DriverType;
import factory.SampleDriverFactory;

public class FirstTest {
	WebDriver driver = null;
	@Before public void setup() {
		driver = SampleDriverFactory.getDriver(DriverType.CHROME);
	}
	
	@After public void clean() {
		driver.quit();
	}
	
	@Test public void firstTest() {
		driver.get("http://www.google.com");
	}
}	
