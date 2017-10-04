package factory;

import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeNexus5 extends ChromeDesktop{
	private final static String DEVICE_NAME = "Nexus 5";

	ChromeNexus5() {
		super();
		super.setCapabilitiesForEmulator(DEVICE_NAME);
	}

	ChromeNexus5(DesiredCapabilities cap) {
		super(cap);
		super.setCapabilitiesForEmulator(DEVICE_NAME);
	}
}
