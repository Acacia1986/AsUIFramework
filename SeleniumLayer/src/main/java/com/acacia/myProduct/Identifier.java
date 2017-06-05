package com.acacia.myProduct;

import org.openqa.selenium.By;

/**
 * Created by miaomiao on 6/5/2017.
 */
public interface Identifier {
    /**
     * Identify the element from a property of the element itself
     * @param elem the element
     * @return the locator that can find this element from its parent context
     */
    By identify(Element elem);
}
