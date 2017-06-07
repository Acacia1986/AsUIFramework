package com.acacia.waits;

import com.acacia.common.Configuration;
import com.acacia.myProduct.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.util.concurrent.TimeoutException;

/**
 * This class is provided as a means to wait for various conditions to occur in the UI
 * There are two configured properties that control the behavior of this class around delays between polling events
 * and how long before a
 * TimeoutException is thrown.
 * Configured Property Name:waiter.delay    The amount of time between polling checking for the EventCondition.
 * waiter.timeout The amount of time to before a
 * Created by miaomiao on 6/4/2017.
 */
public final class Waiter {
    public static Logger logger = LoggerFactory.getLogger(Waiter.class);
    private static final String WAITER_DELAY_KEY = "waiter.delay";
    private static final String WAITER_TIMOUT_KEY = "waiter.timeout";

    private final Element element;



    private final long delay;
    private final long timeout;
    private final Boolean desiredOccurrence;



    public Waiter(Element element,long delay, long timeout){
        this.element = element;
        this.delay = delay;
        this.timeout = timeout;
        desiredOccurrence = true;
    }

    public Waiter(Element element){
        this(element,Waiter.getDefaultDelay(),Waiter.getDefaultTimeout());
    }


    public long getDelay() {
        return delay;
    }

    public long getTimeout() {
        return timeout;
    }

    /**
     * the default is 1000 milliseconds
     * @return
     */
    public static long getDefaultDelay(){
        int defaultDelay = 1000;

        try {
            return Configuration.getInt(WAITER_DELAY_KEY,defaultDelay);
        } catch (ConfigurationException e) {
            logger.warn(String.format("error occured when configuration with key[%s].Using delay value: %s",WAITER_DELAY_KEY,defaultDelay));
            logger.warn(e.getMessage());
            return defaultDelay;
        }
    }

    public static long getDefaultTimeout(){
        int defaultTimeout = 30000;
        try {
            return Configuration.getInt(WAITER_TIMOUT_KEY,defaultTimeout);
        } catch (ConfigurationException e) {
            logger.warn(String.format("error occured when configuration with key[%s]. Using timeout value: %s",WAITER_TIMOUT_KEY,defaultTimeout));
            logger.warn(e.getMessage());
            return defaultTimeout;
        }
    }

    /**
     * wait for condition part
     * @param condition
     * @param delay
     * @param timeout
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public static void waitForConditon(ElementCondition condition,long delay,long timeout) throws TimeoutException, InterruptedException {
        new Waiter(null).waitFor(condition,delay,timeout);
    }

    /**
     * wait for condition
     * @param condition
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public static void waitForCondition(ElementCondition condition) throws TimeoutException, InterruptedException {
        new Waiter(null).waiFor(condition);
    }


    /**
     * wait for part
     * @param condition
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void waiFor(ElementCondition condition) throws TimeoutException, InterruptedException {
        waitFor(condition,delay,timeout);
    }

    /**
     * wait for
     * @param condition
     * @param delay
     * @param timeout
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public void waitFor(ElementCondition condition,long delay,long timeout) throws InterruptedException, TimeoutException {
        long strat = System.currentTimeMillis();
        do{
            if (condition.occurred() == desiredOccurrence) {
                return;
            }else {
                long startNano = System.currentTimeMillis();
                Thread.sleep(delay);
                long endNano = System.currentTimeMillis();
                if ((endNano - startNano)< delay){
                    Thread.sleep(delay - (endNano - startNano));
                }
            }
        }while(timeout < 0 || System.currentTimeMillis() - strat <= timeout);
    throw  new TimeoutException(String.format("Timed out waiting for [%s] to be [%s].",condition,desiredOccurrence));
    }


    /**
     * element visible or not
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void visible() throws TimeoutException, InterruptedException {
        waitFor(new ElementVisible(element),delay,timeout);
    }

    /**
     * Element exist or not
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void exists() throws TimeoutException, InterruptedException {
        waitFor(new ElementExistence(element),delay,timeout);
    }

    /**
     * The method want to wait until find the Element's attribute contains all of the values
     * @param attr
     * @param value
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void attributeContainsAll(String attr, CharSequence... value) throws TimeoutException, InterruptedException {
        waitFor(new ElementAttributeContainsAll(element,attr,value),delay,timeout);
    }

    /**
     * The method want to wait until find Element's attribute contains one of the values
     * @param attr
     * @param value
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void attributeContainsOne(String attr, CharSequence... value) throws TimeoutException, InterruptedException {
        waitFor(new ElementAttributeContainsOne(element,attr,value),delay,timeout);

    }


}
