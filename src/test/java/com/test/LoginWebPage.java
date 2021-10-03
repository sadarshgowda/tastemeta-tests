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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginWebPage extends BaseTestCase {

    private String baseUrl = ConfigReader.getProperty(Constants.BASE_URL);
    private String loginPageUrl = ConfigReader.getProperty(Constants.LOGIN_PAGE_URL);
    private String webDriver = ConfigReader.getProperty(Constants.WEB_DRIVER);
    private String validUsername = ConfigReader.getProperty(Constants.VALID_USERNAME);
    private String validPassword = ConfigReader.getProperty(Constants.VALID_PASSWORD);
    private String inValidUsername = ConfigReader.getProperty(Constants.INVALID_USERNAME);
    private String inValidPassword = ConfigReader.getProperty(Constants.INVALID_PASSWORD);
    private String loginSuccessPageUrl = ConfigReader.getProperty(Constants.LOGIN_SUCCESS_PAGE_URL);

    @Test(priority = 0, description = "checking login functionality with valid username and password")
    public void loginWithValidCredentials() throws InterruptedException {

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

        //finding email web element to enter email
        WebElement email = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Email Address')]"));
        email.sendKeys(validUsername);

        WebElement password = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Password')]"));
        password.sendKeys(validPassword);

        WebElement submit = driver.findElement(By.cssSelector("button.btn.btn-f.sign-in"));
        submit.click();

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(loginSuccessPageUrl, driver.getCurrentUrl());

        driver.quit();


    }


    @Test(priority = 1, description = "checking login functionality with invalid username and valid password")
    public void loginWithInValidCredentials() throws InterruptedException {

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

        //finding email web element to enter email
        WebElement email = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Email Address')]"));
        email.sendKeys(inValidUsername);

        WebElement password = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Password')]"));
        password.sendKeys(validPassword);

        WebElement submit = driver.findElement(By.cssSelector("button.btn.btn-f.sign-in"));
        submit.click();

        TimeUnit.SECONDS.sleep(2);

        Assert.assertNotEquals(loginSuccessPageUrl, driver.getCurrentUrl());

        driver.quit();


    }
}
