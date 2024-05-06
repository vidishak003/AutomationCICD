package SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent{
	
	WebDriver driver;
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css="a.action__submit")
	WebElement placeOrder;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String CountryName)
	{
		Actions a= new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitAppear(results);
		selectCountry.click();

	}
	
	public ConfirmationPage SubmitOrder()
	{
		placeOrder.click();
		return new ConfirmationPage(driver);
	}


}
