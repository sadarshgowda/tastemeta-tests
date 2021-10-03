package com.test;

import com.util.base.BaseTestCase;
import com.util.base.ConfigReader;
import com.util.base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateAccount extends BaseTestCase {
    private String baseUrl = ConfigReader.getProperty(Constants.BASE_URL);
    private String loginPageUrl = ConfigReader.getProperty(Constants.LOGIN_PAGE_URL);
    private String webDriver = ConfigReader.getProperty(Constants.WEB_DRIVER);
    private String createacountUrl = ConfigReader.getProperty(Constants.CREATE_ACCOUNT_URL);

    @Test(priority = 0, description = "execution of test case to create account")
    public void createAccount() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", webDriver);

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(baseUrl);

        //finding locator to click on login button
        WebElement login = driver.findElement(By.cssSelector("p.login.cursor"));
        login.click();

        TimeUnit.SECONDS.sleep(1);

        Assert.assertEquals(loginPageUrl,driver.getCurrentUrl());
        driver.get(loginPageUrl);

        TimeUnit.SECONDS.sleep(1);

        WebElement createAccount = driver.findElement(By.xpath("//*[text()='Create an account']"));
        createAccount.click();

        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(createacountUrl, driver.getCurrentUrl());
        driver.get(createacountUrl);

        driver.findElement(By.name("firstname")).sendKeys("Adashmn");
        driver.findElement(By.name("lastname")).sendKeys("Gox");
        driver.findElement(By.name("name")).sendKeys("sadarshx@gmail.com");
        driver.findElement(By.xpath("//input[contains(@placeholder, '312 345 6789')]")).sendKeys("3123451475");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Password')]")).sendKeys("Abdd$1234");
        driver.findElement(By.cssSelector("label.check-box.signupLabel.pr-0")).click();

        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.cssSelector("button.btn.btn-f.c-account")).click();

        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(baseUrl, driver.getCurrentUrl());

        driver.quit();
    }
}
