package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

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
    ElementsCollection search_users_result_list = $$(By.xpath(".//div[(@aria-label='Users')]/div[2]/table/tbody/tr"));
    ElementsCollection search_roles_result_list = $$(By.xpath(".//div[(@aria-label='Roles')]/div[2]/table/tbody/tr"));

    SelenideElement add_button_role = $(By.xpath(".//*[contains(@id,'ctb1CreateRole')]/a"));
    SelenideElement add_new_role_dialog = $(By.xpath(".//*[@role='dialog']"));
    SelenideElement search_role_result = $(By.xpath(".//*[contains(@id,'roleDisplayName')]"));
    SelenideElement manage_member_container = $(By.xpath(".//*[contains(@id,'ZombieMemberPicker::popup-container')]"));
    SelenideElement search_app_role_result = $(By.xpath(".//*[contains(@id,'approlesDisplayName')]"));
    SelenideElement sign_Out_link = $(By.xpath(".//a[.='Sign Out']"));






    public static enum SubTab{
        USERS,
        ROLES,
        APPLICATION_ROLES
    }

    public SelenideElement getUsersTab(){
        return user_tab;
       // user_tab.shouldHave(Condition.attribute("aria-selected"));
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
        do{
            add_button_user.waitUntil(Condition.visible,9000);
            add_button_user.doubleClick();
            add_new_user_dialog.waitUntil(Condition.appear,9000);
        }while(!add_new_user_dialog.isDisplayed());
    }

    /**
     * Check the Add New User dialog should pop-up after click Add button.
     */
    public SelenideElement getAddNewUserDialog(){
        return add_new_user_dialog;
        //add_new_user_dialog.shouldBe(Condition.appear);
    }




    /**
     * wait until add user dialog disappear.
     */
    public void waitUntilAddUserDisappear(){
        add_new_user_dialog.waitUntil(Condition.disappear,9000);
    }


    /**
     * Get the Add New User dialog.
     * @return
     */
    public AddNewUserDialog initAddNewUserDialog(){
        return new AddNewUserDialog();
    }

    /**
     * Search the user.
     */
    public void search(String name){
        search_box.waitUntil(Condition.enabled,9000);
        search_box.clear();
        search_box.sendKeys(name);
        search_box.pressEnter();

    }

    /**
     *wait until only one result in the list.
     */
    public void waitUntilGetResultList(){
        long start_time = System.currentTimeMillis();
        long end_time;
        int size;
        do{
           sleep(3000);
           end_time = System.currentTimeMillis();
           size = search_users_result_list.size();

        }while(size >1 && end_time-start_time<20000);

    }


    /**
     * Find the new created user's text.
     * @return
     */
    public SelenideElement getNewCreatedUser(){
       // System.out.println("@@@"+ search_user_result.getText()+"@@@");
        //return  search_user_result.getText().equals(userName);
        return search_user_result;
    }


    /**
     * Click the Add button to add role.
     */
    public void clickAddRole(){
        do{
            add_button_role.waitUntil(Condition.appear,9000);
            add_button_role.doubleClick();
            //add_new_role_dialog.waitUntil(Condition.appear,9000);
        }while(!add_new_role_dialog.isDisplayed());
    }



    /**
     * Wait until add role dialog disappear.
     */
    public void waitUntilAddRoleDialogDisappear(){
        add_new_role_dialog.waitUntil(Condition.disappear,9000);
    }


    /**
     *wait until only one result in the list.
     */
    public void waitUntilGetRolesResultList(){
        long start_time = System.currentTimeMillis();
        long end_time;
        int size;
        do{
            sleep(3000);
            end_time = System.currentTimeMillis();
            size = search_roles_result_list.size();
        }while(size > 1 && end_time-start_time<20000);
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
     * @return
     */
    public SelenideElement getNewCreatedRole(){
       // return search_role_result.getText().equals(roleName);
        return search_role_result;
    }

    /**
     * Click Member link.
     */
    public void addToMember(){
        SelenideElement member_Link = $(By.xpath(".//*[contains(@id,'membertitleLink')]"));
        member_Link.waitUntil(Condition.appear,9000);
        member_Link.waitUntil(Condition.enabled,9000);
        member_Link.click();
    }

    /**
     * Check Manage Member Container displayed or not.
     */
    public SelenideElement getManageMemberContainer(){
        manage_member_container.waitUntil(Condition.appear,9000);
       // manage_member_container.shouldBe(Condition.appear);
        return manage_member_container;
    }

    /**
     * Get the Manage Member Container.
     * @return
     */
    public ManageMemberContainer initManageMemberContainer(){
        return new ManageMemberContainer();
    }

    /**
     * Check member user number displayed or not.
     */
    public SelenideElement getNewAddedUserFromSelectedMemeber(){
        //$(By.xpath(".//a[contains(@id,'numberUsers')]")).shouldBe(Condition.appear);
        SelenideElement user_In_Selected = $(By.xpath(".//a[contains(@id,'numberUsers')]"));
        return user_In_Selected;
    }


    /**
     * Click the Add button to add an application role.
     */
    public void clickAddAppRole(){
        this.clickAddRole();
    }



    /**
     * Get the Add Application Role Dialog.
     * @return
     */
    public AddApplicationRoleDialog getAddApplicationRoleDialog(){
        return new AddApplicationRoleDialog();
    }



    /**
     * Check create app role successfully.
     * @return
     */
    public SelenideElement getCreatedAppRole(){
        //return search_app_role_result.getText().equals(roleName);
        return search_app_role_result;
    }


    /**
     * Click application role Member link.
     */
    public void addToAppMember(){
        SelenideElement app_member_Link = $(By.xpath(".//*[contains(@id,'memberlink')]"));
        app_member_Link.waitUntil(Condition.appear,9000);
        app_member_Link.waitUntil(Condition.enabled,9000);
        app_member_Link.click();
    }


    /**
     * Check member role number displayed or not.
     */
    public SelenideElement getNewAddedRoleFromSelectedMemeber(){
        //$(By.xpath(".//a[contains(@id,'numberGroups')]")).shouldBe(Condition.appear);
        SelenideElement role_In_Selected = $(By.xpath(".//a[contains(@id,'numberGroups')]"));
        return role_In_Selected;
    }

    /**
     * Sign out sac site.
     */
    public void signOut(){
        sign_Out_link.waitUntil(Condition.enabled,9000);
        sign_Out_link.click();
    }

}
