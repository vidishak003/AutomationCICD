package SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
		
		@FindBy(css=".cartSection h3")
		List<WebElement> cartProducts;
		
		@FindBy(css=".totalRow button")
		WebElement checkout;
		
		public Boolean verifyProductDisplay(String productName)
		{
			Boolean match=cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
		
		public checkoutPage goToCheckout()
		{
			checkout.click();
			return new checkoutPage(driver);
		}
		

}
