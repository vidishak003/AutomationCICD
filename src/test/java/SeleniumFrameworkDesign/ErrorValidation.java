package SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.TestComponents.Retry;


public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})//retryAnalyzer=Retry.class
	public void LoginErrorValidation()
	{
	landingPage.loginApplication("vidisha@gmail.com", "Vidi12344");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation()
	{
	String productName="ZARA COAT 3";
	ProductCatalouge productCatalouge=landingPage.loginApplication("dipanshu@gmail.com", "Dipanshu@1234");
	
	List<WebElement> products =productCatalouge.getProducts();  
	productCatalouge.addProductToCart(productName);
			
	CartPage cartPage=productCatalouge.goToCartPage();
	Boolean match = cartPage.verifyProductDisplay("ZARA COATT 33");
	Assert.assertFalse(match);
	}
}


//<dependency>
//	<groupId>Vidisha</groupId>
//	<artifactId>Selenium-java</artifactId>
//	<version>0.0.1-SNAPSHOT</version>
//</dependency>

//<properties>
//<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
//<maven.compiler.source>1.8</maven.compiler.source>
//<maven.compiler.target>1.8</maven.compiler.target>
//</properties>

