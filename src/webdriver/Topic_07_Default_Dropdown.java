package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	Random rand;
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Default_Dropdown() {
		driver.get("https://demo.nopcommerce.com");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Joe");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Biden");
		
		
		// khởi tạo select để thao tác vs Day Dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		
		// chon item có text là 13
		select.selectByVisibleText("13");
		
		// khởi tạo select để thao tác vs Month Dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		
		// khởi tạo select để thao tác vs Month Dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1946");
		
		String emailAddress = "Biden" + rand.nextInt(9999) + "@hotmail.com";
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); 
		driver.findElement(By.cssSelector("input#Company")).sendKeys("United States");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), "Joe");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), "Biden");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1946");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), "United States");
		
	}

	//@Test
	public void TC_02_Default_Dropdown() {
		driver.get("https://rode.com/en/support/where-to-buy");
		
		select = new Select(driver.findElement(By.id("country")));
		
		select.selectByValue("Vietnam");
		sleepInSecond(3);
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		List <WebElement> dealers = driver.findElements(By.cssSelector("div#map h4"));
		
	for (WebElement element : dealers) {
		System.out.println(element.getText());
		
	}
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

		
