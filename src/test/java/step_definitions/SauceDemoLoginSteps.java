package step_definitions;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SauceDemoLoginPage;
import utilities.Driver;
import utilities.PropertiesReader;

public class SauceDemoLoginSteps {
	
	SauceDemoLoginPage page = new SauceDemoLoginPage();
	
	// valid test #Starts
	@Given("I am on the SauceDemo login page")
	public void i_am_on_the_sauce_demo_login_page() {
	   Driver.getDriver().get(PropertiesReader.getProperty("sauceUrl"));
	}
	@When("I enter valid username {string} password {string}")
	public void i_enter_valid_username_password(String username, String password) {
		page.username.sendKeys(username);
		page.password.sendKeys(password);
	}
	@When("I click on the login button")
	public void i_click_on_the_login_button() {
	    page.loginButton.click();
	}
	@Then("I should be directed to inventory page")
	public void i_should_be_directed_to_inventory_page() {
	    Assert.assertTrue(page.inventoryPageProductText.isDisplayed());
	}
	@Then("There should be {int} items in the page")
	public void there_should_be_items_in_the_page(Integer number) {
	    Assert.assertTrue(number == page.inventoryItems.size());
		Assert.assertSame(number, page.inventoryItems.size());
	}
	
	// valid test #Ends
	
	
	
	// Invalid tests #Starts
	@When("I enter invalid username {string} password {string}")
	public void i_enter_invalid_username_password(String username, String invalidPassword) {
		page.username.sendKeys(username);
		page.password.sendKeys(invalidPassword);
	}
	@Then("I should not be logged in")
	public void i_should_not_be_logged_in() {
	    Assert.assertTrue(page.username.isDisplayed());
	}
	@Then("Error message should display {string}")
	public void error_message_should_display(String errorMessage) {
	    Assert.assertEquals(page.errormessage.getText(), errorMessage);
	    Assert.fail();
	}

	// Invalid test #Ends
}

	
