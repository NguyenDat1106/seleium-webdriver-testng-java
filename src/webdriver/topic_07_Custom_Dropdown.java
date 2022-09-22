package webdriver;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_07_Custom_Dropdown{
	// Khai báo 
	WebDriver driver;
	WebDriverWait explicitwait;
	JavascriptExecutor jsExecutor;
	
	
	// Vừa khai báo vừa khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	// Khởi tạo driver	
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		//driver.manage().window().setSize(new org.openqa.selenium.Dimension(1366, 768));
		
		
	// Khởi tạo wait
		explicitwait = new WebDriverWait(driver, 30);
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	public void TC_01_() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		/* 
		 // 1 click vào 1 phần tử nào đó thuộc dropdown để cho nó xổ ra
		 driver.findElement(By.cssSelector("pspan#number-button")).click();
		 // 2 chờ cho tất cả các item trong dropdowm được load ra xong
		 // lưu ý: ko dùng sleep cứng được
		 // phải có 1 hàm wait nào đó để nó linh động
		 // nếu như chưa tìm thấy thì sẽ tìm lại trong khoảng time dc set
		 // nếu tìm thấy rồi thì ko cần phải chờ hết khoảng time này
		 // bắt dc 1 locator để đại diện cho tất cả các item								
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
		// 3.1 nếu item cần chọn đang hiển thị 
		// 3.2 nếu item cần chon ko hiển thị thì cần cuộn chuột xuống ( scroll down)
		// 4 kiểm tra text của item - nếu đúng vs cái mình cần thì click vào 
		// viết code để duyệt qua từng item và kiểm tra theo điều kiện
		 
		 // lưu trữ tất cả các item lại thì mưới duyệt qua dc
		List<WebElement> allItems = driver.findElements(By.cssSelector(ul#number-menu div));
		
		// duyệt qua từng item
		// vòng lập foreach
		for (WebElement item : allItems) {
		// dùng biến item để thao tác trong vòng lập for
		 
		 //lấy ra text
		String textActualItem = item.getText();
		
		// kiểm tra nếu nó bằng vs text mình mong muốn
		if (textActualItem.equals("7")) {
		// nó sẽ nhận vào 1 điều kiện là boolean (true/ false)
		// nếu như điều kiện mà đúng thì mới vào trong hàm if
		// điều kiện mà sai thì bỏ qua
		
		// thì lick vào
		item.click();
		}
		} */
		
		
		// gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 class
		/* Number Dropdown */
		selectItemCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "7");
		
		selectItemCustomDropdown("span#number-button", "ul#number-menu div", "3");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "3");
		
		/* Speed Dropdown */
		selectItemCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		
		selectItemCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		
		selectItemCustomDropdown("span#speed-button", "ul#speed-menu div", "Medium");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");
		
		selectItemCustomDropdown("span#speed-button", "ul#speed-menu div", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		
		selectItemCustomDropdown("span#speed-button", "ul#speed-menu div", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		
		/* Title Dropdown */
		selectItemCustomDropdown("span#salutation-button", " ul#salutation-menu div", "Mr.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
		
		selectItemCustomDropdown("span#salutation-button", " ul#salutation-menu div", "Mrs.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");
		
		selectItemCustomDropdown("span#salutation-button", " ul#salutation-menu div", "Dr.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
	}


	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		
		scrollToElement("img.image-background");
		sleepInSecond(3);
		
		selectItemCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "CITY L");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L");
		
		
		scrollToElement("div.div-cost-estimates");
		sleepInSecond(3);
		
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Đà Nẵng");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Đà Nẵng");
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");
	}


	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemCustomDropdown("div.dropdown", "div.menu span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectItemCustomDropdown("div.dropdown", "div.menu span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		selectItemCustomDropdown("div.dropdown", "div.menu span.text", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		
	}
	

	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemCustomDropdown("div.btn-group", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemCustomDropdown("div.btn-group", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemCustomDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
	}
	

	public void TC_05_React_Selectable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemCustomDropdown("div.dropdown", "div.menu span.text", "Afghanistan");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
		
		selectItemCustomDropdown("div.dropdown", "div.menu span.text", "Aland Islands");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Aland Islands");
		
		selectItemCustomDropdown("div.dropdown", "div.menu span.text", "Algeria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
	}
	
	@Test
	public void TC_06_React_SelectEdisable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterItemCustomDropdown("input.search", "div.menu span.text", "Afghanistan");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
		
		enterItemCustomDropdown("input.search", "div.menu span.text", "Aland Islands");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Aland Islands");
		
		enterItemCustomDropdown("input.search", "div.menu span.text", "Algeria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
		
	}
	
	// không dùng cho bất kì 1 dropdown nào cụ thể (specific)
	// dùng cho các dropdown chung/ ko cụ thể của 1 dự án 
		public void selectItemCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
			
		driver.findElement(By.cssSelector(parentLocator)).click();								
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
		String textActualItem = item.getText();
		if (textActualItem.equals(textExpectedItem)) { 
		item.click();
		
		// khi đã tìm thấy và thỏa mãn điều kiện thì ko cần duyệt tiếp
		break;
			 }
											
		   }
		}
		
		public void enterItemCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
			driver.findElement(By.cssSelector(parentLocator)).clear();
			driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);	
			sleepInSecond(1);
			
			explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
			List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
			for (WebElement item : allItems) {
			String textActualItem = item.getText();
			if (textActualItem.equals(textExpectedItem)) { 
			item.click();
			
			// khi đã tìm thấy và thỏa mãn điều kiện thì ko cần duyệt tiếp
			break;
				 }
												
			   }
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
	
