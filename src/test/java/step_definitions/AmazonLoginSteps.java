package step_definitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AmazonLoginPage;
import utilities.Driver;
import utilities.PropertiesReader;

public class AmazonLoginSteps {
	
	AmazonLoginPage amazonlp = new AmazonLoginPage();

	@Given("I am on amazon home page")
	public void i_am_on_amazon_home_page() {
	    Driver.getDriver().get(PropertiesReader.getProperty("amazonUrl"));
	}
	
	@Given("The sign in button displays")
	public void the_sign_in_button_displays() {
		Assert.assertTrue(amazonlp.signInBtn.isDisplayed());
	}
	
	@When("I click on the sign in button")
	public void i_click_on_the_sign_in_button() {
		amazonlp.signInBtn.click();
	}
	
	@Then("I should be directed to log in page")
	public void i_should_be_directed_to_log_in_page() {
		Assert.assertTrue(amazonlp.emailBox.isDisplayed());
	}
	
	
	// invalid user test #STARTs
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		Driver.getDriver().get(PropertiesReader.getProperty("amazonUrl"));
		amazonlp.signInBtn.click();
	    Assert.assertTrue(amazonlp.emailBox.isDisplayed());
	}
	@When("I enter invalid email {string}")
	public void i_enter_invalid_email(String email) {
	    amazonlp.emailBox.sendKeys(email);
	}
	@When("I click the continue button")
	public void i_click_the_continue_button() {
	    amazonlp.continueBtn.click();
	}
	@Then("I should get error message says {string}")
	public void i_should_get_error_message_says(String expectedErrorMessage) {
	   Assert.assertTrue(amazonlp.errorMessage.isDisplayed());
	   String actualText = amazonlp.errorMessage.getText().trim();
	   Assert.assertEquals(actualText, expectedErrorMessage);
	}
	@Then("I should still in the log in page")
	public void i_should_still_in_the_log_in_page() {
		Assert.assertTrue(amazonlp.emailBox.isDisplayed());
	}
	
	// invalid user test #ENDs
}






