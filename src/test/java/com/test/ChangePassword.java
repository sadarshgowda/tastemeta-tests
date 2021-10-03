package com.test;

import com.util.base.BaseTestCase;
import com.util.base.ConfigReader;
import com.util.base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChangePassword extends BaseTestCase {
    private String baseUrl = ConfigReader.getProperty(Constants.BASE_URL);
    private String loginPageUrl = ConfigReader.getProperty(Constants.LOGIN_PAGE_URL);
    private String webDriver = ConfigReader.getProperty(Constants.WEB_DRIVER);
    private String userName = ConfigReader.getProperty(Constants.NEW_USER);
    private String passWord = ConfigReader.getProperty(Constants.NEW_USER_PASSWORD);
    private String loginSuccessPageUrl = ConfigReader.getProperty(Constants.LOGIN_SUCCESS_PAGE_URL);
    private String settingsUrl = ConfigReader.getProperty(Constants.SETTINGS_URL);
    private String newPassword = "1234$Efgh";



    @Test(priority = 0, description = "test case to change password")
    public void changePassword() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", webDriver);

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(baseUrl);

        //finding locator to click on login button
        WebElement login = driver.findElement(By.cssSelector("p.login.cursor"));
        login.click();

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(loginPageUrl,driver.getCurrentUrl());
        driver.get(loginPageUrl);

        //finding email web element to enter email
        WebElement email = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Email Address')]"));
        email.sendKeys(userName);

        //finding password web element to enter password
        WebElement password = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Password')]"));
        password.sendKeys(passWord);

        WebElement submit = driver.findElement(By.cssSelector("button.btn.btn-f.sign-in"));
        submit.click();

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(loginSuccessPageUrl, driver.getCurrentUrl());

        driver.findElement(By.xpath("//*[text()=' Settings']")).click();

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(settingsUrl, driver.getCurrentUrl());
        driver.get(driver.getCurrentUrl());

        //finding change password web elements and changing password
        driver.findElement(By.xpath("//*[text()='Change Password ']")).click();

        driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Password')]")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter new password')]")).sendKeys(newPassword);
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter confirm password')]")).sendKeys(newPassword);

        driver.findElement(By.cssSelector("button.btn.btn-confirm.mt-3")).click();

        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.cssSelector("p.login.cursor")).click();

        //signin with new password

        TimeUnit.SECONDS.sleep(3);
        driver.get(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("p.login.cursor")).click();

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(loginPageUrl,driver.getCurrentUrl());
        driver.get(loginPageUrl);

        //finding email web element to enter email
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Email Address')]")).sendKeys(userName);

        //finding password web element to enter password
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Password')]")).sendKeys(newPassword);

        driver.findElement(By.cssSelector("button.btn.btn-f.sign-in")).click();

        TimeUnit.SECONDS.sleep(5);

        Assert.assertEquals(loginSuccessPageUrl, driver.getCurrentUrl());

        driver.findElement(By.cssSelector("p.login.cursor")).click();

        driver.quit();

    }
}
