package stepdefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;

public class Login {

	WebDriver driver;
	private LoginPage loginPage;
	private AccountPage accountPage;
	private CommonUtils commonUtils;

	@Given("User navigates to login page")
	public void user_navigates_to_login_page() {

		driver = DriverFactory.getDriver();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();

	}

	@When("^User enters valid email address (.+) into email field$")
	public void User_enters_valid_email_address_into_email_field(String emailText) {

		loginPage.enterEmailAddress(emailText);

	}

	@And("^User enters valid password (.+) into password field$")
	public void user_enters_valid_password_into_password_field(String passwordText) {

		loginPage.enterPassword(passwordText);

	}

	@And("User clicks on Login button")
	public void user_clicks_on_login_button() {

		accountPage = loginPage.clickOnLoginButton();

	}

	@Then("User should get successfully logged in")
	public void user_should_get_successfully_logged_in() {

		Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationOption());

	}

	@When("User enters invalid email address into email field")
	public void user_enters_invalid_email_address_into_email_field() {

		commonUtils = new CommonUtils();
		loginPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());

	}

	@When("User enters invalid password {string} into password field")
	public void user_enters_invalid_password_into_password_field(String invalidPasswordText) {

		loginPage.enterPassword(invalidPasswordText);

	}

	@Then("User should get a proper warning message about credentials mismatch")
	public void user_should_get_a_proper_warning_message_about_credentials_mismatch() {

		Assert.assertTrue(
				loginPage.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));

	}

	@When("User dont enter email address into email field")
	public void user_dont_enter_email_address_into_email_field() {

		loginPage.enterEmailAddress("");

	}

	@When("User dont enter password into password field")
	public void user_dont_enter_password_into_password_field() {

		loginPage.enterPassword("");

	}

	@When("User enters data from datatable")
	public void user_enters_data_from_datatable(io.cucumber.datatable.DataTable dataTable) {
		
		
		// For other transformations you can register a DataTableType.
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<datatable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(dataTable);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<dataTable.cell(0,1)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(dataTable.cell(0,1));
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<dataTable.asLists()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//it prints like [[Name, age, emailid], [John, 38, john38@gmail.com], [William, 39, William39@gmail.com], [Cathy, 40, Cathy40@gmail.com]]
		System.out.println(dataTable.asLists());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<dataTable.asMaps()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//it prints like key Value (pair) [{Name=John, age=38, emailid=john38@gmail.com}, {Name=William, age=39, emailid=William39@gmail.com}, {Name=Cathy, age=40, emailid=Cathy40@gmail.com}]
		System.out.println(dataTable.asMaps());
		System.out.println("************************List <Map<String,String>>()******************************");
		List<Map<String,String>> dataMap = dataTable.asMaps();
		System.out.println(dataMap.get(0));
		System.out.println(dataMap.get(1).get("Name"));
		System.out.println(dataMap.get(1).get("age"));
		System.out.println(dataMap.get(1).get("emailid"));
		
		System.out.println("*********************************************************");
		System.out.println("COMPARISON ******* COMPARISON");
		System.out.println("*********************************************************");
		
		List<List<String>> actuallist = new ArrayList<List<String>>();
		actuallist.add(Arrays.asList("Name","age"));
		actuallist.add(Arrays.asList("John","38"));
		actuallist.add(Arrays.asList("William","39"));
		actuallist.add(Arrays.asList("Cathy","40"));
		
		DataTable actual = DataTable.create(actuallist);
		dataTable.diff(actual);
		
	}

}
