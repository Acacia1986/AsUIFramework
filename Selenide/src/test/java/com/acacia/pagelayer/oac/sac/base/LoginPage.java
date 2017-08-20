package com.acacia.pagelayer.oac.sac.base;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class LoginPage {


    public void Login(){
        $(By.id("idUser")).setValue("admin");
        $(By.id("idPassword")).setValue("welcome1");
        $(By.id("btn_login")).submit();
    }



}
