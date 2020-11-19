package com.ferit.dominikmajdandzic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoggedOutRiskCalculatorTests {

	@Test(priority = 1)
	public void positiveLowRiskResultCalculateTest() {
		String inputAge = "23";
		String inputWeight = "56";

//		create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize window
		driver.manage().window().maximize();

//		open test page
		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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
		validateUrl(driver);

//		correct score
		String expectedScore = "-5.4";
		validateScore(driver, expectedScore);

//		close browser
		driver.quit();
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

	private void validateUrl(WebDriver driver) {
		String expectedUrl = "http://localhost/zavrsni/test_risk/risk_calculator.php";
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
	public void positiveModerateRiskResultCalculateTest() {
		String inputAge = "44";
		String inputWeight = "45";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		String expectedScore = "11.3";
		validateScore(driver, expectedScore);

		driver.quit();
	}

	@Test(priority = 1)
	public void positiveHighRiskResultCalculateTest() {
		String inputAge = "76";
		String inputWeight = "65";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

		enterAge(inputAge, driver);

//		select questions 2-5
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

		validateUrl(driver);

		String expectedScore = "21.5";
		validateScore(driver, expectedScore);

		driver.quit();
	}

	@Test(priority = 2)
	public void negativeAllInputsEmptyCalculateTest() {
		String inputAge = "";
		String inputWeight = "";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

		enterAge(inputAge, driver);

//		questions 2-5 remain unselected

		enterWeight(inputWeight, driver);

		clickCalculateButton(driver);

		validateUrl(driver);

		validateErrorMessage(driver);

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

	@Test(priority = 2)
	public void negativeQuestionOneEmptyCalculateTest() {
		String inputAge = "";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 3)
	public void negativeQuestionTwoEmptyCalculateTest() {
		String inputAge = "44";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 4)
	public void negativeQuestionThreeEmptyCalculateTest() {
		String inputAge = "44";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 5)
	public void negativeQuestionFourEmptyCalculateTest() {
		String inputAge = "44";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 6)
	public void negativeQuestionFiveEmptyCalculateTest() {
		String inputAge = "";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 7)
	public void negativeQuestionSixEmptyCalculateTest() {
		String inputAge = "44";
		String inputWeight = "";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeCharInputCalculateTest() {
		String inputAge = "abcde";
		String inputWeight = "45";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeWeightCharInputCalculateTest() {
		String inputAge = "45";
		String inputWeight = "abcde";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeAndWeightCharInputCalculateTest() {
		String inputAge = "abcde";
		String inputWeight = "abcde";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeNegativeIntInputCalculateTest() {
		String inputAge = "-14";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeWeightNegativeIntInputCalculateTest() {
		String inputAge = "44";
		String inputWeight = "-44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 8)
	public void negativeAgeAndWeightNegativeIntInputCalculateTest() {
		String inputAge = "-44";
		String inputWeight = "-44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	@Test(priority = 9)
	public void negativeAgeFloatInputCalculateTest() {
		String inputAge = "45.5";
		String inputWeight = "44";

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "http://localhost/zavrsni/test_risk/risk_calculator.php";
		driver.get(url);

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

		validateUrl(driver);

		validateErrorMessage(driver);

		driver.quit();
	}

	

}
