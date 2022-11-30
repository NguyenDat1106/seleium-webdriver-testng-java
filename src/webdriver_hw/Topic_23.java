package webdriver_hw;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	
	public void TC_01_Not_Enough_Time() {
	
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// Thiếu thời gian để cho element tiếp theo hoạt động
		sleepInSecond(3);

		// Get text vào verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
		
	}

	
	public void TC_02_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// đủ thời gian để cho element tiếp theo hoạt động
		sleepInSecond(5);
		
		// Get text vào verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
			
	}

	@Test
	public void TC_03_More_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// dư thời gian để cho element tiếp theo hoạt động
		sleepInSecond(10);
		
		// Get text vào verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
			
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	// Sleep cứng (Static wait)	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
	}
}
