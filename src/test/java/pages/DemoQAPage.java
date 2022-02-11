package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class DemoQAPage {
	
	public DemoQAPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	// Demo Radio page elements 
	@FindBy (xpath = "//div[contains(text(), 'Do you like')]")
	public WebElement doYouLikeText;
	
	@FindBy (xpath = "//label[@for='yesRadio']")
	public WebElement yesRadioLabel;
	
	@FindBy (xpath = "//label[@for='impressiveRadio']")
	public WebElement impressiveRadioLabel;
	
	
	public WebElement noRadio; // if there is an id that is meaning, we can just use the id directly

	@FindBy (css = ".mt-3")
	public WebElement radioSelectText;
	
	
	// Demo Alerts page elements
	@FindBy (id = "alertButton")
	public WebElement Alert1;
	
	public WebElement timerAlertButton;
	
	@FindBy (id = "confirmButton")
	public WebElement confirmOrDismissAlert;
	
	
}