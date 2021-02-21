package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase2 {

	public static String BaseURL = "http://demo.nopcommerce.com";

	protected ThreadLocal<RemoteWebDriver> driver = null;

	@BeforeClass
	@Parameters(value = {"browser"})
	public void setUp(@Optional("chrome") String browser) throws MalformedURLException
	{
		driver = new ThreadLocal<>();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", browser);
		driver.set(new RemoteWebDriver(new URL("http://192.168.1.24:4444/wd/hub"), caps));
		getDriver().navigate().to(BaseURL);
	}

	public WebDriver getDriver()
	{
		return driver.get();
	}

	@AfterClass
	public void stopDriver()
	{
		getDriver().quit();
		driver.remove();
	}
}