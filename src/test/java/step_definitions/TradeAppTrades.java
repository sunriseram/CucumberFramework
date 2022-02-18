package step_definitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TradeAppTradesPage;
import utilities.BrowserUtils;
import utilities.DBUtils;
import utilities.Driver;
import utilities.PropertiesReader;

public class TradeAppTrades {
	
	TradeAppTradesPage tradePage = new TradeAppTradesPage();
	BrowserUtils utils = new BrowserUtils();
	DBUtils dbUtils = new DBUtils();

	String stockSymbol;
	String stockEntryPrice;
	List<String> addTradeData;

	@Given("I am on the Trade App log in page")
	public void i_am_on_the_trade_app_log_in_page() {
		Driver.getDriver().get(PropertiesReader.getProperty("TradeAppUrl"));
		Assert.assertTrue(tradePage.pleaseSignInText.isDisplayed());
	}

	@When("I enter username {string} password {string}")
	public void i_enter_username_password(String username, String password) {
		tradePage.username.sendKeys(username);
		tradePage.password.sendKeys(password);
	}

	@When("I click on Trade login button")
	public void i_click_on_trade_login_button() {
		tradePage.signInBtn.click();
	}

	@Then("I should be on Trade homepage")
	public void i_should_be_on_trade_homepage() {
		Assert.assertTrue(tradePage.tradeIconImage.isDisplayed());
		Assert.assertTrue(tradePage.addTradeBtn.isDisplayed());
	}

	@When("I click on Add Trade button")
	public void i_click_on_add_trade_button() {
		tradePage.addTradeBtn.click();
	}

	@Then("I should be on Save Trade page")
	public void i_should_be_on_save_trade_page() {
		Assert.assertTrue(tradePage.saveTradeText.isDisplayed());
	}

	@When("I select {string} and I enter symbol {string} entryDate {string} entryPrice {string} exitDate {string} exitPrice {string}")
	public void i_select_and_i_enter_symbol_entry_date_entry_price_exit_date_exit_price(String buyOrsell, String symbol,
			String entryDate, String entryPrice, String exitDate, String exitPrice) {
		stockSymbol = symbol;
		stockEntryPrice = entryPrice;
		// select from the dropdown
		utils.selectByVisibleText(tradePage.buyOrSellDropdown, buyOrsell);

		tradePage.stockSymbol.sendKeys(symbol);
		tradePage.openDate.sendKeys(entryDate);
		tradePage.stockEntryPrice.sendKeys(entryPrice);
		tradePage.closeDate.sendKeys(exitDate);
		tradePage.stockExitPrice.sendKeys(exitPrice);
	}

	@When("I click Save button")
	public void i_click_save_button() {
		tradePage.saveBtn.click();
	}

	@Then("The trade is displayed on the trade table")
	public void the_trade_is_displayed_on_the_trade_table() {
		Assert.assertTrue(tradePage.addTradeBtn.isDisplayed());

		for (WebElement singleSymbol : tradePage.stockSymbols) {
			if (singleSymbol.getText().equals(stockSymbol)) {
				Assert.assertEquals(singleSymbol.getText(), stockSymbol);
			}
		}

	}

	@Then("The data is deleted on the Database")
	public void the_data_is_deleted_on_the_database() {
		String sql = "DELETE FROM records WHERE symbol=" + "'" + stockSymbol + "'";
		dbUtils.deleteRecord(sql);
	}

	// addTrade scenario #End

	// data table scenario #Starts

	@When("I enter the following data")
	public void i_enter_the_following_data(DataTable dataTable) {
		addTradeData = dataTable.asList();
		
		// |Buy to Open|VHDA|04/04/2021|10.0|01/11/2022|18|

		stockSymbol = addTradeData.get(1);
		stockEntryPrice = addTradeData.get(3);
		// select from the dropdown
		utils.selectByVisibleText(tradePage.buyOrSellDropdown, addTradeData.get(0));

		tradePage.stockSymbol.sendKeys(addTradeData.get(1));
		tradePage.openDate.sendKeys(addTradeData.get(2));
		tradePage.stockEntryPrice.sendKeys(addTradeData.get(3));
		tradePage.closeDate.sendKeys(addTradeData.get(4));
		tradePage.stockExitPrice.sendKeys(addTradeData.get(5));

	}

	// UI data validation againt database #Start
	@Then("The trade data resides in data base correctly")
	public void the_trade_data_resides_in_data_base_correctly() {
		// followings are the UI inputs
		//|Buy to Open|VHDA|04/04/2021|10.0|01/11/2022|18.0|
		String buyorsell = addTradeData.get(0);
		String symbol = addTradeData.get(1);
		String entryPrice = addTradeData.get(3);
		String exitPrice = addTradeData.get(5);

		// followings are the database data
		String selectQuery = "SELECT long_short, symbol, entry_price, exit_price FROM records WHERE symbol=" + "'"
				+ stockSymbol + "'";
		List<String> dataFromDB = dbUtils.selectARecord(selectQuery);

		String buyOrSellDB = dataFromDB.get(0);
		System.out.println("buy or sell: " + buyOrSellDB);
		String symbolDB = dataFromDB.get(1);
		System.out.println("symbol: : " + symbolDB);
		String entrPriceDB = dataFromDB.get(2);
		System.out.println("entry price: " + entrPriceDB);
		String exitPriceDB = dataFromDB.get(3);
		System.out.println("exit price: " + exitPriceDB);

		Assert.assertTrue(buyOrSellDB.equals("1"));
		Assert.assertEquals(symbol, symbolDB);
		Assert.assertEquals(entryPrice, entrPriceDB);
		Assert.assertEquals(exitPrice, exitPriceDB);

	}

	// Insert DB scenario #Starts
	@Given("I executed Insert query with the following data to Database")
	public void i_executed_insert_query_with_the_following_data_to_database(DataTable dataTable) {
		addTradeData = dataTable.asList();

		stockSymbol = addTradeData.get(3).substring(1, addTradeData.get(3).length()-1);

		String id = addTradeData.get(0);
		String user_id = addTradeData.get(1);
		String long_short = addTradeData.get(2);
		String symbol = addTradeData.get(3);
		String opendate = addTradeData.get(4);
		String entryPrice = addTradeData.get(5);
		String closedate = addTradeData.get(6);
		String exitPrice = addTradeData.get(7);
		String gain = addTradeData.get(8);

		String insertQuery = "INSERT INTO records VALUES(" + id + "," + user_id + "," + long_short + "," + symbol + ","
				+ opendate + "," + entryPrice + "," + closedate + "," + exitPrice + "," + gain + ")";

		System.out.println(insertQuery);
		dbUtils.insertRecord(insertQuery);
	}

	@When("I search the {string}")
	public void i_search_the(String tradeSymbol) {
		stockSymbol = tradeSymbol;
		tradePage.searchBox.sendKeys(tradeSymbol);
	}

	@When("I click the Search button")
	public void i_click_the_search_button() {
		tradePage.searchBtn.click();
	}

	@Then("The trade input displayed on the trade table")
	public void the_trade_input_displayed_on_the_trade_table() {
		Assert.assertTrue(tradePage.addTradeBtn.isDisplayed());
		Assert.assertEquals(stockSymbol, tradePage.stockSymbols.get(0).getText());
	}

}