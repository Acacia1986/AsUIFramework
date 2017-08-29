package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by miaomiao on 8/20/2017.
 */
public class ManageMemberContainer {
    SelenideElement type_Filter = $(By.xpath(".//*[contains(@id,'sol1FilterType::content')]"));
    SelenideElement name_Filter = $(By.xpath(".//*[contains(@id,'PickerFilter::content')]"));
    SelenideElement search_Button = $(By.xpath(".//button[contains(@id,'PickerSearch')]"));
    SelenideElement available_User = $(By.xpath(".//*[contains(@id,'memberShuttle::leadUl')]/li"));
    SelenideElement selected_User = $(By.xpath(".//*[contains(@id,'memberShuttle::trailUl')]/li"));
    SelenideElement move_Selected_User_To_Selected = $(By.xpath(".//a[@title='Move selected items to: Selected Users']"));
    SelenideElement ok_Button = $(By.xpath(".//button[.='OK']"));
    SelenideElement move_app_role_button = $(By.xpath(".//a[@title = 'Move selected items to: Selected Roles']"));

    public enum Options{
        USER,
        ROLES
    }


    /**
     * Select options from the type filter.
     * @param option
     */
    public void selectOption(Options option){
        switch(option){
            case USER:
                type_Filter.selectOption("Users");
            break;
            case ROLES:
                type_Filter.selectOption("Roles");
            break;
            default:
        }
    }

    /**
     * Enter name into filter.
     * @param name
     */
    public void enterNameToFilter(String name){
        name_Filter.clear();
        //name_Filter.sendKeys(name);
        name_Filter.setValue(name);
        sleep(9000);
        executeJavaScript("arguments[0].setAttribute('value',arguments[1])",name_Filter,name);
        String value = name_Filter.val();
        System.out.println("Value: " + value);
    }

    /**
     * Click search button.
     */
    public void clickSearchButton(){
        search_Button.waitUntil(Condition.enabled,9000);
        search_Button.click();
        sleep(30000);
    }

    /**
     * Wait until the result displayed in the available panel.
     */
    public void waitUntilGetResult(){
       available_User.waitUntil(Condition.appear,30000);
    }

    /**
     * Select available user to selected user.
     */
    public void selectAvailableUserToSelectedUser(){
       // SelenideElement available_User = $(By.xpath(".//input[contains(@id,'memberShuttle')]"));
        available_User.waitUntil(Condition.appear,9000);
        available_User.click();
        move_Selected_User_To_Selected.waitUntil(Condition.enabled,9000);
        move_Selected_User_To_Selected.click();
        selected_User.waitUntil(Condition.appear,9000);
    }

    public void selectAvailableRoleToSelectedRole(){
        // SelenideElement available_User = $(By.xpath(".//input[contains(@id,'memberShuttle')]"));
        available_User.waitUntil(Condition.appear,9000);
        available_User.click();
        move_app_role_button.waitUntil(Condition.enabled,9000);
        move_app_role_button.click();
        selected_User.waitUntil(Condition.appear,9000);
    }



    /**
     * Click Ok button.
     */
    public void clickOKButton(){
        ok_Button.waitUntil(Condition.appear,9000);
        ok_Button.click();
    }

}
