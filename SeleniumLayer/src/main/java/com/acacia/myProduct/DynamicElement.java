package com.acacia.myProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by miaomiao on 6/7/2017.
 */
public class DynamicElement extends Element {
    private WebElement element;
    private By refreshBy;
    private final Identifier id;
    private final String foundBy;

    /**  --------- Constructor ----------------*/
    public DynamicElement(Context parent, WebElement element,  Identifier id, String foundBy) {
        super(parent);
        this.element = element;
        this.refreshBy = null;
        if (id == null)
            throw  new NullPointerException("id can not be null");
        this.id = id;
        this.foundBy = foundBy;
    }



    @Override
    protected SearchContext seleniumContext() {
        return element;
    }

    @Override
    public void persist() {
        getParent().persist();
        try {
            if (refreshBy == null)
                this.refreshBy = id.identify(this);
        } catch (IllegalStateException e) {
           String message = String.format("Cause IllegalStateException: Trying to persist() a stale element. Try invoking persist() earlier.%n(%s)",
                   this.toString());
            throw new IllegalStateException(message, e);
        }catch (NoSuchElementException e){
            String message = String.format("NoSuchElementException: Try invoking persist() earlier.%n(%s)", this.toString());
            throw new IllegalStateException(message,e);
        }
    }

    @Override
    protected void refresh() {
        if (refreshBy == null){
            String message =  String.format("persist() was not invoked so can not auto-refresh this (%s)", this.toString());
            throw new IllegalStateException(message);
        }
        element = getParent().findElement(refreshBy);
    }

    @Override
    protected WebElement webElement() {
        return element;
    }

    @Override
    public String toString() {
         return String.format("Element [WebElement: %s][By: %s][Identifier:%s][FoundBy:%s]", element, refreshBy, id, this.foundBy);
    }
}
