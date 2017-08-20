package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class AppRoleManagementPanel {
    SelenideElement user_tab = $(By.xpath(".//*[@role='presentation']/a[.='Users']"));
    SelenideElement roles_tab = $(By.xpath(".//*[@role='presentation']/a[.='Roles']"));
    SelenideElement application_role_tab = $(By.xpath(".//*[@role='presentation']/a[.='Application Roles']"));
    SelenideElement add_button_user = $(By.xpath(".//*[contains(@id,'ctb1CreateUser')]"));
    SelenideElement add_new_user_dialog = $(By.xpath(".//*[@role='dialog']"));
    SelenideElement search_box = $(By.xpath(".//input[contains(@id,'itSearchUsers')]"));
    SelenideElement search_user_result = $(By.xpath(".//*[contains(@id,'userDisplayName')]"));
    SelenideElement add_button_role = $(By.xpath(".//*[contains(@id,'ctb1CreateRole')]"));
    SelenideElement add_new_role_dialog = $(By.xpath(".//*[@role='dialog']"));
    SelenideElement search_role_result = $(By.xpath(".//*[contains(@id,'roleDisplayName')]"));
    SelenideElement manage_member_container = $(By.xpath(".//*[contains(@id,'ZombieMemberPicker::popup-container')]"));




    public static enum SubTab{
        USERS,
        ROLES,
        APPLICATION_ROLES
    }

    public void checkUsersTabBeSelectedAsDefault(){
        user_tab.shouldHave(Condition.attribute("aria-selected"));
    }

    /**
     * Select the sub tab from the application role management.
     * @param subTab
     */
    public void selectSubTab(SubTab subTab){
        switch(subTab){
            case USERS:
                if (user_tab.has(Condition.attribute("aria-selected"))&&user_tab.getAttribute("aria-selected").equals("true")){
                    break;
                }else {
                    user_tab.click();
                }
            break;
            case ROLES:
                if (roles_tab.has(Condition.attribute("aria-selected"))&&roles_tab.getAttribute("aria-selected").equals("true")){
                    break;
                }else {
                    roles_tab.click();
                }
            break;
            case APPLICATION_ROLES:
                if (application_role_tab.has(Condition.attribute("aria-selected"))&&roles_tab.getAttribute("aria-selected").equals("true")){
                    break;
                }else {
                    application_role_tab.click();
                }
            default:
        }
    }

    /**
     * Click the Add button to add new user.
     */
    public void clickAddUser(){
        add_button_user.shouldBe(Condition.visible);
        add_button_user.click();
    }

    /**
     * Check the Add New User dialog should pop-up after click Add button.
     */
    public void checkAddNewUserDialogDisplay(){
        add_new_user_dialog.shouldBe(Condition.appear);
    }

    /**
     * Get the Add New User dialog.
     * @return
     */
    public AddNewUserDialog getAddNewUserDialog(){
        return new AddNewUserDialog();
    }

    /**
     * Search the user.
     */
    public void search(String name){
        search_box.sendKeys(name);
        search_box.pressEnter();
    }

    /**
     * Find the new created user's text.
     * @return
     */
    public Boolean checkCreatedUserDisplayOrNot(String userName){
        return  search_user_result.getText().equals(userName);
    }


    /**
     * Click the Add button to add role.
     */
    public void clickAddRole(){
        add_button_role.shouldBe(Condition.visible);
        add_button_role.click();
    }

    /**
     * Check the Add Role dialog displayed.
     */
    public void checkAddRoleDialogDisplay(){
        add_new_role_dialog.shouldBe(Condition.appear);
    }


    /**
     * Get the Add Role dialog.
     * @return
     */
    public AddRoleDialog getAddRoleDialog(){
        return  new AddRoleDialog();
    }

    /**
     * Check create role successfully.
     * @param roleName
     * @return
     */
    public Boolean checkCreatedRoleDisplayOrNot(String roleName){
        return search_role_result.getText().equals(roleName);
    }

    /**
     * Click Member link.
     */
    public void addToMember(){
        SelenideElement member_Link = $(By.xpath(".//*[contains(@id,'membertitleLink')]"));
        member_Link.waitUntil(Condition.appear,9000);
        member_Link.shouldBe(Condition.enabled);
        member_Link.click();
    }

    /**
     * Check Manage Member Container displayed or not.
     */
    public void checkManageMemberContainerDisplayOrNot(){
        manage_member_container.waitUntil(Condition.appear,9000);
        manage_member_container.shouldBe(Condition.appear);
    }

    /**
     * Get the Manage Member Container.
     * @return
     */
    public ManageMemberContainer getManageMemberContainer(){
        return new ManageMemberContainer();
    }



}
