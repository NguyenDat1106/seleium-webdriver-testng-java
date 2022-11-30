package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_27_Page_Ready{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait  explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
		//jsExecutor = (JavascriptExecutor) driver;
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		String EmployeeName = "Peter Mac";
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());

		//Assert.assertEquals(driver.findElement(By.cssSelector("div#task-list-group-panel-container span")).getText(), "3 month(s)");
		
		//driver.findElement(By.cssSelector("a.active")).click();
		//Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys(EmployeeName);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// load lại 1 phần trang
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + EmployeeName + "']")).isDisplayed());
		



	}

	@Test
	public void TC_02_TestProject() {
		driver.get("https://blog.testproject.io/");
		System.out.println("Wait cho page ready - first time");
		//Assert.assertTrue(isPageLoadedSuccess());

		// Handle popup
		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			System.out.println("Close popup");
			driver.findElement(By.cssSelector("div.mailch-wrap")).click();
		}
		// Hover chuột vào field search
		System.out.println("Hover mouse to Search textbox");
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		System.out.println("Wait cho page ready - second time");
		Assert.assertTrue(isPageLoadedSuccess());
		
		System.out.println("Enter value to search textbox");
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.page-title>span")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement title : postTitles) {
			String postTitleText = title.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(postTitleText.contains("Selenium"));
		}


	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isPageLoadedSuccess() {
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(jLoad);
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
	
