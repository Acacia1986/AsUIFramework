package com.acacia.pagelayer.oac.sac.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by miaomiao on 8/21/2017.
 */
public class AnalyticsPage {

    SelenideElement login_User_Name = $(By.xpath(".//*[@id='user']"));
    SelenideElement my_Account_Option = $(By.xpath(".//*[@id='menuOptionItem_MyAccount']"));
    SelenideElement preferences_Table = $(By.xpath(".//*[@id='idGeneralPreferencesTable']"));
    SelenideElement application_Roles_Tab = $(By.xpath(".//div[@title='Application Roles']"));
    List<SelenideElement> roles_List =  $$(By.xpath(".//*[@id='idGroupsTab_body']/div/table/tbody/tr/td/div"));
    SelenideElement cancel_Button = $(By.xpath(".//*[@name='Cancel']"));
    List<SelenideElement> action_List = $$(By.xpath(".//td[contains(@class,'masterHeader')]/span"));




    /**
     * Click the User Name.
     */
    public void clickUseNameDropDown(){
        login_User_Name.waitUntil(Condition.appear,9000);
        login_User_Name.shouldBe(Condition.enabled);
        login_User_Name.click();
    }

    /**
     * Choose My Account option.
     */
    public void chooseMyAccount(){
        my_Account_Option.shouldBe(Condition.appear);
        my_Account_Option.click();
    }

    /**
     * Wait until my account finish rending.
     */
    public void waitUntilMyAccountRending(){
        preferences_Table.waitUntil(Condition.appear,9000);
    }

    /**
     * Choose Application Roles.
     */
    public void chooseApplicationRoles(){
        application_Roles_Tab.shouldBe(Condition.enabled);
        application_Roles_Tab.click();
    }

    /**
     * Get the user's application roles.
     * @return
     */
    public List<String> checkRolesOfUserHave(){
        List<String> rolesName = new ArrayList<String>();
        String title;
       for(int i = 0;i < roles_List.size();i++){
           title = roles_List.get(i).getAttribute("title");
           rolesName.add(title);
        }
        return rolesName;
    }

    /**
     * Click cancel button from the My Account dialog.
     */
    public void cancel(){
        cancel_Button.click();
    }

    /**
     * Get the Create... action list.
     * @return
     */
    public List<String> getCreateActionTitleList(){
        List<String> action_title = new ArrayList<String>();
        String title;
        for (int i =0;i < action_title.size(); i++){
            title = action_List.get(i).getText();
            action_title.add(title);
        }
        return action_title;
    }



}
