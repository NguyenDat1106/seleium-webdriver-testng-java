package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_12_Alert{
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firefoxAuthenAutoIT = projectPath + "\\autoITScripts\\" + "authen_firefox.exe"; 
	String chromeAuthenAutoIT = projectPath + "\\autoITScripts\\" + "authen_chrome.exe";
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		// Switch to Alert (Khi alert đang xuất hiện 
		alert = driver.switchTo().alert();

		// Verify title trước khi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// accept 1 alert
		alert.accept();
		sleepInSecond(3);
		
		// verify accept alert thành công 
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
					
	}

	
	public void TC_02_Comfirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		// Switch to Alert (Khi alert đang xuất hiện 
		alert = driver.switchTo().alert();
		
		// Verify title trước khi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// accept 1 alert
		alert.dismiss(); // xong thành công là mất alert luôn
		sleepInSecond(3);
		
		// verify accept alert thành công 
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
					
	}

	
	public void TC_03_Prompt_Alert() {
		 driver.get("https://automationfc.github.io/basic-form/");
		 
		 String keyword = "SeleniumWebDriver";
			
			driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
			sleepInSecond(3);
			
			// Switch to Alert (Khi alert đang xuất hiện 
			alert = driver.switchTo().alert();
			
			// Verify title trước khi accept alert
			Assert.assertEquals(alert.getText(), "I am a JS prompt");
			
			// accept 1 alert
			alert.sendKeys(keyword); 
			sleepInSecond(3);
			
			alert.accept();
			sleepInSecond(3);
			
			// verify accept alert thành công 
			Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + keyword);
	}
	
	
	public void TC_04_Accept_Alert_Login() {
		driver.get("https://demo.guru99.com/v4");
		
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		// verify alert title
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
				
		// accert alert
		alert.accept();
		sleepInSecond(3);
		
		// verify app Url
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
		
		
	}
	
	
	public void TC_05_Authentication_Alert() {
		// pass hẳn cái user/password vào url trước khi open nó ra
		// url:http://the-internet.herokuapp.com/basic_auth
		// Pass: Username/ Password vào Url
		// http://username:password@the-internet.herokuapp.com/basic_auth
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
	Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
	}
	
	
	public void TC_06_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");
		
	String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	
		
		driver.get(getAuthenticationUrl(basicAuthenUrl, "admin", "admin"));
		
	Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
	}
	@Test
	public void TC_07_Authentication_Alert_AutoIT() throws IOException {
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxAuthenAutoIT, "admin", "admin"});
		} else {
			Runtime.getRuntime().exec(new String[] { chromeAuthenAutoIT, "admin", "admin"});
		}
		
	// bật Scripts của AutoIT trước rồi mở site chứa Authentication alert lên sau
		// thực thi 1 file exe trong code fava
		
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		
	Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getAuthenticationUrl(String basicAuthenUrl, String userName, String password) {
	String[] authenUrlArray = basicAuthenUrl.split("//");
	basicAuthenUrl = authenUrlArray[0] + "//" + userName + ":" + password + "@" + authenUrlArray[1];
	return basicAuthenUrl;
	}
	
	// Sleep cứng (Static wait)	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
	
