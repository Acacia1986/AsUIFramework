package com.acacia.pagelayer.oac.sac.base;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class LoginPage {


    public void Login(String user,String pw){
        $(By.id("idUser")).setValue(user);
        $(By.id("idPassword")).setValue(pw);
        $(By.id("btn_login")).submit();
    }



}
