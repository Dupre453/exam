package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import static browser.Config.BROWSER_TYPE;
import static browser.Config.WAIT;

/**
 * Класс Browser предназначен для упрощения инициализации WebDriver в тестах.
 * В зависимости от конфигурации может открывать разные браузеры (Chrome или Firefox).
 */
public class Browser {
    // Переменная driver хранит текущий экземпляр WebDriver.
    public static WebDriver driver;

    /**
     * Метод для создания и инициализации WebDriver.
     * Он устанавливает необходимые настройки для выбранного браузера (Chrome или Firefox),
     * устанавливает максимальный размер окна и настраивает неявное ожидание.
     *
     * @return - возвращает экземпляр WebDriver, настроенный в соответствии с конфигурацией.
     */
    public static WebDriver createDriver(){
        // В зависимости от значения переменной BROWSER_TYPE выбирается браузер для тестов.
        switch (BROWSER_TYPE){
            case "chrome":
                // Настройки для браузера Chrome.
                ChromeOptions options = new ChromeOptions();
                // Устанавливает стратегию загрузки страниц, "eager" означает, что тест начнется после
                // загрузки DOM, не дожидаясь полной загрузки всех ресурсов (например, изображений).
                options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                // Инициализация драйвера для Chrome с указанными опциями.
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                // Настройки для браузера Firefox.
                FirefoxOptions fOptions = new FirefoxOptions();
                // Устанавливает аналогичную стратегию загрузки страниц для Firefox.
                fOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                // Инициализация драйвера для Firefox с указанными опциями.
                driver = new FirefoxDriver(fOptions);
                break;
            default:
                // Сообщение об ошибке, если BROWSER_TYPE не соответствует ни Chrome, ни Firefox.
                System.out.println("Некорректное имя браузера: " + BROWSER_TYPE);
        }

        // Устанавливает окно браузера на полный экран.
        driver.manage().window().maximize();
        // Устанавливает неявное ожидание для поиска элементов на странице (в секундах).
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));

        // Возвращает инициализированный WebDriver.
        return driver;
    }
}
