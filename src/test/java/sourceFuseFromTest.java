import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class sourceFuseFromTest {

    public static WebDriver driver;
    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    SoftAssert softAssert = new SoftAssert();
    HashMap<String, String> formDetails = new HashMap<String, String>();

    /****************************************  All Web Element  **********************************************/

    public final By firstName = By.xpath("//div[@id='fnameInput']//input");
    public final By lastName = By.xpath("//div[@id='lnameInput']//input");
    public final By email = By.xpath("//div[@id='emailInput']//input");
    public final By currentCompany = By.xpath("//div[@id='curCompanyInput']//input");
    public final By mobileNumber = By.xpath("//div[@id='mobInput']//input");
    public final By dateOfBirth = By.xpath("//div[@class='input-group date']//input");
    public final By positionForApply = By.xpath("//div[@id='positionInput']//input");
    public final By portfolioWebsite = By.xpath("//div[@id='portfolioInput']//input");
    public final By salaryRequirement = By.xpath("//div[@id='salaryInput']//input");
    public final By whenStart = By.xpath("//div[@id='whenStartInput']//input");
    public final By address = By.xpath("//textarea[@id='address']");
    public final By yesRelocate = By.xpath("//input[@id='yes']");
    public final By uploadResume = By.xpath("//input[@id='resume']");
    public final By submitButton = By.xpath("//button[@type='submit']");
    public final By resetButton = By.xpath("//button[@type='reset']");


    @BeforeSuite
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://sfwebhtml:t63KUfxL5vUyFLG4eqZNUcuRQ@sfwebhtml.sourcefuse.com/automation-form-demo-for-interview/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void testCase1() {
        driver.findElement(submitButton).click();

        List<WebElement> requiredFieldNames = driver.findElements(By.xpath("//div[label/span[@class='required']]"));
        System.out.println("********************************************** All Mandatory Label **************************************************");
        for (WebElement requiredFieldName : requiredFieldNames) {
            System.out.println(requiredFieldName.findElement(By.xpath("./label")).getText());
        }
    }

    @Test(priority = 2)
    public void testCase2() {
        fillForm();
        softAssert.assertEquals(formDetails.get("firstName"), driver.findElement(firstName).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("lastName"), driver.findElement(lastName).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("email"), driver.findElement(email).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("currentCompany"), driver.findElement(currentCompany).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("mobileNumber"), driver.findElement(mobileNumber).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("dateOfBirth"), driver.findElement(dateOfBirth).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("positionForApply"), driver.findElement(positionForApply).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("portfolioWebsite"), driver.findElement(portfolioWebsite).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("salaryRequirement"), driver.findElement(salaryRequirement).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("address"), driver.findElement(address).getAttribute("value"));
        softAssert.assertEquals(formDetails.get("whenStart"), driver.findElement(whenStart).getAttribute("value"));
        driver.findElement(resetButton).click();
    }

    @Test(priority = 3)
    public void testCase3() {
        fillForm();
        Assert.assertEquals(formDetails.get("firstName"), driver.findElement(firstName).getAttribute("value"));
        Assert.assertEquals(formDetails.get("lastName"), driver.findElement(lastName).getAttribute("value"));
        Assert.assertEquals(formDetails.get("email"), driver.findElement(email).getAttribute("value"));
        Assert.assertEquals(formDetails.get("currentCompany"), driver.findElement(currentCompany).getAttribute("value"));
        Assert.assertEquals(formDetails.get("mobileNumber"), driver.findElement(mobileNumber).getAttribute("value"));
        Assert.assertEquals(formDetails.get("dateOfBirth"), driver.findElement(dateOfBirth).getAttribute("value"));
        Assert.assertEquals(formDetails.get("positionForApply"), driver.findElement(positionForApply).getAttribute("value"));
        Assert.assertEquals("http://" + formDetails.get("portfolioWebsite"), driver.findElement(portfolioWebsite).getAttribute("value"));
        Assert.assertEquals(formDetails.get("salaryRequirement"), driver.findElement(salaryRequirement).getAttribute("value"));
        Assert.assertEquals(formDetails.get("address"), driver.findElement(address).getAttribute("value"));
        Assert.assertEquals(formDetails.get("whenStart"), driver.findElement(whenStart).getAttribute("value"));
        driver.findElement(resetButton).click();
    }

    @Test(priority = 4)
    public void testCase4() {
        fillForm();
        driver.findElement(submitButton).click();
    }

    @Test(priority = 5)
    public void testCase5() throws SQLException {
        connectDataBase();
        String query = "select * from userTableName where email_id='"+ formDetails.get("email") + "' and mobile_number='"+ formDetails.get("mobileNumber") +"'";
        statement = connection.createStatement();
        rs = statement.executeQuery(query);

        Assert.assertEquals(formDetails.get("firstName"), rs.getString("First_Name"));
        Assert.assertEquals(formDetails.get("lastName"), rs.getString("Last_Name"));
        Assert.assertEquals(formDetails.get("email"), rs.getString("email_id"));
        Assert.assertEquals(formDetails.get("currentCompany"), rs.getString("Current_Company"));
        Assert.assertEquals(formDetails.get("mobileNumber"), rs.getString("Mobile_Number"));
        Assert.assertEquals(formDetails.get("dateOfBirth"), rs.getString("Date_Of_Birth"));
        Assert.assertEquals(formDetails.get("positionForApply"), rs.getString("Position_For_Apply"));
        Assert.assertEquals("http://" + formDetails.get("portfolioWebsite"), rs.getString("portfolio_Website"));
        Assert.assertEquals(formDetails.get("salaryRequirement"), rs.getString("Salary_Requirement"));
        Assert.assertEquals(formDetails.get("address"), rs.getString("Address"));
        Assert.assertEquals(formDetails.get("whenStart"), rs.getString("FirstName"));
    }

    @Test(priority = 6)
    public void testCase6() throws SQLException {
        String query = "select email_sent from userTableName where email_id='"+ formDetails.get("email") + "' and mobile_number='"+ formDetails.get("mobileNumber") +"'";
        statement = connection.createStatement();
        rs = statement.executeQuery(query);

        Assert.assertEquals(formDetails.get("firstName"), rs.getString("email_sent"));
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    /************************************** Common Utils Functions **********************************************/

    public void fillForm() {
        formDetails.put("firstName", generateRandomString(5));
        formDetails.put("lastName", generateRandomString(4));
        formDetails.put("email", generateRandomString(6) + "@emailId.com");
        formDetails.put("currentCompany", generateRandomString(7));
        formDetails.put("mobileNumber", "9" + generateRandomNumber(9));
        formDetails.put("positionForApply", generateRandomString(5));
        formDetails.put("portfolioWebsite", "www." + generateRandomString(4) + ".com");
        formDetails.put("salaryRequirement", generateRandomNumber(6));
        formDetails.put("address", generateRandomString(15));
        formDetails.put("dateOfBirth", "04/04/1994");
        formDetails.put("whenStart", "15/04/2020");
        driver.findElement(firstName).sendKeys(formDetails.get("firstName"));
        driver.findElement(lastName).sendKeys(formDetails.get("lastName"));
        driver.findElement(email).sendKeys(formDetails.get("email"));
        driver.findElement(currentCompany).sendKeys(formDetails.get("currentCompany"));
        driver.findElement(mobileNumber).sendKeys(formDetails.get("mobileNumber"));
        driver.findElement(dateOfBirth).sendKeys(formDetails.get("dateOfBirth"));
        driver.findElement(positionForApply).sendKeys(formDetails.get("positionForApply"));
        driver.findElement(portfolioWebsite).sendKeys(formDetails.get("portfolioWebsite"));
        driver.findElement(salaryRequirement).sendKeys(formDetails.get("salaryRequirement"));
        driver.findElement(whenStart).sendKeys(formDetails.get("whenStart"));
        driver.findElement(address).sendKeys(formDetails.get("address"));
        driver.findElement(yesRelocate).click();
        driver.findElement(uploadResume).sendKeys(System.getProperty("user.dir") + "/src/test/java/rohit_cv.pdf");
    }

    public String generateRandomString(int count) {
        final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwzyz";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_STRING.length());
            builder.append(ALPHA_STRING.charAt(character));
        }
        return builder.toString();
    }

    public String generateRandomNumber(int count) {
        final String numbers = "123456789";
        StringBuilder build_number = new StringBuilder();
        while (count-- != 0) {
            int number = (int) (Math.random() * numbers.length());
            build_number.append(numbers.charAt(number));
        }
        return build_number.toString();
    }

    public void connectDataBase(){
        String databaseURL = "jdbc:mysql://localhost:3306/easy";
        String user = "root";
        String password = "root";
        connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
