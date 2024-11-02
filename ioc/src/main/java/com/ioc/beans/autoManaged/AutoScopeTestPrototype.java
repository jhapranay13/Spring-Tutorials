package com.ioc.beans.autoManaged;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AutoScopeTestPrototype {
    @Override
    public String toString() {
        return "ScopeTestPrototype{}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
