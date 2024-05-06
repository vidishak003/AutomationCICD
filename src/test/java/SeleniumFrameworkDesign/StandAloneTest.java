package SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest{
	//just adding new comments

	//String productName="ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void standalone(HashMap<String, String> input ) throws IOException
	{
		
		//Login
		ProductCatalouge productCatalouge=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//Add product
		List<WebElement> products =productCatalouge.getProducts();  //List<WebElement> products = 
		productCatalouge.addProductToCart(input.get("product"));
		
		//Cart		
		CartPage cartPage=productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		checkoutPage checkoutPage=cartPage.goToCheckout();
		
		//CheckoutPage
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.SubmitOrder();
		
		//ordered
		String confirmMessage= confirmationPage.verifyConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods= {"standalone"})
	public void OrderHistory()
	{
		String productName="ZARA COAT 3";
		ProductCatalouge productCatalouge=landingPage.loginApplication("vidisha@gmail.com", "Vidi1234");
		OrdersPage orderPage=productCatalouge.goToOrdersPage();
		orderPage.verifyOrdersDisplay(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFrameworkDesign\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}	
		
	
	
		//dataProvider : return new Object [][] {{"vidisha@gmail.com","Vidi1234","ZARA COAT 3"},{"dipanshu@gmail.com","Dipanshu@1234","ADIDAS ORIGINAL"}};
		//hashmap
//		HashMap<String, String> map=new HashMap<String,String>();
//		map.put("email", "vidisha@gmail.com");
//		map.put("password", "Vidi1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1=new HashMap<String,String>();
//		map1.put("email", "dipanshu@gmail.com");
//		map1.put("password", "Dipanshu@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
		
	

}
