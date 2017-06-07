package com.acacia.myProduct;

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
   protected static Element getElment(){
       return new UiSynchronizedElement(new )
   }


    /** -----------------Here are the abstract methods from Element ---------------------*/
    @Override
    protected SearchContext seleniumContext() {
        return null;
    }

    @Override
    public void persist() {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected WebElement webElement() {
        return null;
    }

    /* -------------------- Here are Override the WebElement which are Element impliment WebElement ---------------*/



}
