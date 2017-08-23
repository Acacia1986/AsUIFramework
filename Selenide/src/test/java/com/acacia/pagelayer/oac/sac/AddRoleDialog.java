package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Created by miaomiao on 8/20/2017.
 */
public class AddRoleDialog {

    SelenideElement add_role_title = $(By.xpath(".//*[contains(@id,'d1CreateRole::_ttxt')]"));
    SelenideElement role_Name_Label = $(By.xpath(".//*[contains(@id,'roleNameLabel')]/label"));
    SelenideElement role_Name_Input = $(By.xpath(".//input[contains(@id,'roleNameValueText')]"));
    SelenideElement role_Display_Name_Label = $(By.xpath(".//*[contains(@id,'roleDisplayNameLabel')]/label"));
    SelenideElement role_Display_Name_Input = $(By.xpath(".//input[contains(@id,'roleDisplayNameValueText')]"));
    SelenideElement role_Description_Label = $(By.xpath(".//*[contains(@id,'roleDescriptionLabel')]/label"));
    SelenideElement role_Description_Input = $(By.xpath(".//textarea[contains(@id,'roleDescriptionValueText')]"));

    SelenideElement save_Button = $(By.xpath(".//*[contains(@id,'CreateRole_ok')]"));
    SelenideElement cancel_Button = $(By.xpath(".//*[contains(@id,'CreateRole_cancel')]"));

    /**
     * check the Add Role dialog title
     */
    public void checkAddRoleDialogTitle(){
        add_role_title.shouldHave(Condition.matchText("Add New Role"));
    }

    /**
     * Enter the role's information.
     * @param roleInfo
     */
    public  void enterRoleInfo(String... roleInfo){
        role_Name_Input.setValue(roleInfo[0]);
        role_Name_Input.sendKeys(roleInfo[0]);
        //role_Name_Input.click();
        //executeJavaScript("arguments[0].setAttribute('aria-required','false')");
        executeJavaScript("arguments[0].setAttribute('value',arguments[1])",role_Name_Input,roleInfo[0]);
        //role_Display_Name_Input.sendKeys(roleInfo[1]);
        role_Description_Input.sendKeys(roleInfo[2]);
    }

    /**
     * Click the save button.
     */
    public void save(){
        save_Button.shouldBe(Condition.appear);
        save_Button.doubleClick();
    }


    /**
     * Click the cancel button.
     */
    public void cancel(){
        cancel_Button.shouldBe(Condition.appear);
        cancel_Button.click();
    }






}
