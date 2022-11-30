package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class topic_26_FluentWait{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	long alltime = 15; //second
	long pollingtime = 100; // milisecond
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Fluent() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
			findElement("//div[@id='start']/button").click();
			
			Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");

	}

	@Test
	public void TC_02_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdountTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		fluentElement = new FluentWait<WebElement>(countdountTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(alltime))
		.pollingEvery(Duration.ofMillis(pollingtime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}
	
	public WebElement findElement(String xpathLocator) {
		//Dùng FluentWait
				fluentDriver = new FluentWait<WebDriver>(driver);
				// set tổng thời gian và tần số
				fluentDriver.withTimeout(Duration.ofSeconds(alltime))
				
				.pollingEvery(Duration.ofMillis(pollingtime))
				.ignoring(NoSuchElementException.class);
				
				// Apply điều kiện
				return fluentDriver.until(new Function<WebDriver, WebElement>() {

					//@Override
					public WebElement apply(WebDriver driver) {
						return driver.findElement(By.xpath(xpathLocator));
					}
				});
        
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
	
