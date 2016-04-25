import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class BasePage extends Wrappers {

    private By usersIcon = findByCss("html /deep/ i[class='qbico qb-qbusers']");

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UsersPage openUsersPage(){
        click(usersIcon);
        pause(500);
        return new UsersPage(driver);
    }

    protected  UsersPage loginAndOpenUsersPage(String appLogin, String appPassword, String appName) {
        SignInPage signInPage = new SignInPage(driver);

        signInPage.visit();
        MainPage mainPage = signInPage.login(appLogin, appPassword);
        ApplicationPage applicationPage = mainPage.openYourApplication(appName);
        UsersPage usersPage =  applicationPage.openUsersPage();
        return new UsersPage(driver);
    }

    protected WebDriver driver;
}
