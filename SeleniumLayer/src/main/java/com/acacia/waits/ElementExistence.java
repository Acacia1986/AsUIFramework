package com.acacia.waits;

import com.acacia.myProduct.Element;
import org.openqa.selenium.WebDriverException;

/**
 * Created by miaomiao on 6/6/2017.
 */
public class ElementExistence implements ElementCondition {
    private final Element element;

    public ElementExistence(Element element){
        this.element = element;
    }

    @Override
    public boolean occurred() throws IllegalStateException {
        try {
            return element.exists();
        } catch (WebDriverException e) {
           return false;
        }
    }

    @Override
    public String toString(){
        return String.format("ElementExistence [%s]",element);
    }
}
