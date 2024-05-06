package SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
		
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> orderProducts;
		
		public Boolean verifyOrdersDisplay(String productName)
		{
			Boolean match=orderProducts.stream().anyMatch(orderProduct-> orderProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
		
		
		

}
