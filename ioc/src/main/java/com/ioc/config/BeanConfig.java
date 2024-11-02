package com.ioc.config;

import com.ioc.beans.userManaged.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value = {"com.ioc.beans.autoManaged", "com.ioc.beans.circularDependency"})
public class BeanConfig {

    @Bean
    public String getName() {
        return "Pranay";
    }

    @Bean
    public BeanOne getBeanOne() {
        return new BeanOne();
    }

    @Bean(name = "getBeanOneWithDiffNam")
    public BeanOne getBeanOneWithDifferentName() {
        return new BeanOne();
    }

    @Bean
    @Primary
    public Game getGameOne() {
        return new GameOne();
    }

    @Bean
    @Qualifier(value = "getGameTwoQualifier")
    public Game getGameTwo() {
        return new GameTwo();
    }

    @Bean
    public GameContainer getGameContainerOne(Game game) {
        return new GameContainer(game);
    }

    @Bean
    public GameContainer getGameContainerTwo(@Qualifier("getGameTwoQualifier") Game game) {
        return new GameContainer(game);
    }

    @Bean
    public ScopeTestSingleton getSingleTonScope() {
        return new ScopeTestSingleton();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ScopeTestPrototype getPrototype() {
        return new ScopeTestPrototype();
    }
}
