package assignment3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2PriceDrag extends Task1RoomBooking{
		
//	Scenario 2:
//
//		- Move Nightly price to $0 to $200
//		- Verify first hotel price is less then 200
		@Test
    		
   	public void Pricechecking(){
   		
  	        WebElement source = webDriverFluentWait(By.xpath(".//*[@id='filter-price']/div[2]/div/div[2]/div[3]"));
    	    Actions action = new Actions(getDriver());
    	    action.dragAndDropBy(source,-92,0).release().build().perform();
    	 
    	    
    	  //   String price = webDriverFluentWait(By.xpath(".//*[@id='listings']/ol/li[1]/article/div/div[3]/div[1]/a/span/ins")).getText(); 	    
    	     
    	     String price = webDriverFluentWait(By.xpath(".//*[@id='listings']/ol/li[1]/article/div/div[3]/div[3]/a/preceding::div[2]/following::ins[1]")).getText();
    	     String Newprice = null;
    	     if (price != null && price.length() > 1) {
    	         Newprice = price.substring(1, price.length());
    	     }

    	    
    	     int result = Integer.parseInt(Newprice);
    	     System.out.println(result);
    	     
    	     
    	     
    	     
    	      if(result <= 200)
    	    	  System.out.println("Hotel price is less than 200$");
    	      else
    	      {
    	    	  System.out.println("The prices are greater than 200");
    	      }
    		}

    	}
