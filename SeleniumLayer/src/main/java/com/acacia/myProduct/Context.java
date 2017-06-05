package com.acacia.myProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

/**
 * Created by miaomiao on 6/5/2017.
 */
public abstract class Context {

   protected  abstract SearchContext seleniumContext();

   public Element findElement(By by, Identifier id ){

       return null;
   }

}
