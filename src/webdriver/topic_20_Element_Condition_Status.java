package webdriver;

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

public class topic_20_Element_Condition_Status{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		// có trên UI (Bắt buộc)
		// có trên HIML (Bắt buộc)
		
		// Wait cho email textbox hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("Automationtest@gmail.net");
	}

	
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		// Không có trên UI (bắt buộc)
		// có trong html
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
	
		// Wait cho Re-enter email textbox  không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

	}


	
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		// Không có trên UI (bắt buộc)
		// không có trong html
		
		// Wait cho Re-enter email textbox textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	
	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		// có trên UI 
		// có trên HTML (bắt buộc)
		// Wait cho Re-enter email textbox  presence trong HTML trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
	}
	
	
	public void TC_03_Presence_II() {
		// không có trong UI 
		// có trong html (bắt buộc)
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		// Wait cho Re-enter email textbox textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));

	}
	
	public void TC_04_Staleness() {
		// không có trong UI (bắt buộc)
		// không trong html 
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

		// Phase 1: element có trong HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__")); 
		
		// thao tác vs element khác làm cho element re-enter ko còn trong DOM nữa
		// close popup đi 
		driver.findElement(By.cssSelector("img._8idr")).click(); 
		
		// Wait cho Re-enter email textbox không còn trong DOM trong vòng 10s
				explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
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
//HTML Format
	//<input class='text form-control'
	// id='txtemail' name='txtname'
	// placeholder='Địa chỉ email'
	// type='email' value=''>
	
	// input[@class='text form-control']
	// input [@id='txtEmail'] -> **
	// input [@name='txtEmail'] -> **
	// input [@placeholder='Địa chỉ email']
	// input [@type='email']
	// input [@value=''] 
	
	
	// 1 - < hoặc <>
	// 2 - tên thẻ (tagname): input/himl/head/body/form/label/span/...
	// 3 - thuộc tính (Attribute name): class/id/name/placeholder/type/value
	// 4 - giá trị của thuộc tính (Attribuute value): text form-control/txtemail/...
	// 5 - > hoặc </>
	
	// tagname - attribute name - attribute value
	
	// Xpatth Format Basic
	// Absolute Xpath: /html/body/...
	// Relative Xpath: //tagname[@attribute-name='attribute-value']
	
	
	// CSS Format Basic
		// Relative Xpath:tagname[attribute-name='attribute-value']
	
