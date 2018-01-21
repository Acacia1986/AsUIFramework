package com.acacia.selenide.test.OAC;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.WebDriverRunner.PHANTOMJS;

/**
 * Created by miaomiao on 8/24/2017.
 */

public class BaseTest {

    @BeforeSuite
    public void beforeSuite(){
        System.setProperty("selenide.timeout","12000");
        System.getProperty("selenide.browser", System.getProperty("browser", PHANTOMJS));
    }




}
