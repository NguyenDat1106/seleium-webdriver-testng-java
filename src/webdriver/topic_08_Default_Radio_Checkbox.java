package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_08_Default_Radio_Checkbox{
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	public void TC_01_Jotform() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// chon Checkbox: Cancer - Fainting Spells
		checkToCheckboxOrRadio("//input[@value='Cancer']");
		checkToCheckboxOrRadio("//input[@value='Fainting Spells']");
		
		// Verify nó được chọn thành công 
		Assert.assertTrue(isElementSelected("//input[@value='Cancer']"));
		Assert.assertTrue(isElementSelected("//input[@value='Fainting Spells']"));
		
		// chon ra Radio 5+ days và 1-2 cups/day
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		driver.findElement(By.xpath("//input[@value='1-2 cups/day']")).click();
		
		// Verify nó được chọn thành công 
		Assert.assertTrue(isElementSelected("//input[@value='5+ days']"));
		Assert.assertTrue(isElementSelected("//input[@value='1-2 cups/day']"));
		
		/* bỏ chọn thì chỉ cần click 1 lần nữa thì bỏ chọn */
		// bỏ chon Checkbox: Cancer - Fainting Spells
		uncheckToCheckboxOrRadio("//input[@value='Cancer']");
		uncheckToCheckboxOrRadio("//input[@value='Fainting Spells']");
		
		// Verify nó bỏ chọn thành công 
		Assert.assertFalse(isElementSelected("//input[@value='Cancer']"));
		Assert.assertFalse(isElementSelected("//input[@value='Fainting Spells']"));
		
		
		// chon ra Radio 5+ days và 1-2 cups/day
		checkToCheckboxOrRadio("//input[@value='5+ days']");
		checkToCheckboxOrRadio("//input[@value='1-2 cups/day']");
				
		// Verify nó được chọn thành công 
		Assert.assertTrue(isElementSelected("//input[@value='5+ days']"));
		Assert.assertTrue(isElementSelected("//input[@value='1-2 cups/day']"));	
		
	}
	

public void TC_02_Jotform_Select_all() {
	driver.get("https://automationfc.github.io/multiple-fields/");
	
	List<WebElement> allCheckboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
	
	/* DÙNG HÀM */
	// dùng vòng lập để duyệt qua click chọn
	for (WebElement checkbox : allCheckboxs) {
		checkToCheckboxOrRadio(checkbox);	
	}
	// dùng vòng lập để duyệt qua và kiểm tra
			for (WebElement checkbox : allCheckboxs) {
				Assert.assertTrue(isElementSelected(checkbox));
			}
			
	/* CODE CHAY*/
	// dùng vòng lập để duyệt qua click chọn
		for (WebElement checkbox : allCheckboxs) {
			if (!checkbox.isSelected());
			checkbox.click();	
		}
		
	// dùng vòng lập để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxs) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		
		// dùng vòng lập để duyệt qua click chọn
		for (WebElement checkbox : allCheckboxs) {
			uncheckToCheckboxOrRadio(checkbox);
			
		}
		
		// dùng vòng lập để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxs) {
			Assert.assertFalse(isElementSelected(checkbox));
		}				
}


public void TC_03_Select_all() {
	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	
	List<WebElement> allCheckboxs = driver.findElements(By.xpath("//div[@id='example']//input[@type='checkbox']"));
	// vừa chon vừa verify
	for (WebElement checkbox : allCheckboxs) {
		checkToCheckboxOrRadio(checkbox);	
	}
	
	for (WebElement checkbox : allCheckboxs) {
		uncheckToCheckboxOrRadio(checkbox);	
	}
}

@Test
public void TC_04_Default() {
	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	sleepInSecond(5);
	
	checkToCheckboxOrRadio("//label[text()='Luggage compartment cover']/preceding-sibling::input");
	Assert.assertTrue(isElementSelected("//label[text()='Luggage compartment cover']/preceding-sibling::input"));
	
	uncheckToCheckboxOrRadio("//label[text()='Luggage compartment cover']/preceding-sibling::input");
	Assert.assertFalse(isElementSelected("//label[text()='Luggage compartment cover']/preceding-sibling::input"));
	
}

	public void checkToCheckboxOrRadio(String xpathLacator) {
		// Kiểm tra trước đã chọn hay chưa 
		// Nếu chọn rồi thì ko cần click nữa 
		// Nếu chưa chọn thì click vào -> Được chọn
		if(!driver.findElement(By.xpath(xpathLacator)).isSelected()) {
			driver.findElement(By.xpath(xpathLacator)).click();
		}
		
	}

	public void checkToCheckboxOrRadio(WebElement element) {
		// Kiểm tra trước đã chọn hay chưa 
		// Nếu chọn rồi thì ko cần click nữa 
		// Nếu chưa chọn thì click vào -> Được chọn
		if(!element.isSelected() && element.isEnabled()) {
			System.out.println("Click to element" + element);
			element.click();
			Assert.assertTrue(isElementSelected(element));
		}
		
	}
	
	public void uncheckToCheckboxOrRadio(String xpathLacator) {
		// Kiểm tra trước đã chọn hay chưa 
		// Nếu chọn rồi thì ko cần click nữa 
		// Nếu chưa chọn thì click vào -> Được chọn
		if(driver.findElement(By.xpath(xpathLacator)).isSelected()) {
			driver.findElement(By.xpath(xpathLacator)).click();
		}
		
	}
	
	public void uncheckToCheckboxOrRadio(WebElement element) {
		// Kiểm tra trước đã chọn hay chưa 
		// Nếu chọn rồi thì ko cần click nữa 
		// Nếu chưa chọn thì click vào -> Được chọn
		if(element.isSelected() && element.isEnabled()) {
			element.click();
			Assert.assertFalse(isElementSelected(element));
		}
		
	}
	
	public boolean isElementSelected(String xpathLocator) {
	return driver.findElement(By.xpath(xpathLocator)).isSelected();
	}

	public boolean isElementSelected(WebElement element) {
		boolean  Status = element.isSelected() && element.isEnabled();
		return Status;
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
	
