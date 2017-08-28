package com.acacia.selenide.test.OAC;

import org.testng.annotations.BeforeSuite;

/**
 * Created by miaomiao on 8/24/2017.
 */

public class BaseTest {

    @BeforeSuite
    public void beforeSuite(){
        System.setProperty("selenide.timeout","12000");
    }




}
