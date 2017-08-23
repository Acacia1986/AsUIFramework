package com.acacia.selenide.test.OAC;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by miaomiao on 8/8/2017.
 */
public class SearchAskCreate1 {
        Logger logger = LoggerFactory.getLogger(SearchAskCreate1.class);




    @Test
    public void testSearchAskCreate1() throws InterruptedException {
        logger.info("testSearchAskCreate1");
        long time = System.currentTimeMillis();
        //Open VA
        open("http://slc08cwe.us.oracle.com:9704/va/home.jsp");
        //Login as admin
        $(By.id("idUser")).setValue("dvauthoruser");
        $(By.id("idPassword")).setValue("welcome1");
        $(By.id("btn_login")).submit();
        //Step 1 Login to VA. In Home page Find content Or visualize data search box, Start typing Product , auto suggest list will be displayed.
        $(By.id("home-tokenized-searchfield:searchfield_2:hsc")).waitUntil(Condition.appear,90000);
        $(By.id("home-tokenized-searchfield:searchfield_2:hsc")).should(Condition.visible);
        $(By.className("senseCompleteInputField")).sendKeys("Product");
        $(By.className("bitech-spinner")).waitUntil(Condition.hidden,9000);


        //Step 2 Select P1 Product from A - Sample Sales.Products
        $(By.xpath(".//*[@class='senseCompleteMenuContent']")).findElement(By.xpath(".//*[@title='A - Sample Sales.Products.P1  Product']")).click();
        $(By.className("bi_progressPane")).waitWhile(Condition.disabled,9000);
        $(By.className("bi_progressPane")).waitWhile(Condition.attribute("style","display: none;"),9000);
        $(By.className("bi_progressPane")).waitUntil(Condition.attribute("style","display: block;"),9000);
        $(By.className("bi_progressPane")).waitUntil(Condition.attribute("style","display: none;"),9000);
        $(By.xpath(".//*[contains(@class,'oj-datagrid-header-cell')]")).waitUntil(Condition.visible,9000);


        //Step 3 Verify Table view will be displayed with P1 Product column.
        $(By.xpath(".//*[@class='bi_viz_gridview_body_resize  bi_viz_gridview_table']")).shouldBe(Condition.appear);
        //Step 4 In search box start typing revenue. Select 1-Revenue from Base facts.
        $(By.xpath(".//*[@class='senseCompleteInputField'][last()]")).sendKeys("revenue");
        $(By.className("bitech-spinner")).waitUntil(Condition.hidden,9000);
        //Select 1-revenue
        $(By.xpath(".//*[@class='senseCompleteMenuContent']")).findElement(By.xpath(".//*[@title='A - Sample Sales.Base Facts.1- Revenue']")).click();
        $(By.className("bi_progressPane")).waitWhile(Condition.disabled,16000);
        $(By.className("bi_progressPane")).waitWhile(Condition.attribute("style","display: none;"),9000);
        $(By.className("bi_progressPane")).waitUntil(Condition.attribute("style","display: block;"),9000);
        $(By.className("bi_progressPane")).waitUntil(Condition.attribute("style","display: none;"),9000);

        $(By.xpath(".//*[@class='oj-dvtbase oj-chart oj-component-initnode']/*[name()='svg']")).waitUntil(Condition.attribute("width","100%"),9000);
        //Step 5
        $(By.xpath(".//*[@class='bi_viztitlewrapper bitech-autoapply-label']/label")).click();
        $(By.xpath(".//*[@data-bi_button_id='viztoolbar_openInVA']")).shouldBe(Condition.appear);
        //Step 6
        $(By.xpath(".//*[@data-bi_button_id='viztoolbar_openInVA']")).click();
        //Step 7 save project.
        switchTo().window("Untitled");
        $(By.xpath(".//*[@data-bi_button_id='reporttoolbar_save_btn']")).waitUntil(Condition.appear,9000);
        $(By.xpath(".//*[@data-bi_button_id='reporttoolbar_save_btn']")).click();
        $(By.xpath(".//*[@data-bi_menuoption_id='save']")).click();
        $(By.className("bitech-report-savedialog-content")).waitUntil(Condition.appear,9000);
        $(By.id("bitech_savedialog_nameinput_input")).sendKeys("Search_Ask_Project" + time);
        $(By.id("bi-report-saveas-dialog-containerokButton")).click();
        $(By.className("bitech-msgcomp-backsplash")).should(Condition.appear);
        $(By.className("bitech-msgcomp-message")).should(Condition.exactText("The project was successfully saved."));
    }

}
