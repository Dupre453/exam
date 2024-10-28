package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //public static final By HEADER_XPATH = By.xpath("//nav[@class='navbar navbar-expand-lg navbar-light bg-light']");
    public static final By NAME_PAGE = By.xpath("//h3");
    public static final String BUTTON_XPATH = "//*[@type='button' and text()='%s']";

    public void openUrl(String url){
        driver.get(url);
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", 500);
    }

    public WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element); // Скролл к элементу
        return element;
    }

    public void waitElementIsVisible(By locator, int second){
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(ExpectedConditions.visibilityOf(findElement(locator)));
    }

    public void waitElementIsDisplay(By locator, int second){
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(d-> isElementDisplay(locator));
    }

    public void waitForElementsEnabled(By locator, int second){
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(d-> isElementEnable(locator));
    }

    public void click(By locator){
        findElement(locator).click();
    }

    public void clear(By locator){
        findElement(locator).clear();
    }

    public String getText(By locator){
        return findElement(locator).getText();
    }

    public void sendKeys(By locator, String text){
        findElement(locator).sendKeys(text);
    }

    public boolean isElementEnable(By locator) {
        return findElement(locator).isEnabled();
    }

    public boolean isElementDisplay(By locator) {
        try{
            return findElement(locator).isDisplayed();
        }
        catch (Exception ex){
            return false;
        }
    }

    // Метод для выброса ошибки
    public void failure() {
        throw new AssertionError();
    }

    // Получение имени страницы
    public String getPageName() {
        return getText(NAME_PAGE);
    }

    // Клик по кнопке на странице
    public void buttonClick(String buttonName) {
        click(By.xpath(String.format(BUTTON_XPATH, buttonName)));
    }

    public void clickButton(By locator) {
        click(locator);
    }

    // Получение значения атрибута элемента
    public String getAttributeValue(By locator, String attribute) {
        return findElement(locator).getCssValue(attribute);
    }


}

