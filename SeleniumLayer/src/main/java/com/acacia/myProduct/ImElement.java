package com.acacia.myProduct;

import com.acacia.waits.ElementCondition;
import com.acacia.waits.Waiter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * There define ImElement , because we want to make it much more extension not only the WebElement method but also define our own method
 *
 * Created by miaomiao on 6/4/2017.
 */
public interface ImElement extends WebElement{

    /**
     * You can add your won method here , which are about all the Web Element should have this behavior , like-a
     */
    Waiter waitFor();

     String getHTML();

     void dragAndDropTo(Element target);

     void dragWaitAndDrop(Element target, ElementCondition condition)
            throws IllegalStateException, TimeoutException, InterruptedException;

     void persist();

     boolean exists() throws IllegalStateException;

     void doMouseOver();

     Waiter waitFor(long timeout);

     Waiter waitFor(long timeout, long pollDelay);

     void controlClick();

     void hoverOnCanvas(int x, int y);
}
