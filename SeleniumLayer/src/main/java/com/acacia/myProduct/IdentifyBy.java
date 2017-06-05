package com.acacia.myProduct;

import org.openqa.selenium.By;

/**
 * Created by miaomiao on 6/5/2017.
 */
public abstract class IdentifyBy {

    private IdentifyBy(){}//Disable instantiation

    public static final Identifier ID = new Identifier() {
        private  By by;
        @Override
        public By identify(Element elem) {
            by = By.id(elem.getAttribute("id"));
            return by;
        }
        @Override
        public String toString(){
            return  this.by != null ? this.by.toString():"Id:undefined until the persist have been called";
        }
    };

    public static final Identifier ClassName = new Identifier() {
        private By by = null;
        @Override
        public By identify(Element elem) {
            this.by = By.className(elem.getAttribute("class"));
            return by;
        }
        @Override
        public String toString(){
            return this.by !=null ? this.by.toString():"ClassName: undefined until persist have been called";
        }
    };

    public static final Identifier Name = new Identifier() {
        private By by = null;
        @Override
        public By identify(Element elem) {
            this.by = By.name(elem.getAttribute("name"));
            return by;
        }
        @Override
        public  String toString(){
            return  this.by != null ? this.by.toString():"Name: undefined until persist have been called";
        }
    };

    public static final Identifier PartialLinkText = new Identifier() {
        private  By by = null;
        @Override
        public By identify(Element elem) {
            this.by = By.partialLinkText(elem.getText());
            return null;
        }
        @Override
        public  String toString(){
            return this.by !=null ? this.by.toString():"PartialLinkText: undefined until persist have been called";
        }
    };
}
