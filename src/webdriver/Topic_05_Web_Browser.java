package webdriver;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	// Khai báo
	WebDriver driver;
	WebElement element;

	// Khai báo + khởi tạo
	String projectPath = System.getProperty("user.dir");

	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		// Khởi tạo

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Browser() {
		// các hàm tương tác vs Browser sẽ thông qua biến driver

		// Đóng browser
		driver.close();// **
		driver.quit(); // **

		// tìm ra element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); // **

		// tìm ra nhiều element ( multiple )
		List<WebElement> links = driver.findElements(By.cssSelector("")); // **

		// mở ra các Url truyền vào
		driver.get("https://www.facebook.com//"); // **

		// trả vè 1 Url tại tab đang đứng
		String gamepageUrl = driver.getCurrentUrl();

		String gamepageTitle = driver.getTitle();

		// source code của page hiện tại
		String gamePageSourceCode = driver.getPageSource();

		// lấy ra một cái ID của tab/ window đang đứng/ active (Window/ tab)
		driver.getWindowHandle(); // 1 // **
		driver.getWindowHandles(); // tất cả // **

		driver.manage().getCookies(); // **

		driver.manage().logs().getAvailableLogTypes();

		driver.manage().window().fullscreen();

		driver.manage().window().maximize(); // **

		// chờ cho element được tìm thấy trong khoảng thời gian xx
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// chờ cho page load thành công xx giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		// chờ cho script được inject thành công vào Browser/ element xx giây
		// (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");

		// Alert/ Frame (Ifame)/ Window (tab)
		driver.switchTo().alert(); // **

		driver.switchTo().frame(0); // **

		driver.switchTo().window(""); // **

	}

	@Test
	public void TC_02_Element() {
		driver.get("https://www.facebook.com//");
		// các hàm tương tác vs element sẽ thông qua element cái WebElement (biến nào đó)
		// xóa dữ liệu trong 1 field dạng editable ( có thể nhập)
		// Textbox/ Text Area/ Editable Dropdown
		element.clear();

		// nhập dữ liệu vào field dạng editable
		element.sendKeys("dat@gmail.com");
		element.sendKeys(Keys.ENTER);
		
		// Click vào 1 element: Button/ Checkbox/ Radio/
		element.click();

		element.getAttribute("placeholder");
		// email address ỏ phone number

		driver.findElement(By.id("firstname")).getAttribute("Value");

		// trả về thuộc tính CSS của element
		// Font/ size/ color

		// trả về màu nền của element
		element.getCssValue("backgroud-color");

		// trả về font - size của element
		element.getCssValue("font-size");

		// Test GUI: Point/ rectangle/ Size ( Visualize Testing)
		element.getLocation();
		element.getRect();
		element.getSize();

		// chụp hình và attach vào HTML report
		element.getScreenshotAs(OutputType.FILE);

		// trả về thẻ HTML của element
		WebElement emailAddressTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailAddressTextbox = driver.findElement(By.cssSelector("input[id='email']"));
		emailAddressTextbox.getTagName();
		// 2 cách để thao tác

		// 1: khai báo biến và dùng lại
		// dùng đi dùng lại nhiều lần - ít nhất là 2 lần mới vần khai báo biến

		// khai báo biến cùng với kiểu dữ liệu của hàm

		emailAddressTextbox.clear();
		emailAddressTextbox.sendKeys("dat@gmail.com");

		// 2: dùng trực tiếp - dùng có 1 lần
		driver.findElement(By.id("email")).sendKeys("dat@gmail.com");
		
		// trả về text của 1 element ( Link/ Header/ Message lỗi/ Message sucess )
		element.getText();
		
		// Trả về giá trị đúng hoặc sai của 1 element
		element.isDisplayed();
		
		// Trả về giá trị đúng hoặc sau của 1 element có thể thao tác được hay ko
		// có bị disable ko
		element.isEnabled();
		// Enabled: true
		// bị disable: false
		
		// Trả về giá trị đứng hoặc sau cảu 1 element đã được chọn rồi hay chưa
		// Checkbox/Radio
		element.isSelected();
		// Chọn ra: true
		// Chưa chọn: false
		
		
		// Dropdown: có 1 thư viện riêng để xử lí (select)
		
		// chỉ làm việc dc với from ( register/ login/ search/...)
		// Sumit = Enter ở 1 field nào đó
		// Sumit vào 1 field nào đó trong from
		element.submit();
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}