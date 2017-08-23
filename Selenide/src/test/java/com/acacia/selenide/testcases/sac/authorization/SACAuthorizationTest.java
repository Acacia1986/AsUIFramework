package com.acacia.selenide.testcases.sac.authorization;

import com.acacia.pagelayer.oac.sac.*;
import com.acacia.pagelayer.oac.sac.base.AnalyticsPage;
import com.acacia.pagelayer.oac.sac.base.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.navigator;
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
    private AddApplicationRoleDialog addApplicationRoleDialog;
    private AnalyticsPage analyticsPage;
    public static String URL = "http://slc02wam.us.oracle.com:9704/";
    public String user_name = "QA_"+ System.currentTimeMillis();
           // new Random().nextInt(10);
    public String role_name = "RoleQA"+ new Random().nextFloat();
    public String app_role_name = "APPRole"+ System.currentTimeMillis();
                           //new Random().nextInt(10);



    @BeforeMethod
    public void beforeMethod(){
        logger.info("Start before method");
        open(URL + "biserviceadministration/faces/index.jspx");
        loginPage = new LoginPage();
        loginPage.Login("admin","welcome1");
    }
    @Test
    public void testSACAuthorization() {
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
        addNewUserDialog.enterUserInfo(user_name,user_name,user_name,null,null,null,"welcome1","welcome1");
        addNewUserDialog.save();
        appRoleManagementPanel.waitUntilAddUserDisappear();
        //Find this new user to make sure it created.
        appRoleManagementPanel.search(user_name);
        appRoleManagementPanel.waitUntilGetResultList();//
        Assert.assertTrue(appRoleManagementPanel.checkCreatedUserDisplayOrNot(user_name));
        //Step 3 Create Role.
        appRoleManagementPanel.selectSubTab(AppRoleManagementPanel.SubTab.ROLES);
        appRoleManagementPanel.clickAddRole();
        addRoleDialog = appRoleManagementPanel.getAddRoleDialog();
        addRoleDialog.checkAddRoleDialogTitle();
        addRoleDialog.enterRoleInfo("Role_123",role_name,null);
        addRoleDialog.save();
        appRoleManagementPanel.waitUntilAddRoleDialogDisappear();
        appRoleManagementPanel.search("Role_123");
        appRoleManagementPanel.waitUntilGetRolesResultList();
        Assert.assertTrue(appRoleManagementPanel.checkCreatedRoleDisplayOrNot("Role_123"));
        //Step 4
        appRoleManagementPanel.addToMember();
        appRoleManagementPanel.checkManageMemberContainerDisplayOrNot();
        //Step 5
        manageMemberContainer = appRoleManagementPanel.getManageMemberContainer();
        manageMemberContainer.selectOption(ManageMemberContainer.Options.USER);
        manageMemberContainer.enterNameToFilter(user_name);
        manageMemberContainer.clickSearchButton();
        manageMemberContainer.waitUntilGetResult();
        manageMemberContainer.selectAvailableUserToSelectedUser();
        manageMemberContainer.clickOKButton();
        //Verify add member successful.
        appRoleManagementPanel.checkAddUserMemeberSuccessOrNot();
        //Step 6
        appRoleManagementPanel.selectSubTab(AppRoleManagementPanel.SubTab.APPLICATION_ROLES);
        appRoleManagementPanel.clickAddRole();
        addApplicationRoleDialog = appRoleManagementPanel.getAddApplicationRoleDialog();
        addApplicationRoleDialog.checkAddRoleDialogTitle();
        addApplicationRoleDialog.enterApplicationRoleInfo(app_role_name,app_role_name,null);
        addApplicationRoleDialog.save();
        appRoleManagementPanel.search(app_role_name);
        Assert.assertTrue(appRoleManagementPanel.checkCreatedAppRoleDisplayOrNot(app_role_name));
        //Step 7
        appRoleManagementPanel.addToAppMember();
        appRoleManagementPanel.checkManageMemberContainerDisplayOrNot();
        manageMemberContainer = appRoleManagementPanel.getManageMemberContainer();
        manageMemberContainer.selectOption(ManageMemberContainer.Options.ROLES);
        manageMemberContainer.enterNameToFilter(role_name);
        manageMemberContainer.clickSearchButton();
        manageMemberContainer.waitUntilGetResult();
        manageMemberContainer.selectAvailableUserToSelectedUser();
        manageMemberContainer.clickOKButton();
        //Verify add role successful
        appRoleManagementPanel.checkAddRoleMemeberSuccessOrNot();
        //Step 8
        appRoleManagementPanel.signOut();
        navigator.open(URL +"analytics");
        loginPage.Login(user_name,"welcome1");
        //Step 9
        analyticsPage = new AnalyticsPage();
        analyticsPage.clickUseNameDropDown();
        analyticsPage.chooseMyAccount();
        analyticsPage.waitUntilMyAccountRending();
        analyticsPage.chooseApplicationRoles();
        List<String> getRoleName = analyticsPage.checkRolesOfUserHave();
        Assert.assertTrue(getRoleName.contains("Authenticated User"));
        Assert.assertTrue(getRoleName.contains("BI Consumer"));
        Assert.assertTrue(getRoleName.contains("qa_app_role"));
        analyticsPage.cancel();
        //Step 10
        List<String> action_List = analyticsPage.getCreateActionTitleList();
        Assert.assertTrue(action_List.contains("Actionable Intelligence"));
    }



}
