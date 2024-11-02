package com.ioc.beans.userManaged;

import org.springframework.beans.factory.annotation.Autowired;

public class ScopeTestSingleton {

    @Autowired
    Game somebean;

    public String getSomeBeanText(){
        return somebean.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ScopeTest{}";
    }
}
