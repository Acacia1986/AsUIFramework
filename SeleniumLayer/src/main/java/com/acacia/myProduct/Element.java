package com.acacia.myProduct;

import com.acacia.waits.ElementCondition;
import com.acacia.waits.Waiter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by miaomiao on 6/4/2017.
 */
public abstract class Element extends Context implements ImElement{
    private final Browser browser;



    private final Context parent;



    /**
     * Constructor part
     * @param parent
     */
    protected Element(Context parent){
        this.browser = parent.getBrowser();
        this.parent = parent;

    }


    /* ----------Get,Set----------- */
    public Context getParent() {
        return parent;
    }
    @Override
    public Browser getBrowser() {
        return browser;
    }

    /* ------------- Its own abstract method ----------------------*/
    //These abstract method come from Context Class and one abstract method seleniumContext didn't implement here. It will be implimented in
    //StaticElement class and DynamicElement class.
    @Override
    public abstract void persist();

    @Override
    protected abstract void refresh() throws IllegalStateException, java.util.NoSuchElementException;

    /**
     * This abstract method will let Element Class to be implemented. SO for Element Class will have StaticElement and DynamicElement
     * @return
     */
    protected abstract WebElement webElement();


    /*  -------------Impliment Method Part ---------------------*/

    /**
     * Create a Waiter class object for the other method to call
     * @return
     */
    @Override
    public Waiter waitFor(){
        return new Waiter(this);
    }

    @Override
    public void click() {
        try {
            this.webElement().click();
        } catch (Exception handleRetry) {
            try {
                waitFor().exists();
                waitFor().visible();
                webElement().click();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(),e);
            }
        }
    }



    @Override
    public void submit() {
        try {
            webElement().submit();
        } catch (Exception handleRety) {
            try {
                waitFor().visible();
                webElement().submit();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(),e);
            }
        }
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        try {
            webElement().sendKeys(keysToSend);
        } catch (Exception handleRetry) {
            try {
                waitFor().visible();
                webElement().sendKeys(keysToSend);
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(),e);
            }
        }
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
    public String getHTML() {
        return null;
    }

    @Override
    public void dragAndDropTo(Element target) {

    }

    @Override
    public void dragWaitAndDrop(Element target, ElementCondition condition) throws IllegalStateException, TimeoutException, InterruptedException {

    }



    @Override
    public boolean exists() throws IllegalStateException {
        return false;
    }

    @Override
    public void doMouseOver() {

    }

    @Override
    public Waiter waitFor(long timeout) {
        return null;
    }

    @Override
    public Waiter waitFor(long timeout, long pollDelay) {
        return null;
    }

    @Override
    public void controlClick() {

    }

    @Override
    public void hoverOnCanvas(int x, int y) {

    }

    @Override
    public String getTagName() {
        try {
            return webElement().getTagName();
        } catch (Exception hadleRetry) {
            try {
                waitFor().exists();
                return  webElement().getTagName();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(),e);
            }
        }
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


    /* ---------------------- Special method --------------------------------*/

    public void clickAndHold() {
        try {
            new Actions(browser.getWebDriver()).clickAndHold(webElement()).perform();
        } catch (Exception handleRetry) {
            try {
                waitFor().exists();
                new Actions(browser.getWebDriver()).clickAndHold(webElement()).perform();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }


}
