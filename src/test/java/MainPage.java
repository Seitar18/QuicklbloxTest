import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public ApplicationPage openYourApplication(String nameOfApp){
        click(findByCss("html /deep/ a[title='" + nameOfApp + "']"));
        return new ApplicationPage(driver);
    }

    public MainPage(WebDriver driver){
        super(driver);
    }
}
