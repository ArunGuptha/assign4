package assignment3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1RoomBooking extends WebDriverWaitFunctions {

	/*
	 * - Click on hotel deals - Enter London - Check in date as tomorrow date -
	 * Checkout date as week from tomorrow - Select Two rooms - Room 1: Check if
	 * adults default to 2, if not then select 2 - Select children as 1 - Select
	 * child 1 as 2 - Room2: Check adult as 4 - check if default child dropdown
	 * set to 0 - Click on Search - Verify header test display as
	 * "London England, United Kingdom"
	 */

	@Test
	public void RoomBooking() throws InterruptedException {

		WebElement HotelDeals = webDriverFluentWait(By.id("hdr-deals"));
		HotelDeals.click();

		WebElement location = webDriverFluentWait(By.id("qf-1q-destination"));
		location.sendKeys("London");

		List<WebElement> Suggestions = getDriver().findElements(By.xpath(".//div[@class='autosuggest-category-result']"));
		for (WebElement element : Suggestions) {
			if (element.getText().contains("London, United Kingdom")) {
				element.click();
				break;
			}
		}

//		WebElement checkin = webDriverFluentWait(By.id("qf-1q-localised-check-in"));
//		checkin.sendKeys("11/23/2016");
//		WebElement checkout = webDriverFluentWait(By.id("qf-1q-localised-check-out"));
//		checkout.sendKeys("11/30/2016");

		DateTimeFormatter format =  DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.plusDays(7);
        
        webDriverFluentWait(By.id("qf-1q-localised-check-in")).sendKeys(now.format(format));
  
        webDriverFluentWait(By.id("qf-1q-localised-check-out")).clear();
        webDriverFluentWait(By.id("qf-1q-localised-check-out")).sendKeys(then.format(format));
		
		
		
		
		Select NoOfRooms = new Select(webDriverFluentWait(By.id("qf-1q-rooms")));
		NoOfRooms.selectByVisibleText("2");

		WebElement NoOfAdults = webDriverFluentWait(By.id("qf-1q-room-0-adults"));
		Select select = new Select(NoOfAdults);
		List<WebElement> AdultsCount = select.getOptions();

		for (WebElement option : AdultsCount) {
			System.out.println(option.getText());
			if (!option.getText().equals("2")) {
				Select Adults = new Select(webDriverFluentWait(By.id("qf-1q-room-0-adults")));
				Adults.selectByVisibleText("2");

			}

		}

		Select Children = new Select(webDriverFluentWait(By.id("qf-1q-room-0-children")));
		Children.selectByVisibleText("1");

		Select Childage = new Select(webDriverFluentWait(By.id("qf-1q-room-0-child-0-age")));
		Childage.selectByVisibleText("2");

		Select Roomadults = new Select(webDriverFluentWait(By.id("qf-1q-room-1-adults")));
		Roomadults.selectByVisibleText("4");

		WebElement Room2Childs = webDriverFluentWait(By.id("qf-1q-room-1-children"));
		Select select2 = new Select(Room2Childs);
		List<WebElement> options2 = select2.getOptions();

		for (WebElement option2 : options2) {
			System.out.println(option2.getText());
			if (!option2.getText().equals("0")) {
				Select dropdownRoomchild = new Select(webDriverFluentWait(By.id("qf-1q-room-1-children")));
				dropdownRoomchild.selectByVisibleText("0");
			}

		}
		
		webDriverFluentWait(By.xpath(".//*[@class='cta cta-strong']")).click();
		String actual = webDriverFluentWait(By.xpath(".//*[@id='search']/div/div/h1")).getText();
		String expected = "London, England, United Kingdom";
		Assert.assertEquals(actual, expected);

	}

}
