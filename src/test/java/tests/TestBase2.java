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

import data.LoadProperties;

public class TestBase2 {
	
	//Browser stack Configuration
	public static final String USERNAME = LoadProperties.browserStackData.getProperty("username");
	public static final String ACCESS_KEY = LoadProperties.browserStackData.getProperty("accessKey");
	public static final String browserStackURL = "https://" + USERNAME + ":" + ACCESS_KEY 
			+ LoadProperties.browserStackData.getProperty("seleniumURL");
	

	public static String BaseURL = "http://demo.nopcommerce.com";

	protected ThreadLocal<RemoteWebDriver> driver = null;

	@BeforeClass
	@Parameters(value = {"browser","os","os_version"} )
	public void setUp(@Optional("chrome") String browser, String os, String osVersion) throws MalformedURLException
	{
		driver = new ThreadLocal<>();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", browser);
		caps.setCapability("os", os);
		caps.setCapability("os_version", osVersion);
		
		//Selenium Grid Local
		//driver.set(new RemoteWebDriver(new URL("http://192.168.1.24:4444/wd/hub"), caps));
		
		//Run on BrowserStack
		driver.set(new RemoteWebDriver(new URL(browserStackURL), caps));
		
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