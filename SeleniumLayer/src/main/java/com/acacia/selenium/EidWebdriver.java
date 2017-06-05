package com.acacia.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * Created by miaomiao on 6/3/2017.
 */
public class EidWebdriver  implements WebDriver {

    private WebDriver webDriver;

    private String driverType;


    /**
     * @return WebDriver
     */
    protected WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * @return Driver type
     */
    public String getDriverType() {
        return driverType;
    }

    /**
     * Set Webdriver
     * @param webDriver
     */
    protected void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Set Driver type
     * @param driverType
     */
    protected void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    /**
     * Do not want this class instantiated by itself so that we control the setting of the type value.
     */
   protected  EidWebdriver(WebDriver webDriver,String driverType){
        this.webDriver = webDriver;
        this.driverType = driverType;
   }



    @Override
    public void get(String url) {
        webDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.close();
        this.quit();
    }

    public void closeCurrentBrowserWindow(){
       webDriver.close();
    }
    @Override
    public void quit() {


    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }
}
