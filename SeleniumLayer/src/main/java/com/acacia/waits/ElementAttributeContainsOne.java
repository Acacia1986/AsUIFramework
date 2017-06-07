package com.acacia.waits;

import com.acacia.myProduct.Element;

/**
 * Created by miaomiao on 6/7/2017.
 */
public class ElementAttributeContainsOne implements ElementCondition {

    private final Element element;
    private final String attribute;
    private final CharSequence[] valueToContains;

    public ElementAttributeContainsOne(Element element, String attribute, CharSequence... valueToContains) {
        this.element = element;
        this.attribute = attribute;
        this.valueToContains = valueToContains;
    }

    /**
     * e.g element 's attribute is "This is testing". When pass "is test" as valueToConatins, first for loop is
     * false = false || true  Then the second loop is true = true || whatever is true or false. It will always return true
     * @return
     * @throws IllegalStateException
     */
    @Override
    public boolean occurred() throws IllegalStateException {
        boolean existOrNot = false;
        for (CharSequence value: valueToContains){
            existOrNot = existOrNot || element.getAttribute(attribute).contains(value);
        }
        return false;
    }

    @Override
    public String toString(){
        final StringBuilder values = new StringBuilder(valueToContains.length);
        values.append(valueToContains);
        return String.format("ElementAttributeContainsOne [Element: '%s'][attribute: '%s'][values: %s]", element,attribute,values.toString());
    }
}
