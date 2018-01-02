package com.acacia.myProduct;

import com.acacia.common.Configuration;
import com.acacia.selenium.EidWebdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.net.URL;

/**
 * Created by Acacia on 2017/6/7.
 */
public class Browser extends Context {
    Logger logger = LoggerFactory.getLogger(Browser.class);
    private final By[] locatorIgnoreListOnRefresh;



    protected EidWebdriver webDriver;
    private final String baseUrl;
    //Browser Size
    private static final String BROWSER_SIZE_DIMENSIONS_PROPERTY_NAME = "browser.size.dimensions";
    private static final String BROWSER_WITH_HEIGHT_SPLIT_REGEX = ",";
    public static final String MAXIMIZED = "maximized";

    public final static String BROWSER_SIZE_WIDTH_HEIGHT = Configuration.getValue(BROWSER_SIZE_DIMENSIONS_PROPERTY_NAME, MAXIMIZED)
            .toLowerCase();
    public final Integer BROWSER_MILLISECONDS_AFTER_MAXIMIZE = Configuration.getInt("browser.milliseconds.after.maximize", 0);

    /* ------------------------Constructor ----------------------------------*/
    public Browser(URL url, String BaseURL) throws ConfigurationException {
            this(url,BaseURL,null);
    }
    public Browser(URL url, String BaseURL, final By[] locatorIgnoreListOnRefresh) throws ConfigurationException {
        if (locatorIgnoreListOnRefresh != null) {
            logger.info("Page Done Loading Filter - applied for objects: ");
            for (By by : locatorIgnoreListOnRefresh) {
                logger.info("\t" + by.toString());
            }
            this.locatorIgnoreListOnRefresh = locatorIgnoreListOnRefresh.clone();
        } else {
            //lets just make sure this is initialized
            this.locatorIgnoreListOnRefresh = null;
        }

        webDriver = new WebDriverFactory().getWebDriver();
        webDriver.get(url.toString());

        if (BROWSER_SIZE_WIDTH_HEIGHT.contentEquals(MAXIMIZED)) {
            webDriver.manage().window().maximize();

            if (BROWSER_MILLISECONDS_AFTER_MAXIMIZE > 0) {
                try {
                    Thread.sleep(BROWSER_MILLISECONDS_AFTER_MAXIMIZE);
                } catch (InterruptedException e) {
                    throw new ConfigurationException();
                }
            }
        } else {
            int width, height;
            try {
                width = Integer.parseInt(BROWSER_SIZE_WIDTH_HEIGHT.split(BROWSER_WITH_HEIGHT_SPLIT_REGEX)[0]);
                height = Integer.parseInt(BROWSER_SIZE_WIDTH_HEIGHT.split(BROWSER_WITH_HEIGHT_SPLIT_REGEX)[1]);
                webDriver.manage().window().setSize(new Dimension(width, height));
            } catch (Exception iobe) {
                logger.error("Error parsing property: [" + BROWSER_SIZE_DIMENSIONS_PROPERTY_NAME + "] with value: ["
                        + BROWSER_SIZE_WIDTH_HEIGHT + "]. Please specify values 'maximized' or integer values of 'width,height'");
                logger.warn("Continuing with maximized browser");
                webDriver.manage().window().maximize();

            }
        }

        this.baseUrl = BaseURL;

    }

    /*---------------------------Get/Set----------------------------------------*/
    public EidWebdriver getWebDriver() {
        return webDriver;
    }


    /* ------------------------Extend from Context ------------------------*/

    @Override
    protected Browser getBrowser() {
        return this;
    }

    protected SearchContext seleniumContext() {
        return null;
    }

    public void persist() {

    }

    protected void refresh() {

    }
}
