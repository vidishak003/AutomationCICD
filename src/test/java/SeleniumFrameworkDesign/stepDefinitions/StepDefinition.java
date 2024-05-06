package SeleniumFrameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.CartPage;
import SeleniumFrameworkDesign.ConfirmationPage;
import SeleniumFrameworkDesign.LandingPage;
import SeleniumFrameworkDesign.ProductCatalouge;
import SeleniumFrameworkDesign.checkoutPage;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	
	public LandingPage landingPage;
	ProductCatalouge productCatalouge;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalouge=landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement> products =productCatalouge.getProducts();  //List<WebElement> products = 
		productCatalouge.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName)
	{
		CartPage cartPage=productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage=checkoutPage.SubmitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string)
	{
		String confirmMessage= confirmationPage.verifyConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed(String stringg)
	{
		Assert.assertEquals(stringg,landingPage.getErrorMessage());
		driver.close();
	}
}
