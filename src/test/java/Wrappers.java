import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;

import java.util.concurrent.TimeoutException;


public abstract class Wrappers {

    public abstract WebDriver getWebDriver();


    public By findByCss(String cssSelector){
        return By.cssSelector(cssSelector);
    }

    public void waitUntilElement(By locator){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void type(By field, String value){
        getWebDriver().findElement(field).sendKeys(value);
    }

    public void clearAndType(By field, String value){
        getWebDriver().findElement(field).clear();
        getWebDriver().findElement(field).sendKeys(value);
    }

    public void click(By element){
        getWebDriver().findElement(element).click();
        pause(100);
    }

    public void acceptAlert(){
        Alert alert = getWebDriver().switchTo().alert();
        alert.accept();
    }


    public String getText(By element){
        return getWebDriver().findElement(element).getText().toString();
    }

    public String getValue(By element){
        return getWebDriver().findElement(element).getAttribute("value").toString();
    }

    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public Boolean isElementExist( By element){return (getWebDriver().findElements(element).size() != 0);
    }

    public Boolean isElementPresented(By element){
        Boolean elementCondition = false;
        try{
            waitUntilElement(element);
        }
        catch (Exception e){
            return false;
        }

        if(isElementExist(element))
            elementCondition = getWebDriver().findElement(element).isDisplayed();

        return  elementCondition;
    }


    public void open(String url){
        getWebDriver().get(url);
    }

}
