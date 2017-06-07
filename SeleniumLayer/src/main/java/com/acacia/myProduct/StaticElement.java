package com.acacia.myProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by miaomiao on 6/7/2017.
 */
public class StaticElement extends  Element {
    private WebElement element;
    private final By refreshBy;
    /**
     * Don't want other package to init this class
     * @param parent
     */
    protected StaticElement(Context parent,By refreshBy) {
        super(parent);
        if (refreshBy == null)
            throw  new NullPointerException("RefreshBy cannot be null,please at least pass one value");
        this.refreshBy = refreshBy;
    }

    @Override
    public void persist() {
        getParent().persist();
    }

    /**
     * getParent come from Element.getParent and findElement is Context's SearchContext.findElement()
     */
    @Override
    protected void refresh() {
        //Need to investigate
        element = getParent().findElement(refreshBy);
    }

    /**
     *
     * @return
     */
    @Override
    protected SearchContext seleniumContext() {
        if (element == null)
            refresh();
        return element;
    }

    @Override
    protected WebElement webElement() {
        if ( element == null)
            refresh();
        return element;
    }

    @Override
    public String toString() {
        return String.format("Element [WebElement:%s][By:%s]",element,refreshBy);
    }
}
