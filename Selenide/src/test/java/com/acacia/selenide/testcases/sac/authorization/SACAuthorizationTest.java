package com.acacia.selenide.testcases.sac.authorization;

import com.acacia.pagelayer.oac.sac.*;
import com.acacia.pagelayer.oac.sac.base.AnalyticsPage;
import com.acacia.pagelayer.oac.sac.base.LoginPage;
import com.acacia.selenide.test.OAC.BaseTest;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.navigator;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class SACAuthorizationTest extends BaseTest{
    Logger logger = LoggerFactory.getLogger(SACAuthorizationTest.class);
    private LoginPage loginPage;
    private SACHomePage sacHomePage;
    private AppRoleManagementPanel appRoleManagementPanel;
    private AddNewUserDialog addNewUserDialog;
    private AddRoleDialog addRoleDialog;
    private ManageMemberContainer manageMemberContainer;
    private AddApplicationRoleDialog addApplicationRoleDialog;
    private AnalyticsPage analyticsPage;
    public final static String URL = "http://slc08cdr.us.XXX.com:9704/";
    public String user_name = "QA_"+ System.currentTimeMillis();
           // new Random().nextInt(10);
    public String role_name = "RoleQA"+ System.currentTimeMillis();
                   //new Random().nextFloat();
    public String app_role_name = "APPRole"+ System.currentTimeMillis();
                           //new Random().nextInt(10);


    @BeforeMethod
    public void beforeMethod(){
        logger.info("Start before method");
        //Configuration.browser = WebDriverRunner.PHANTOMJS;
        //Configuration.timeout = 15000;
        open(URL + "biserviceadministration/faces/index.jspx");
        loginPage = new LoginPage();
        loginPage.login("admin","welcome1");
    }
    @Test
    public void testSACAuthorization() {
        logger.info("testSACAuthorization");
        //Step 1
        sacHomePage = new SACHomePage();
        sacHomePage.getAppRoleManagement().shouldHave(Condition.attribute("aria-selected"));
        appRoleManagementPanel = new AppRoleManagementPanel();
        appRoleManagementPanel.getUsersTab().shouldHave(Condition.attribute("aria-selected"));
        //Step 2 create User.
        appRoleManagementPanel.clickAddUser();
        appRoleManagementPanel.getAddNewUserDialog().shouldBe(Condition.appear);
        addNewUserDialog = appRoleManagementPanel.initAddNewUserDialog();
        addNewUserDialog.getAddUserDialogTitle().shouldHave(Condition.matchText("Add New User"));
        //Check the fields are required.
        Condition condition = Condition.attribute("title","Required");
        addNewUserDialog.getFieldRequired(AddNewUserDialog.FIELDNAME.USER_NAME).shouldHave(condition);
        addNewUserDialog.getFieldRequired(AddNewUserDialog.FIELDNAME.FIRST_NAME).shouldHave(condition);
        addNewUserDialog.getFieldRequired(AddNewUserDialog.FIELDNAME.LAST_NAME).shouldHave(condition);
        addNewUserDialog.getFieldRequired(AddNewUserDialog.FIELDNAME.PASSWORD).shouldHave(condition);
        addNewUserDialog.getFieldRequired(AddNewUserDialog.FIELDNAME.CONFIRM_PASSWORD).shouldHave(condition);
        //Enter user information.
        addNewUserDialog.enterUserInfo(user_name,user_name,user_name,null,null,null,"welcome1","welcome1");
        addNewUserDialog.save();
        appRoleManagementPanel.waitUntilAddUserDisappear();
        //Find this new user to make sure it created.
        appRoleManagementPanel.search(user_name);
        appRoleManagementPanel.waitUntilGetResultList();//
        appRoleManagementPanel.getNewCreatedUser().shouldHave(Condition.text(user_name));
        //Step 3 Create Role.
        appRoleManagementPanel.selectSubTab(AppRoleManagementPanel.SubTab.ROLES);
        appRoleManagementPanel.clickAddRole();
        addRoleDialog = appRoleManagementPanel.getAddRoleDialog();
        addRoleDialog.getAddRoleDialogTitle().shouldHave(Condition.text("Add New Role"));
        addRoleDialog.enterRoleInfo(role_name,role_name,null);
        addRoleDialog.save();
        appRoleManagementPanel.waitUntilAddRoleDialogDisappear();
        appRoleManagementPanel.search(role_name);
        appRoleManagementPanel.waitUntilGetRolesResultList();
        appRoleManagementPanel.getNewCreatedRole().shouldHave(Condition.text(role_name));
        //Step 4
        appRoleManagementPanel.addToMember();
        appRoleManagementPanel.getManageMemberContainer().waitUntil(Condition.appear,30000);
        appRoleManagementPanel.getManageMemberContainer().shouldBe(Condition.appear);
        //Step 5
        manageMemberContainer = appRoleManagementPanel.initManageMemberContainer();
        manageMemberContainer.selectOption(ManageMemberContainer.Options.USER);
        manageMemberContainer.enterNameToFilter(user_name);
        manageMemberContainer.clickSearchButton();
        manageMemberContainer.waitUntilGetResult();
        manageMemberContainer.selectAvailableUserToSelectedUser();
        manageMemberContainer.clickOKButton();
        //Verify add member successful.
        appRoleManagementPanel.getNewAddedUserFromSelectedMemeber().shouldBe(Condition.appear);
        //Step 6
        appRoleManagementPanel.selectSubTab(AppRoleManagementPanel.SubTab.APPLICATION_ROLES);
        appRoleManagementPanel.clickAddAppRole();
        addApplicationRoleDialog = appRoleManagementPanel.getAddApplicationRoleDialog();
        addApplicationRoleDialog.getAddRoleDialogTitle().shouldHave(Condition.text("Add Custom Application Role"));
        addApplicationRoleDialog.enterApplicationRoleInfo(app_role_name,app_role_name,null);
        addApplicationRoleDialog.save();
        appRoleManagementPanel.searchAPP(app_role_name);
        appRoleManagementPanel.getCreatedAppRole().shouldHave(Condition.text(app_role_name));
        //Step 7
        appRoleManagementPanel.addToAppMember();
        appRoleManagementPanel.getManageMemberContainer();
        manageMemberContainer = appRoleManagementPanel.initManageMemberContainer();
        manageMemberContainer.selectOption(ManageMemberContainer.Options.ROLES);
        manageMemberContainer.enterNameToFilter(role_name);
        manageMemberContainer.clickSearchButton();
        manageMemberContainer.waitUntilGetResult();
        manageMemberContainer.selectAvailableRoleToSelectedRole();
        manageMemberContainer.clickOKButton();
        //Verify add role successful
        appRoleManagementPanel.getNewAddedRoleFromSelectedMemeber().shouldBe(Condition.appear);
        //Step 8
        appRoleManagementPanel.signOut();
        navigator.open(URL +"analytics");
        loginPage.login(user_name,"welcome1");
        //Step 9
        analyticsPage = new AnalyticsPage();
        analyticsPage.clickUseNameDropDown();
        analyticsPage.chooseMyAccount();
        analyticsPage.waitUntilMyAccountRending();
        analyticsPage.chooseApplicationRoles();
        analyticsPage.getRolesOfUserHave().shouldHave(CollectionCondition.texts(app_role_name,"Authenticated User","BI Consumer"));
        analyticsPage.cancel();
        //Step 10
        analyticsPage.getCreateActionTitleList().shouldHave(CollectionCondition.texts("Actionable Intelligence"));
    }



}
