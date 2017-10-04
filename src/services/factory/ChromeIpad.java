package factory;

import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeIpad extends ChromeDesktop{
	private final static String DEVICE_NAME = "iPad";

	ChromeIpad() {
		super();
		super.setCapabilitiesForEmulator(DEVICE_NAME);
	}

	ChromeIpad(DesiredCapabilities cap) {
		super(cap);
		super.setCapabilitiesForEmulator(DEVICE_NAME);
	}
}
