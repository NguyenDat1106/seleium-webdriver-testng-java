package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_14_Action_Part_0{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
    Actions action;
    JavascriptExecutor jsExecutor;
	String osName = System.getProperty("os.name");
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		action = new Actions(driver) ;
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	
	public void TC_01_Hover() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
		
	WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
	action.moveToElement(ageTextbox).perform();
	sleepInSecond(5);
	Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-corner-all")).getText(), "We ask for your age only for statistical purposes.");
		
	}
	
	public void TC_02_Hover() {
	driver.get("https://www.myntra.com/");
	WebElement kidLink = driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"));
	action.moveToElement(kidLink).perform();
	sleepInSecond(3);
	
	action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']"))).perform();
	sleepInSecond(3);
	
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
	}
	
	public void TC_03_Hover() {
	driver.get("https://fptshop.com.vn/");
    action.moveToElement(driver.findElement(By.xpath("//a[@title='ĐIỆN THOẠI']"))).perform();
	sleepInSecond(3);
	
	driver.findElement(By.xpath("//a[text()='Apple (iPhone)']")).click();
	sleepInSecond(3);
	
	Assert.assertEquals(driver.getCurrentUrl(), "https://fptshop.com.vn/dien-thoai/apple-iphone");
	
		
	}
	
	public void TC_04_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//store all 12 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);
		
		// chọn từ 1 đến 11 ( click và giữ chuột tại số thứ 1)
		action.clickAndHold(allNumbers.get(0))
		
		//Hover chuột tới số 11
		.moveToElement(allNumbers.get(10))
		
		// nhả chuột trái ra
		.release()
		
		//thực thi các action trên
		.perform();
		 allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(allNumbers.size(), 9);
		
		//allNumbers.get(0).click();
	}
	
	
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//store all 12 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);
		
		// Nhấn phím control xuống 
		action.keyDown(Keys.CONTROL).perform();
		
		action.click(allNumbers.get(0))
		.click(allNumbers.get(2))
		.click(allNumbers.get(7))
		.click(allNumbers.get(10)).perform();
		
		// Nhả phím control ra
		action.keyUp(Keys.CONTROL).perform();
		
		 allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(allNumbers.size(), 4);
		
		//allNumbers.get(0).click();
	}
	
	
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickMeText = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
	// croll to element
		// true: mép trên của element vè kéo element lên trên cùng
		// false: mép dưới của elelment và kéo element xuống dưới cùng
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickMeText);
		sleepInSecond(3);
		
		action.doubleClick(doubleClickMeText).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	
	}
	
	
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		WebElement deleteBefor = driver.findElement(By.cssSelector("li.context-menu-icon-delete"));
		action.moveToElement(deleteBefor).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(deleteBefor).perform();
		sleepInSecond(3);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertFalse(deleteBefor.isDisplayed());
		
	}
	
	@Test
	public void TC_08_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		
		// verify background color
				String loginButtonBackgroundHexa = Color
						.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase();
				Assert.assertEquals(loginButtonBackgroundHexa, "#03A9F4");
		
		
		
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
	
