package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_11_Custom_Radio_Checkbox{
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}

	
	public void TC_01_() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		//Case 1
		// thẻ input bị ẩn nên không click được
		// thẻ input dùng để verify dc
		
		//Click 
		//driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"));
		//sleepInSecond(3);
		
		// Verify
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		
		// Case 2
		// ko dùng thẻ input để click - thay thế bằng 1 thẻ dang hiển thị đại diện cho checkox/ radio: span/div
		// các thẻ này lại ko verify dc
		//driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
		//sleepInSecond(3);
		
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).isSelected());
		
		// case 3
		// trong 1 dự án mà 1 element có 2 locator để define thì sinh ra nhiều code/ cần phải maintain nhiều
		//dễ gay hiểu nhầm (Confuse) cho người mới
		
		// dùng thẻ span để click
		//driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
		//sleepInSecond(3);
		
		// dùng thẻ input để verify
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		
		// Case 4: Work-around
		// thẻ input click + verify
		// hàm click() của element ko click vào element bị ẩn 
		// hàm click() của javacriptExecutor để click() ko quan tâm element bị ẩn hay ko
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("	//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
	}
	
	
	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By springRadio = By.xpath("//span[contains(text(),' Spring ')]/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(springRadio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(springRadio).isSelected());
			
	}
	
	public void TC_03_VNDirect() {
	driver.get("https://account-v2.vndirect.com.vn");
	
	By termCheckbox = By.xpath("//input[@name='acceptTerms']");
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
	
}

	@Test
	public void TC_04_Google() {
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	
	By canthoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
	
	// Verify trước khi click
	// String Text = String Text
	Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "false");
	Assert.assertFalse(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isSelected());
	// String Text = boolean
		//Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), false);
	
	// Click  vào 
	driver.findElement(canthoRadio).click();
	sleepInSecond(3);
	
	// Verify sau khi click
	Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	
	By miQuangCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");
	
	checkToCheckbox("//div[@aria-label='Mì Quảng']");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(miQuangCheckbox).getAttribute("aria-checked"), "true");
	Assert.assertFalse(driver.findElement(By.xpath("//div[@aria-label='Mì Quảng' and @aria-checked='true']")).isSelected());
	
	uncheckToCheckbox("//div[@aria-label='Mì Quảng']");
	sleepInSecond(3);
	
	Assert.assertEquals(driver.findElement(miQuangCheckbox).getAttribute("aria-checked"), "false");
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Mì Quảng' and @aria-checked='false']")).isDisplayed());
}
    public void checkToCheckbox(String xpathLacator) {
    	WebElement element = driver.findElement(By.xpath(xpathLacator));
	if (element.getAttribute("aria-checked").equals("false"));
	element.click();
	
	
}

    public void uncheckToCheckbox(String xpathLacator) {
    	WebElement element = driver.findElement(By.xpath(xpathLacator));
	if (element.getAttribute("aria-checked").equals("false"));
	element.click();
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
	
