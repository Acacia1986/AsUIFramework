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
    SelenideElement search_Button = $(By.xpath(".//*[contains(@id,'PickerSearch')]"));
    SelenideElement available_User_Panel = $(By.xpath(".//*[contains(@id,'memberShuttle::leadUl')]"));
    SelenideElement selected_User_Panel = $(By.xpath(".//*[contains(@id,'memberShuttle::trailUl')]"));
    SelenideElement move_Selected_User_To_Selected = $(By.xpath(".//a[@title='Move selected items to: Selected Users']"));
    SelenideElement ok_Button = $(By.xpath(".//button[.='OK']"));

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
       // name_Filter.setValue(name);
        name_Filter.clear();
        name_Filter.click();
        executeJavaScript("arguments[0].setAttribute('value',arguments[1])",name_Filter,name);
    }

    /**
     * Click search button.
     */
    public void clickSearchButton(){
        search_Button.should(Condition.enabled);
        search_Button.doubleClick();
    }

    /**
     * Wait until the result displayed in the available panel.
     */
    public void waitUntilGetResult(){
       //available_User_Panel.$(By.xpath("/li/label/input")).waitUntil(Condition.appear,9000);

        do{
            sleep(3000);

        }while(!available_User_Panel.find(By.xpath("/li/label/input")).isDisplayed());

    }

    /**
     * Select available user to selected user.
     */
    public void selectAvailableUserToSelectedUser(){
        SelenideElement available_User = $(By.xpath(".//input[contains(@id,'memberShuttle')]"));
        available_User.shouldBe(Condition.appear);
        available_User.click();
        move_Selected_User_To_Selected.waitUntil(Condition.enabled,9000);
        move_Selected_User_To_Selected.click();
        selected_User_Panel.$(By.xpath("/li/label/input")).waitUntil(Condition.appear,9000);
    }

    /**
     * Click Ok button.
     */
    public void clickOKButton(){
        ok_Button.shouldBe(Condition.appear);
        ok_Button.click();
    }





}
