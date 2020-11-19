package com.ferit.dominikmajdandzic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkTests {

	@Test(priority = 1)
	public void homeToLogInTest() {
//		create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize window
		driver.manage().window().maximize();

//		open test page
		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

//		click link
		WebElement link = driver.findElement(By.xpath("//a[@href='user_authentication/login.php']"));
		link.click();

//		verification		
//		same url
		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);

//		close browser
		driver.quit();
	}

	private void validateUrl(WebDriver driver, String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as ecpected");
	}

	@Test(priority = 1)
	public void homeToRegisterTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='user_authentication/register.php']"));
		link.click();

		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 1)
	public void homeToRiskCalculatorTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='test_risk/risk_calculator.php']"));
		link.click();

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 2)
	public void logInToHomeTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='../index.php']"));
		link.click();

		String expectedUrl = "http://localhost/zavrsni/index.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 2)
	public void logInToRegisterTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/user_authentication/login.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='register.php']"));
		link.click();
		
		String expectedUrl = "http://localhost/zavrsni/user_authentication/register.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 3)
	public void registerToHomeTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='../index.php']"));
		link.click();

		String expectedUrl = "http://localhost/zavrsni/index.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 3)
	public void registerToLogInTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/user_authentication/register.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='login.php']"));
		link.click();

		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 4)
	public void riskCalculatorToHomeTest() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

		WebElement link = driver.findElement(By.xpath("//a[@href='../index.php']"));
		link.click();

		String expectedUrl = "http://localhost/zavrsni/index.php";
		validateUrl(driver, expectedUrl);

		driver.quit();
	}

}
