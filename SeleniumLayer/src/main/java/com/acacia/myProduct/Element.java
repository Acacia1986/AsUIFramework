package com.acacia.myProduct;

import com.acacia.waits.Waiter;
import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by miaomiao on 6/4/2017.
 */
public abstract class Element implements ImElement{

    /**
     * This abstract method will let Element Class to be implemented. SO for Element Class will have StaticElement and DynamicElement
     * @return
     */
    protected abstract WebElement webElement();

    @Override
    public void click() {
        this.webElement().click();
    }

    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {

    }

    @Override
    public void clear() {
        try {
            webElement().clear();
        } catch (Exception handleRetry) {
            try {
                waitFor().visible();
                webElement().clear();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(),e);
            }
        }

    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public Waiter waitFor(){
        return new Waiter(this);
    }
}
