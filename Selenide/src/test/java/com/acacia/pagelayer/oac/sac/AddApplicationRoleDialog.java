package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by miaomiao on 8/21/2017.
 */
public class AddApplicationRoleDialog {


    SelenideElement add_app_role_title = $(By.xpath(".//*[contains(@id,'d1CreateRole::_ttxt')]"));
    SelenideElement role_Name_Label = $(By.xpath(".//*[contains(@id,'nameLabel')]/label"));
    SelenideElement role_Name_Input = $(By.xpath(".//input[contains(@id,'nameValueText')]"));
    SelenideElement role_Display_Name_Label = $(By.xpath(".//*[contains(@id,'dispNameLabel')]/label"));
    SelenideElement role_Display_Name_Input = $(By.xpath(".//input[contains(@id,'dispNameValueText')]"));
    SelenideElement role_Description_Label = $(By.xpath(".//*[contains(@id,'descLabel')]/label"));
    SelenideElement role_Description_TextArea = $(By.xpath(".//textarea[contains(@id,'descValueText')]"));


    SelenideElement save_Button = $(By.xpath(".//*[contains(@id,'CreateRole_ok')]"));
    SelenideElement cancel_Button = $(By.xpath(".//*[contains(@id,'CreateRole_cancel')]"));

    /**
     * check the Add Role dialog title.
     */
    public void checkAddRoleDialogTitle(){
        add_app_role_title.shouldHave(Condition.matchText("Add Custom Application Role"));
    }

    /**
     * Enter the application role information.
     */
    public void enterApplicationRoleInfo(String... roleInfo){
        role_Name_Input.sendKeys(roleInfo[0]);
        role_Display_Name_Input.sendKeys(roleInfo[1]);
        role_Description_TextArea.sendKeys(roleInfo[2]);
    }


    /**
     * Click the save button.
     */
    public void save(){
        save_Button.shouldBe(Condition.appear);
        save_Button.click();
    }


    /**
     * Click the cancel button.
     */
    public void cancel(){
        cancel_Button.shouldBe(Condition.appear);
        cancel_Button.click();
    }


}
