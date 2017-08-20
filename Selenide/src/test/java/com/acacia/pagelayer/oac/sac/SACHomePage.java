package com.acacia.pagelayer.oac.sac;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by miaomiao on 8/17/2017.
 */
public class SACHomePage {

    public enum TabName{
        APP_ROLE_MANAGEMENT,
        SNAPSHOTS,
        DATABASE_CONNECTIONS,
        DELIVERIES
    }

    /**
     * Choose the tab of the SAC home page.
     * @param tabName
     */
    public void selectTab(TabName tabName){
        switch(tabName){
            case APP_ROLE_MANAGEMENT:
                $(By.xpath(".//*[@role='presentation']/a[.='Application Role Management']")).click();
            break;
            case SNAPSHOTS:
            break;
            default:
        }
    }
    /**
     * Check app role management should be selected as default.
     */
    public void AppRoleManagementShouldBeSelectedAsDefault(){
        $(By.xpath(".//*[@role='presentation']/a[.='Application Role Management']")).shouldHave(Condition.attribute("aria-selected"));
    }

    /**
     * Get the application role management panel.
     * @return
     */
    public AppRoleManagementPanel getAppRoleManagementPanel(){
        return new AppRoleManagementPanel();
    }







}
