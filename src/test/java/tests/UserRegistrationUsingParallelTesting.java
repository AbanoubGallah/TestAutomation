package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationUsingParallelTesting extends TestBase2 {
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	Faker fakeData = new Faker(); 
	String firstname = fakeData.name().firstName(); 
	String lastname = fakeData.name().lastName(); 
	String email = fakeData.internet().emailAddress(); 
	String password = fakeData.number().digits(8).toString(); 

	@Test(priority=1)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(getDriver()); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(getDriver()); 
		registerObject.userRegistration(firstname,lastname,email,password);
		System.out.println("The Userr Data is : "+ firstname + " " + lastname + " " + email + " " + password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}

	@Test(priority=2)
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}

	@Test(priority=3)
	public void RegisteredUserCanLogin() throws InterruptedException 
	{
		homeObject = new HomePage(getDriver());
		Thread.sleep(1000);
		homeObject.openLoginPage();
		loginObject = new LoginPage(getDriver()); 
		Thread.sleep(1000);
		loginObject.UserLogin(email,password);
		Assert.assertTrue(registerObject.logoutLink.getText().equalsIgnoreCase("LOG OUT"));
	}
}
