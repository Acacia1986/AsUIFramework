package com.acacia.selenide.testcases.sac.authorization;

import com.acacia.pagelayer.oac.sac.*;
import com.acacia.pagelayer.oac.sac.base.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class SACAuthorizationTest {
    Logger logger = LoggerFactory.getLogger(SACAuthorizationTest.class);
    private LoginPage loginPage;
    private SACHomePage sacHomePage;
    private AppRoleManagementPanel appRoleManagementPanel;
    private AddNewUserDialog addNewUserDialog;
    private AddRoleDialog addRoleDialog;
    private ManageMemberContainer manageMemberContainer;

    @BeforeMethod
    public void beforeMethod(){
        logger.info("Start before method");
        open(":9704/biserviceadministration/faces/index.jspx");
        loginPage = new LoginPage();
        loginPage.Login();
    }
    @Test
    public void testSACAuthorization(){
        logger.info("testSACAuthorization");
        //Step 1
        sacHomePage = new SACHomePage();
        sacHomePage.AppRoleManagementShouldBeSelectedAsDefault();
        appRoleManagementPanel = new AppRoleManagementPanel();
        appRoleManagementPanel.checkUsersTabBeSelectedAsDefault();
        //Step 2 create User.
        appRoleManagementPanel.clickAddUser();
        appRoleManagementPanel.checkAddNewUserDialogDisplay();
        addNewUserDialog = appRoleManagementPanel.getAddNewUserDialog();
        addNewUserDialog.checkAddUserDialogTitle();
        addNewUserDialog.checkFieldShouldBeRequired();
        addNewUserDialog.enterUserInfo("QA","QA_F","QA_L",null,null,null,"welcome1","welcome1");
        addNewUserDialog.save();
        //Find this new user to make sure it created.
        appRoleManagementPanel.search("QA");
        appRoleManagementPanel.checkCreatedUserDisplayOrNot("QA");
        //Step 3 Create Role.
        appRoleManagementPanel.selectSubTab(AppRoleManagementPanel.SubTab.ROLES);
        appRoleManagementPanel.clickAddRole();
        addRoleDialog = appRoleManagementPanel.getAddRoleDialog();
        addRoleDialog.checkAddRoleDialogTitle();
        addRoleDialog.enterRoleInfo("Role_QA","Role_QA",null);
        addRoleDialog.save();
        appRoleManagementPanel.search("Role_QA");
        appRoleManagementPanel.checkCreatedRoleDisplayOrNot("Role_QA");
        //Step 4
        appRoleManagementPanel.addToMember();
        appRoleManagementPanel.checkManageMemberContainerDisplayOrNot();
        //Step 5
        manageMemberContainer = appRoleManagementPanel.getManageMemberContainer();
        manageMemberContainer.selectOption(ManageMemberContainer.Options.USER);
        manageMemberContainer.enterNameToFilter("QA");
        manageMemberContainer.clickSearchButton();
        manageMemberContainer.waitUntilGetResult();
        manageMemberContainer.selectAvailableUserToSelectedUser();
        manageMemberContainer.clickOKButton();
        //Step 6













    }



}
