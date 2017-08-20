package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class AddNewUserDialog {

    SelenideElement add_new_user_title = $(By.xpath(".//div[contains(@id,'CreateUser::_ttxt')]"));
    SelenideElement user_Name = $(By.xpath(".//*[contains(@id,'usernameLabel')]"));
    SelenideElement user_Name_Input = $(By.xpath(".//input[contains(@id,'usernameValueText')]"));
    SelenideElement user_First_Name = $(By.xpath(".//*[contains(@id,'firstNameLabel')]"));
    SelenideElement user_First_Name_Input = $(By.xpath(".//input[contains(@id,'firstNameValueText')]"));
    SelenideElement user_Last_Name = $(By.xpath(".//*[contains(@id,'lastNameLabel')]"));
    SelenideElement user_Last_Name_Input = $(By.xpath(".//input[contains(@id,'lastNameValueText')]"));
    SelenideElement user_Display_Name = $(By.xpath(".//*[contains(@id,'displayNameLabel')]"));
    SelenideElement user_Display_Name_Input = $(By.xpath(".//input[contains(@id,'displayNameValueText')]"));
    SelenideElement user_Description = $(By.xpath(".//*[contains(@id,'descriptionLabel')]"));
    SelenideElement user_Description_Input = $(By.xpath(".//textarea[contains(@id,'descriptionValueText')]"));
    SelenideElement user_Email = $(By.xpath(".//*[contains(@id,'emailLabel')]"));
    SelenideElement user_Email_Input = $(By.xpath(".//input[contains(@id,'emailValueText')]"));
    SelenideElement user_Password = $(By.xpath(".//*[contains(@id,'passwordLabel')]"));
    SelenideElement user_Password_Input = $(By.xpath(".//input[contains(@id,'passwordValueText')]"));
    SelenideElement user_Confirm_Password = $(By.xpath(".//*[contains(@id,'confirmPasswordLabel')]"));
    SelenideElement user_Confirm_Password_Input = $(By.xpath(".//input[contains(@id,'confirmPasswordValueText')]"));

    SelenideElement save_button = $(By.xpath(".//*[contains(@id,'CreateUser_ok')]"));
    SelenideElement cancel_button = $(By.xpath(".//*[contains(@id,'CreateUser_cancel')]"));

    By label = By.xpath("label");
    By span = By.xpath("span");





    public void checkAddUserDialogTitle(){
         add_new_user_title.shouldHave(Condition.matchText("Add New User"));
    }

    /**
     * Check these fields are required.
     * Fields: username,firstName,lastName,password,confirm password.
     */
    public void checkFieldShouldBeRequired(){

        user_Name.find(label).shouldHave(Condition.matchText("Username"));
        user_Name.find(span).getAttribute("title").equals("Required");

        user_First_Name.find(label).shouldHave(Condition.matchText("First Name"));
        user_First_Name.find(span).getAttribute("title").equals("Required");

        user_Last_Name.find(label).shouldHave(Condition.matchText("Last Name"));
        user_Last_Name.find(span).getAttribute("title").equals("Required");

        user_Password.find(label).shouldHave(Condition.matchesText("Password"));
        user_Password.find(span).getAttribute("title").equals("Required");

        user_Confirm_Password.find(label).shouldHave(Condition.matchText("Confirm Password"));
        user_Confirm_Password.find(span).getAttribute("title").equals("Required");
    }

    /**
     * Enter the user's information.
     * @param userInfo
     */
    public void enterUserInfo(String... userInfo){
        if(userInfo.length > 8){
            throw new ArrayIndexOutOfBoundsException();
        }
        user_Name_Input.sendKeys(userInfo[0]);
        user_First_Name_Input.sendKeys(userInfo[1]);
        user_Last_Name_Input.sendKeys(userInfo[2]);
        user_Display_Name_Input.sendKeys(userInfo[3]);
        user_Description_Input.sendKeys(userInfo[4]);
        user_Email_Input.sendKeys(userInfo[5]);
        user_Password_Input.sendKeys(userInfo[6]);
        user_Confirm_Password_Input.sendKeys(userInfo[7]);
    }

    /**
     * Click the save button.
     */
    public void save(){
        save_button.shouldBe(Condition.visible);
        save_button.click();
    }

    /**
     * Click the cancel button.
     */
    public void cancel(){
        cancel_button.shouldBe(Condition.visible);
        cancel_button.click();
    }

}
