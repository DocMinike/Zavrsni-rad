package com.ferit.dominikmajdandzic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoggedInRiskCalculatorTests {

	@Test(priority = 1)
	public void positiveLowRiskResultCalculateAndSaveTest() {
		String inputAge = "23";
		String inputWeight = "56";

//		create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize window
		driver.manage().window().maximize();

//		open test page
		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_1"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

//		verification	
//		same url
		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

//		correct score
		String expectedScore = "-5.4";
		validateScore(driver, expectedScore);		
		
		clickSaveButton(driver);
		
		validateUrl(driver, expectedUrl);
		
		validateScoreSaved(driver);

//		close browser
		driver.quit();
	}

	private String inputUsername = "user1";
	private String inputPassword = "pass123";
	
	private void logInUser(WebDriver driver) {

		WebElement linkToLogin = driver.findElement(By.xpath("//a[@href='user_authentication/login.php']"));
		linkToLogin.click();

		String expectedUrl = "http://localhost/zavrsni/user_authentication/login.php";
		validateUrl(driver, expectedUrl);

//		enter username
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(inputUsername);

//		enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(inputPassword);

//		click login button
		WebElement logInButton = driver.findElement(By.name("login_user"));
		logInButton.click();

//		logout btn visible 
		WebElement logOutButton = driver.findElement(By.xpath("//span[@class='logout']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out button not visible");

//		successful login message
		WebElement successMessage = driver.findElement(By.xpath("//div[@class='error success']"));
		String expectedMessage = "You are now logged in";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);

		WebElement linkToCalculator = driver.findElement(By.xpath("//a[@href='test_risk/risk_calculator.php']"));
		linkToCalculator.click();

		String expectedUrl2 = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl2);

//		verify logged in on calculator
		WebElement userInfoContent = driver.findElement(By.xpath("//div[@class='header']"));
		String userInfoText = userInfoContent.getText();
		Assert.assertTrue(userInfoText.contains(inputUsername),
				"User info does not contain correct username.\nUser info " + userInfoText + "\nUsername: "
						+ inputUsername);
	}
	
	private void clickSaveButton(WebDriver driver) {
		WebElement saveResultButton = driver.findElement(By.xpath("//input[@name='save_score']"));
		saveResultButton.click();
	}

	private void validateScoreSaved(WebDriver driver) {
		WebElement score = driver.findElement(By.id("number_score"));
		String actualScore = score.getText();
		WebElement userInfoContent = driver.findElement(By.xpath("//div[@class='header']"));
		String userInfoText = "Saved score: " + userInfoContent.getText();
		Assert.assertTrue(userInfoText.contains(actualScore),
				"Actual displayed text does not contain expected score. \nExpected score: " + actualScore
						+ "\nDisplayed text: " + userInfoText);
	}

	private void enterAge(String inputAge, WebDriver driver) {
		WebElement age = driver.findElement(By.id("q1"));
		age.sendKeys(inputAge);
	}

	private void enterWeight(String inputWeight, WebDriver driver) {
		WebElement weight = driver.findElement(By.id("q6"));
		weight.sendKeys(inputWeight);
	}

	private void clickCalculateButton(WebDriver driver) {
		WebElement calculateButton = driver.findElement(By.id("scoreBtn"));
		calculateButton.click();
	}

	private void validateUrl(WebDriver driver, String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as ecpected");
	}

	private void validateScore(WebDriver driver, String expectedScore) {
		WebElement score = driver.findElement(By.id("number_score"));

		String actualScore = score.getText();
		Assert.assertEquals(actualScore, expectedScore, "Actual score is not equal to expected score.\nActual score: "
				+ actualScore + "\nExpected score: " + expectedScore);
	}

	@Test(priority = 1)
	public void positiveModerateRiskResultCalculateAndSaveTest() {
		String inputAge = "44";
		String inputWeight = "45";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		String expectedScore = "11.3";
		validateScore(driver, expectedScore);
		
		clickSaveButton(driver);
		
		validateUrl(driver, expectedUrl);
		
		validateScoreSaved(driver);

		driver.quit();
	}

	@Test(priority = 1)
	public void positiveHighRiskResultCalculateAndSaveTest() {
		String inputAge = "76";
		String inputWeight = "65";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

		WebElement race = driver.findElement(By.id("q2_2"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_1"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_2"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		String expectedScore = "21.5";
		validateScore(driver, expectedScore);
		
		
		clickSaveButton(driver);
		
		validateUrl(driver, expectedUrl);
		
		validateScoreSaved(driver);

		driver.quit();
	}

	@Test(priority = 2)
	public void negativeAllInputsEmptyCalculateAndSaveTest() {
		String inputAge = "";
		String inputWeight = "";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		questions 2-5 remain unselected

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);		
		
		validateUserNotSaved(driver, expectedUrl);
		
		driver.quit();
	}
	
	private void validateErrorMessage(WebDriver driver) {
		WebElement errorMessage = driver.findElement(By.id("error_message"));
		String expectedMessage = "Please make sure you entered all of the information correctly.";
		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);
	}

	private void validateUserNotSaved(WebDriver driver, String expectedUrl) {
		WebElement prevUserInfo = driver.findElement(By.xpath("//div[@class='header']"));
		String prevUserInfoText = prevUserInfo.getText();
		clickSaveButton(driver);
		validateUrl(driver, expectedUrl);
		WebElement userInfoContent = driver.findElement(By.xpath("//div[@class='header']"));
		String userInfoText = "Saved score: " + userInfoContent.getText();
		Assert.assertTrue(userInfoText.contains(prevUserInfoText),
				"Actual displayed text does not contain expected score. \nExpected score: " + prevUserInfoText
						+ "\nDisplayed text: " + userInfoText);
	}

	@Test(priority = 2)
	public void negativeQuestionOneEmptyCalculateAndSaveTest() {
		String inputAge = "";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2, 3, 4, 5 
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_1"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_1"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 3)
	public void negativeQuestionTwoEmptyCalculateAndSaveTest() {
		String inputAge = "44";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions  3, 4, 5 

		WebElement rheumArth = driver.findElement(By.id("q3_1"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_1"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
			
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 4)
	public void negativeQuestionThreeEmptyCalculateAndSaveTest() {
		String inputAge = "44";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2, 4, 5 
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_1"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 5)
	public void negativeQuestionFourEmptyCalculateAndSaveTest() {
		String inputAge = "44";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2, 3, 5 
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_1"));
		rheumArth.click();

		WebElement fracHist = driver.findElement(By.id("q5_1"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 6)
	public void negativeQuestionFiveEmptyCalculateAndSaveTest() {
		String inputAge = "";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2, 3, 4
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_1"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 7)
	public void negativeQuestionSixEmptyCalculateAndSaveTest() {
		String inputAge = "44";
		String inputWeight = "";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2, 3, 4, 5 
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_1"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_1"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeCharInputCalculateAndSaveTest() {
		String inputAge = "abcde";
		String inputWeight = "45";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeWeightCharInputCalculateAndSaveTest() {
		String inputAge = "45";
		String inputWeight = "abcde";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeAndWeightCharInputCalculateAndSaveTest() {
		String inputAge = "abcde";
		String inputWeight = "abcde";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);
		
		logInUser(driver);

		enterAge(inputAge, driver);


//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
				
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeNegativeIntInputCalculateAndSaveTest() {
		String inputAge = "-14";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);

		validateUserNotSaved(driver, expectedUrl);
		
		driver.quit();
	}

	@Test(priority = 8)
	public void negativeWeightNegativeIntInputCalculateAndSaveTest() {
		String inputAge = "44";
		String inputWeight = "-44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
			
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeAndWeightNegativeIntInputCalculateAndSaveTest() {
		String inputAge = "-44";
		String inputWeight = "-44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}

	@Test(priority = 9)
	public void negativeAgeFloatInputCalculateAndSaveTest() {
		String inputAge = "45.5";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/index.php";
		driver.get(url);

		logInUser(driver);

		enterAge(inputAge, driver);

//		select questions 2-5
		WebElement race = driver.findElement(By.id("q2_1"));
		race.click();

		WebElement rheumArth = driver.findElement(By.id("q3_2"));
		rheumArth.click();

		WebElement estrogen = driver.findElement(By.id("q4_1"));
		estrogen.click();

		WebElement fracHist = driver.findElement(By.id("q5_3"));
		fracHist.click();

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		validateUrl(driver, expectedUrl);

		validateErrorMessage(driver);
		
		validateUserNotSaved(driver, expectedUrl);

		driver.quit();
	}
	
	

}
