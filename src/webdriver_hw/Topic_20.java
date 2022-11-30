package webdriver_hw;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20 {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		// có trên UI (bắt buộc)
		// có trên HTML (bắt buộc)
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("Automation@gmail.com");
	}
	

	
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		// không có trên UI ( bắt buộc)
		// có trên HTML 
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		// Wait cho re-enter email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
	}

	
	public void TC_03_Invisible_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		// không có trên UI (bắt buộc)
		// không có trên HTML
		
		// Wait cho Re-enter email textbox  không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");
		// có trên UI 
		// có trên HTML (bắt buộc)
		
		// Wait cho Re-enter email textbox presence trong HTML vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

	}
	
	
	public void TC_05_Presence_II(){
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		// Wait cho Re-enter email textbox  không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));

	}
	@Test
	public void TC_06_Staleness() {
		// không có trong UI (bắt buộc)
		// không trong html 
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		// phase 1: element có trong HTML
		WebElement reEnterEmailAddessTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		// thao tác vs element khác làm cho element re-enter ko còn trong DOM nữa
		// close popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		// Wait cho Re-enter email textbox không còn trong DOM trong vòng 10s
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddessTextbox));
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
