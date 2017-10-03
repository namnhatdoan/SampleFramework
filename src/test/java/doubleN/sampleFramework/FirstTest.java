package doubleN.sampleFramework;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.DriverType;
import factory.EmulatorType;
import factory.SampleDriverFactory;

public class FirstTest {
	//WebDriver driver = null;
	ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	
	@Before public void setup() {
		SampleDriverFactory.getDriver(DriverType.CHROME, EmulatorType.IPAD);
		
	}
	
	@After public void clean() {
		SampleDriverFactory.dispose();
	}
	
	@Test public void firstTest() {
		SampleDriverFactory.driver.get("https://www.whatismybrowser.com/");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	
