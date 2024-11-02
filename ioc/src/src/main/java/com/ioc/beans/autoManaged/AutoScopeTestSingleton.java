package com.ioc.beans.autoManaged;

import com.ioc.beans.userManaged.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoScopeTestSingleton {

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
