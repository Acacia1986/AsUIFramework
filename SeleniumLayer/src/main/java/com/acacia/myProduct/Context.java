package com.acacia.myProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaomiao on 6/5/2017.
 */
public abstract class Context {

    protected abstract Browser getBrowser();
    /**
     * This method let the sub-class to impliment it . Such as StaticElement and DynamicElement
     * @return
     */
   protected  abstract SearchContext seleniumContext();

    /**
     * Find the Dynamic Element
     * @param by
     * @param identifier
     * @return
     */
   public Element findElement(By by, Identifier identifier){

       try {
           return UiSynchronizedElement.getElment(this,seleniumContext().findElement(by),identifier,by.toString());
       } catch (StaleElementReferenceException e) {
           refresh();
           return  new DynamicElement(this,seleniumContext().findElement(by),identifier,by.toString());
       }

   }

    /**
     * Find a list of Dynamic Elements.
     * @param by
     * @param identifier
     * @return
     */
   public List<Element> findElements(By by, Identifier identifier){
       List<WebElement> raw;
       try {
           raw = seleniumContext().findElements(by);
       } catch (StaleElementReferenceException e) {
          refresh();
          raw = seleniumContext().findElements(by);
       }
       List<Element> target = new ArrayList<Element>(raw.size());
       for (WebElement el : raw){
           //this will generate a Dynamic element with UiSynchronizedElement.getElment() method
           target.add(UiSynchronizedElement.getElment(this,el,identifier,by.toString()));
       }
        return target;
   }



    /**
     * This method help to do webdriver SearchContext.findElement() but not WebElement.findElemnt()
     * @param by
     * @return
     */
   protected WebElement findElement(By by){

       try {
           return seleniumContext().findElement(by);
       } catch (StaleElementReferenceException e) {
           refresh();
           return seleniumContext().findElement(by);
       }
   }


    /**
     * These abstract methods will implement in StaticElement and DynamicElement. perist() is much meaningful for DynamicElement.
     *
     */
   public abstract void persist();

    /**
     * Refresh means to call Context.protected WebElement findElement(By by)
     * so refresh means to find parent element
     */

   protected abstract void refresh();


}
