package com.ferit.dominikmajdandzic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

	@Test(priority = 1)
	public void positiveLoginTest() {
		String inputUsername = "user1";
		String inputPassword = "pass123";

//		create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize window
		driver.manage().window().maximize();

//		open test page
		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);

//		verification		
//		new url
		String expectedUrl = "http://localhost/zavrsni/index.php";
		validateUrl(driver, expectedUrl);

//		logout btn visible 
		validateLogOutButton(driver);

//		successful login message
		String expectedMessage = "You are now logged in";
		validateSuccessMessage(driver, expectedMessage);

//		username displayed
		validateUsernameDisplayed(inputUsername, driver);

//		close browser
		driver.quit();
	}

	private void validateUsernameDisplayed(String inputUsername, WebDriver driver) {
		WebElement displayedContent = driver.findElement(By.xpath("//div[@class='content']"));
		String displayedText = displayedContent.getText();
		Assert.assertTrue(displayedText.contains(inputUsername),
				"Actual displayed text does not contain expected username. \nExpected username: " + inputUsername
						+ "\nDisplayed text: " + displayedText);
	}

	private void validateSuccessMessage(WebDriver driver, String expectedMessage) {
		WebElement successMessage = driver.findElement(By.xpath("//div[@class='error success']"));

		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);
	}

	private void validateLogOutButton(WebDriver driver) {
		WebElement logOutButton = driver.findElement(By.xpath("//span[@class='logout']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out button not visible");
	}

	private void validateUrl(WebDriver driver, String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as ecpected");
	}

	private void clickLogInButton(WebDriver driver) {
		WebElement logInButton = driver.findElement(By.name("login_user"));
		logInButton.click();
	}

	private void enterPassword(String inputPassword, WebDriver driver) {
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(inputPassword);
	}

	private void enterUsername(String inputUsername, WebDriver driver) {
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(inputUsername);
	}

	@Test(priority = 3)
	public void negativeLoginTestWrongUsername() {
		String inputUsername = "wronguser123";
		String inputPassword = "pass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Wrong username/password combination";
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

	@Test(priority = 3)
	public void negativeLoginTestWrongPassword() {
		String inputUsername = "user1";
		String inputPassword = "wrongpassword123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);
		

		String expectedMessage = "Wrong username/password combination";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 3)
	public void negativeLoginTestWrongUsernameAndPassword() {
		String inputUsername = "wronguser123";
		String inputPassword = "wrongpassword123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Wrong username/password combination";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeLoginTestEmptyUsername() {
		String inputUsername = "";
		String inputPassword = "pass123";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Username is required";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeLoginTestEmptyPassword() {
		String inputUsername = "user1";
		String inputPassword = "";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Password is required";
		validateErrorMessage(driver, expectedMessage);


		driver.quit();
	}

	@Test(priority = 2)
	public void negativeLoginTestEmptyUsernameAndPassword() {
		String inputUsername = "";
		String inputPassword = "";


		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();


		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		enterUsername(inputUsername, driver);

		enterPassword(inputPassword, driver);

		clickLogInButton(driver);


		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);


		String expectedMessage = "Username is required\n" + "Password is required";
		validateErrorMessage(driver, expectedMessage);

		driver.quit();
	}

}
