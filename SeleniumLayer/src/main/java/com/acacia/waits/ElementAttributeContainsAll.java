package com.acacia.waits;

import com.acacia.myProduct.Element;

/**
 * Created by miaomiao on 6/6/2017.
 */
public class ElementAttributeContainsAll implements ElementCondition{

    private final Element element;
    private final String attribute;
    private final CharSequence[] valuesToContains;

    public ElementAttributeContainsAll(Element element, String attribut, CharSequence[] valuesToConatin) {
        this.element = element;
        this.attribute = attribut;
        this.valuesToContains = valuesToConatin;
    }


    /**
     * e.g element's attribut is "This is testing" and when pass "is test" as valuesToConatin. The first for loop is
     * existOrNot = true && true. The second for loop will existOrNot = true && false. So if one value didn't contain it will return as false.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public boolean occurred() throws IllegalStateException {
        boolean existOrNot = true;
        for (CharSequence seq:valuesToContains){
            existOrNot = existOrNot && element.getAttribute(attribute).contains(seq);
        }
        return existOrNot;
    }

    @Override
    public  String toString(){
        final StringBuilder values = new StringBuilder(valuesToContains.length);
        values.append(valuesToContains);
        return String.format("ElementAttributeConainsAll [Element: %s][Attribute: %s][Values: %s]",element, attribute,values.toString());
    }
}
