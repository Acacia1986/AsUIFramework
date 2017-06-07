package com.acacia.myProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * Created by miaomiao on 6/5/2017.
 */
public abstract class Context {
    /**
     * This method let the sub-class to impliment it . Such as StaticElement and DynamicElement
     * @return
     */
   protected  abstract SearchContext seleniumContext();

   public Element findElement(By by, Identifier identifier){

       return UiSynchronizedElement.getElment();
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



   public abstract void persist();

   protected abstract void refresh();


}
