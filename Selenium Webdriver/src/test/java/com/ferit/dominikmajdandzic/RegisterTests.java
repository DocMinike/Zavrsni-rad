package com.ferit.dominikmajdandzic;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTests {

	@Test(priority = 1)
	public void positiveRegisterTest() {

		String inputUsername = getSaltString();
		String inputEmail = inputUsername + "@testmail.com";
		String inputPassword = getSaltString();

//		create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize window
		driver.manage().window().maximize();

//		open test page
		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

//		enter password
		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);

//		verification		
//		new url
		String expectedUrl = "http://localhost/zavrsni/index.php";
		validateUrl(driver, expectedUrl);

//		logout btn visible 
		validateLogOutButton(driver);

//		successful login/registration message
		String expectedMessage = "You are now logged in";
		validateSuccessMessage(driver, expectedMessage);

//		username displayed
		validateUsernameDIsplayed(inputUsername, driver);

//		close browser
		driver.quit();
	}

//	generate random string
	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}


	
	private void validateSuccessMessage(WebDriver driver, String expectedMessage) {
		WebElement successMessage = driver.findElement(By.xpath("//div[@class='error success']"));

		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);
	}

	private void validateUsernameDIsplayed(String inputUsername, WebDriver driver) {
		WebElement displayedContent = driver.findElement(By.xpath("//div[@class='content']"));
		String displayedText = displayedContent.getText();
		Assert.assertTrue(displayedText.contains(inputUsername),
				"Actual displayed text does not contain expected username. \nExpected username: " + inputUsername
						+ "\nDisplayed text: " + displayedText);
	}

	private void validateLogOutButton(WebDriver driver) {
		WebElement logOutButton = driver.findElement(By.xpath("//span[@class='logout']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out button not visible");
	}

	private void validateUrl(WebDriver driver, String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as ecpected");
	}

	private void clickRegisterButton(WebDriver driver) {
		WebElement registerButton = driver.findElement(By.name("reg_user"));
		registerButton.click();
	}

	private WebElement enterPassword(String inputPassword, WebDriver driver) {
		WebElement password = driver.findElement(By.name("password_1"));
		WebElement confirmPassword = driver.findElement(By.name("password_2"));
		password.sendKeys(inputPassword);
		return confirmPassword;
	}

	private void enterEmail(String inputEmail, WebDriver driver) {
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys(inputEmail);
	}

	private void enterUsername(String inputUsername, WebDriver driver) {
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(inputUsername);
	}

	@Test(priority = 2)
	public void negativeRegisterTestAllEmpty() {

		String inputUsername = "";
		String inputEmail = "";
		String inputPassword = "";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Username is required\n" + "Email is required\n" + "Password is required";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	private void validateErrorMessage(WebDriver driver, String expectedMessage) {
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));

		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);
	}

	@Test(priority = 2)
	public void negativeRegisterTestUsernameEmpty() {

		String inputUsername = "";
		String inputEmail = "test@testmail.com";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Username is required";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeRegisterTestEmailEmpty() {

		String inputUsername = "testuser";
		String inputEmail = "";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Email is required";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeRegisterTestBothPasswordEmpty() {

		String inputUsername = "testuser";
		String inputEmail = "test@testmail.com";
		String inputPassword = "";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Password is required";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeRegisterTestFirstPasswordEmpty() {

		String inputUsername = "testuser";
		String inputEmail = "test@testmail.com";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);


		WebElement password = driver.findElement(By.name("password_1"));
		WebElement confirmPassword = driver.findElement(By.name("password_2"));
		password.sendKeys("");
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Password is required\n" + "The two passwords do not match";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeRegisterTestSecondPasswordEmpty() {

		String inputUsername = "testuser";
		String inputEmail = "test@testmail.com";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys("");

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "The two passwords do not match";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 3)
	public void negativeRegisterTestUserExists() {

		String inputUsername = "user1";
		String inputEmail = "test@testmail.com";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Username already exists";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 3)
	public void negativeRegisterTestEmailExists() {

		String inputUsername = "testuser";
		String inputEmail = "user1@gmail.com";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Email already exists";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 3)
	public void negativeRegisterTestUserAndEmailExists() {

		String inputUsername = "user1";
		String inputEmail = "user1@gmail.com";
		String inputPassword = "testpass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterEmail(inputEmail, driver);

		WebElement confirmPassword = enterPassword(inputPassword, driver);
		confirmPassword.sendKeys(inputPassword);

		clickRegisterButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Username already exists\n" + "Email already exists";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

}
