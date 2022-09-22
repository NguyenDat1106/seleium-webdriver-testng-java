package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, employeeID, editFirstName, editLastName;
	String immigrationNumber, comments;
	

	 
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Luis";
		lastName = "Suarez";
		editFirstName = "Mohammed";
		editLastName = "salah";
		immigrationNumber = "330174458 ";
		comments = "5 Hahman Dr\nSanta Rosa\nCA 95405 USA";
		
		
	}

	@Test
	public void TC_01_Textbox_Textarea() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php");
		
		// nhập vào user/password textbox
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		
		//click vào login button
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		sleepInSecond(5);
		
		// mở màn hình App Employee
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		
		// nhập dữ liệu vào màn hình App Employee
		driver.findElement(By.cssSelector("input#firstName")).sendKeys("firstName");
		driver.findElement(By.cssSelector("input#firstName")).sendKeys("lastName");
		
		// lưu giá trị employee ID vào biến 
		// lấy ra gái trị + gán vào biến
		// phép gán giá trị =
		employeeID = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
		
		// click save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
	
		
		// Verify the fields are disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("personal_txtEmployeeId")).isEnabled());
		
		// Verify actual value the sam expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("personal_txtEmpFirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("personal_txtEmpLastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("personal_txtEmployeeId")).getAttribute("value"), employeeID);
		
		// click to save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// Verify the fields are anabled
		Assert.assertTrue(driver.findElement(By.cssSelector("personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("personal_txtEmployeeId")).isEnabled());
		
		// edit the field: firstName/ lastName
		driver.findElement(By.cssSelector("personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("personal_txtEmpFirstName")).sendKeys("editFirstName");
		driver.findElement(By.cssSelector("personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("personal_txtEmpLastName")).sendKeys("editLastName");
		
		// click to save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// Verify the fields are disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("personal_txtEmployeeId")).isEnabled());
				
		// Verify actual value the sam expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("personal_txtEmpLastName")).getAttribute("value"), editLastName);
	    Assert.assertEquals(driver.findElement(By.cssSelector("personal_txtEmployeeId")).getAttribute("value"), employeeID);
				
	    // Open Immigration tab
	    driver.findElement(By.xpath("//a[text()='Immigration']")).click();
	    
	    // clikc to Add button
	    driver.findElement(By.cssSelector("input#btnAdd")).click();
	    
	    // Enter to Immigration number textbox and comments textarea
	    driver.findElement(By.cssSelector("input#immigration_number")).sendKeys("immigrationNumber");
	    driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("comments");
	    sleepInSecond(5);
	    
	 // click to save button
	 	driver.findElement(By.cssSelector("input#btnSave")).click();
	 	 sleepInSecond(3);
	 	 
	 	 // click to passport link
	 	 driver.findElement(By.xpath("//a[text()='Passport']")).click();
	 	  
	 	// Verify actual value the sam expected value
	 	Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")).getAttribute("value"), immigrationNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), comments);
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
	
