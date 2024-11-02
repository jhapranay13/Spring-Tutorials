package com.ioc.beans.circularDependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ImplCircA implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;
    private ImplCircB beanB;

    @Override
    public void afterPropertiesSet() throws Exception {
        // InitializingBean
        System.out.println("Setting bean B in A");
        beanB = applicationContext.getBean(ImplCircB.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // ApplicationContextAware
        this.applicationContext = applicationContext;
    }
}
