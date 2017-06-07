package com.acacia.myProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by miaomiao on 6/7/2017.
 */
public class UiSynchronizedElement extends Element {
    Logger logger = LoggerFactory.getLogger(UiSynchronizedElement.class);
    private  Element element;

    /**
     * Constructors
     * @param element
     */
   protected UiSynchronizedElement(Element element){
       super(element.getParent());
       this.element = element;
   }

   protected  UiSynchronizedElement(Context context){super(context);}


   /*  --------------------Its own method -------------------------------- */

    /**
     * Generate Static elements.
     * @param context
     * @param refineBy
     * @return
     */
   protected static Element getElment(Context context, By refineBy){
       return new UiSynchronizedElement(new StaticElement(context,refineBy));
   }
    /**
     * Generate Dynamic element
     * @param context
     * @param element
     * @param id
     * @param foundBy
     * @return
     */
   protected static Element getElment(Context context,WebElement element,Identifier id,String foundBy){
       return  new UiSynchronizedElement(new DynamicElement(context,element,id,foundBy));
   }



    /** -----------------Here are the abstract methods from Element ---------------------*/
    @Override
    protected SearchContext seleniumContext() {

        return element.seleniumContext();
    }

    @Override
    public void persist() {
        element.persist();
    }

    @Override
    protected void refresh() {
        element.refresh();
    }

    @Override
    protected WebElement webElement() {
        return element.webElement();
    }

    /* -------------------- Here are Override the WebElement which are Element impliment WebElement ---------------*/



}
