package com.acacia.myProduct;

import org.openqa.selenium.By;
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


    public DynamicElement(Context parent, WebElement element, By refreshBy, Identifier id, String foundBy) {
        super(parent);
        this.element = element;
        this.refreshBy = refreshBy;
        if (id == null)
            throw  new NullPointerException("id can not be null");
        this.id = id;
        this.foundBy = foundBy;
    }

    /**  --------- Constructor ----------------*/

    @Override
    protected SearchContext seleniumContext() {
        return element;
    }

    @Override
    public void persist() {


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
}
