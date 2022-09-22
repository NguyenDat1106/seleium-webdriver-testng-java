package webdriver_hw;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	 
	@BeforeClass
	public void beforeClass() {
		/*if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();*/
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		String formTabID = driver.getWindowHandle();
		System.out.println("Form tab ID = " +formTabID );
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		switchToWindowByID(formTabID);
		
		driver.findElement(By.name("q")).sendKeys("selenium");
		sleepInSecond(3);
		
		String googleTabID = driver.getWindowHandle();
		
		switchToWindowByID(googleTabID);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		
	}

	
	public void TC_02_Basic_Form() {
		driver.get("https://automationfc.github.io/basic-form/");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		switchToWindowByTitle("GOOGLE");
		driver.findElement(By.name("q")).sendKeys("selenium");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("0987654321");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);

		closeAllWindowWithoutParent(parentID);
		sleepInSecond(3);
	}
	
	
	
	public void switchToWindowByID(String parentID) {
	Set<String> allWindowIDs = driver.getWindowHandles();
	for (String id : allWindowIDs) {
	
	if (!id.equals(parentID)) {
		// Switch vào 
		driver.switchTo().window(id);	
		sleepInSecond(3);
		
	}
	
	}
	
}
	
	public void switchToWindowByTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
		// Switch truoc
			driver.switchTo().window(id);
			
			// lấy ra cái title của page đã switch hiên tại
			String currentPageTitle = driver.getTitle();
		if (currentPageTitle.equals(expectedPageTitle)) {
			break;
			
		}
		
		}
		
	}
	
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
    	driver.switchTo().window(parentID);		
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
