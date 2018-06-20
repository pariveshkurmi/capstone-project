
package com.capstone.project.integrationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class LoginFunctionalTest {

	static WebDriver driver;

	@BeforeClass
	public static void setup() {
		driver = new HtmlUnitDriver();
	}

	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}

	@Test
	public void loginSuccess() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:6080/capstoneproject/login");
		WebElement username = driver.findElement(By.name("username"));
		WebElement pass = driver.findElement(By.name("password"));
		WebElement button = driver.findElement(By.name("submit"));
		username.sendKeys("test");
		pass.sendKeys("test");
		button.click();
		assertTrue(driver.getPageSource().contains("Welcome test!!"));
	}

	@Test
	public void loginFail() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:6080/capstoneproject/login");
		WebElement usernameFail = driver.findElement(By.name("username"));
		WebElement passFail = driver.findElement(By.name("password"));
		WebElement buttonFail = driver.findElement(By.name("submit"));
		usernameFail.sendKeys("test");
		passFail.sendKeys("test123");
		buttonFail.click();
		assertTrue(driver.getPageSource().contains("Reason: Bad credentials"));
	}
	
	
}