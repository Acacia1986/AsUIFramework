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
        //Open VA
        open("http://10.88.67.118:9704/va/home.jsp");
        //Login as admin
        $(By.id("idUser")).setValue("admin");
        $(By.id("idPassword")).setValue("welcome1");
        $(By.id("btn_login")).submit();

        //Step 1 Login to VA. In Home page Find content Or visualize data search box, Start typing Product , auto suggest list will be displayed.
        $(By.id("home-tokenized-searchfield:searchfield_2:hsc")).waitUntil(Condition.appear,90000);
        $(By.id("home-tokenized-searchfield:searchfield_2:hsc")).should(Condition.visible);
        $(By.className("senseCompleteInputField")).sendKeys("Product");
        $(By.className("bitech-spinner")).waitUntil(Condition.hidden,9000);


        int size_1 = $$x(".//*[@class='senseCompleteMenuGroupWrapper']/div").size();
        System.out.println("First size is:" + size_1);
        int size = $$(By.xpath(".//*[@class='senseCompleteMenuGroupWrapper']/div")).size();
        System.out.println("Size is: " + size);
        //Step 2 Select P1 Product from A - Sample Sales.Products
        //$(By.xpath(".//*[@class='senseCompleteMenuGroupWrapper']")).findElement(By.xpath(".//*[@title='A - Sample Sales.Products.P1  Product']")).click();
        $(By.xpath(".//*[@class='senseCompleteMenuContent']")).findElement(By.xpath(".//*[@title='A - Sample Sales.Products.P1  Product']")).click();
       //$$(By.className("senseCompleteMenuOptionText")).findBy(By.xpath(".//*[@title='A - Sample Sales.Products.P1  Product']")).click();
        //$(By.className("bi_vizshelloutercontainer")).waitUntil(Condition.visible,9000);
        $(By.xpath(".//*[@class='oj-dvtbase oj-chart oj-component-initnode']")).waitUntil(Condition.appear,9000);
        //$(By.className("bi_progressPane")).waitUntil(Condition.attribute("Style","display: none;"),9000);

       //Step 3 Verify Table view will be displayed with P1 Product column.
        $(By.xpath(".//*[@class='bi_viz_gridview_body_resize  bi_viz_gridview_table']")).shouldBe(Condition.appear);
        /*$$(By.className("senseCompleteMenuOptionText")).findBy(Condition.text("Y11 - Other Demos.Telco Dim.Product")).click();
        Thread.sleep(5000);*/
        //Step 4 In search box start typing revenue. Select 1-Revenue from Base facts.
        $(By.xpath(".//*[@class='senseCompleteInputField'][last()]")).sendKeys("revenue");
        $(By.className("bitech-spinner")).waitUntil(Condition.hidden,9000);
        //Select 1-revenue
        $(By.xpath(".//*[@class='senseCompleteMenuContent']")).findElement(By.xpath(".//*[@title='A - Sample Sales.Base Facts.1- Revenue']")).click();
        $(By.xpath(".//*[@class='oj-dvtbase oj-chart oj-component-initnode']")).waitUntil(Condition.appear,9000);
        //Step 5
        $(By.className("bi_vizshelltitlecell")).hover();
        $(By.className("bi_inlinetoolbar_itemId_viztoolbar_openInVA bi_inlinetoolbar_item oj-button oj-component oj-enabled oj-component-initnode oj-button-half-chrome oj-button-icon-only oj-hover")).shouldBe(Condition.appear);
        //Step 6
        $(By.xpath(".//*[contains(@aria-label,'Group: 7 Megapixel Digital Camera')]")).click();




    }

}
