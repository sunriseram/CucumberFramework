package step_definitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DemoQAPage;
import utilities.BrowserUtils;
import utilities.Driver;

public class DemoQASteps {
	
	BrowserUtils utils = new BrowserUtils();
	DemoQAPage demoPage = new DemoQAPage();
	
	@Given("I am on the radio button webpage {string}")
	public void i_am_on_the_radio_button_webpage(String url) {
	    Driver.getDriver().get(url);
	    utils.waitUntilElementVisible(demoPage.doYouLikeText);
	    Assert.assertTrue(demoPage.doYouLikeText.isDisplayed());
	}
	@When("I select {string}")
	public void i_select(String radioOption) {
		Assert.assertFalse(demoPage.noRadio.isEnabled());
		
	    if (radioOption.equals("Yes") && demoPage.yesRadioLabel.isEnabled()) {
	    	demoPage.yesRadioLabel.click();
		}
	    
	    if (radioOption.equals("Impressive") && demoPage.impressiveRadioLabel.isEnabled()) {
	    	demoPage.impressiveRadioLabel.click();
		}
	    
	}
	@Then("A text {string} displays")
	public void a_text_displays(String textResult) {
	    utils.waitUntilElementVisible(demoPage.radioSelectText);
	    System.out.println(demoPage.radioSelectText.getText());
	    Assert.assertEquals(demoPage.radioSelectText.getText(), textResult);
	}
	
	
	// Alert 1 steps 
	@Given("I am on the Alerts page {string}")
	public void i_am_on_the_alerts_page(String url) {
	    Driver.getDriver().get(url);
	    utils.waitUntilElementClickable(demoPage.Alert1);
	    Assert.assertTrue(demoPage.Alert1.isDisplayed());
	}
	@When("I click on AlertButton element")
	public void i_click_on_alert_button_element() {
		demoPage.Alert1.click();
	}
	@Then("I am able to accept the Alert @DemoAlert1")
	public void i_am_able_to_accept_the_alert() {
		utils.alertAccept();
	    utils.waitUntilElementVisible(demoPage.Alert1);
	    utils.alertAccept(); 
	}
	
	// Alert 1 steps Ends

}