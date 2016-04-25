import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class SignInPage extends BasePage {

    private By loginField = findByCss("html /deep/ #user_login");
    private By passwordField = findByCss("html /deep/ #user_password");
    private By loginButton = findByCss("html /deep/ #signin_submit");

    public void visit(){
        open("http://admin.quickblox.com/signin");
    }


    public MainPage login(String login, String password){
        type(loginField, login);
        type(passwordField, password);
        click(loginButton);
        pause(1000);
        return new MainPage(driver);
    }


    public SignInPage(WebDriver driver){
        super(driver);
    }

}
