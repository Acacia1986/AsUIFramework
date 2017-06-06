package com.acacia.waits;

import com.acacia.myProduct.Element;

/**
 * Created by miaomiao on 6/6/2017.
 */
public class ElementVisible implements ElementCondition{

    private final Element element;

    public ElementVisible(Element element){
        this.element = element;
    }


    public boolean occurred() throws IllegalStateException {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return  false;
        }
    }

    @Override
    public String toString(){
        return String.format("ElementVisiable [%s]",element);
    }

}
