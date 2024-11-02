package com.ioc.ioc;

import com.ioc.beans.autoManaged.AutoGame;
import com.ioc.beans.autoManaged.AutoScopeTestPrototype;
import com.ioc.beans.autoManaged.AutoScopeTestSingleton;
import com.ioc.beans.userManaged.Game;
import com.ioc.beans.userManaged.ScopeTestPrototype;
import com.ioc.beans.userManaged.ScopeTestSingleton;
import com.ioc.config.BeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTutorialStarter {

    public static void main(String args[]) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        // Using Configuration to Declare the beans and Autowiring
        System.out.println(context.getBean("getName"));
        System.out.println(context.getBean("getBeanOne"));
        System.out.println(context.getBean("getBeanOneWithDiffNam"));
        System.out.println(context.getBean("getGameOne"));
        System.out.println(context.getBean("getGameTwo"));
        System.out.println(context.getBean(Game.class));
        System.out.println(context.getBean("getGameContainerOne"));
        System.out.println(context.getBean("getGameContainerTwo"));
        ScopeTestSingleton singleton = (ScopeTestSingleton) context.getBean("getSingleTonScope");
        System.out.println(singleton.getSomeBeanText() + " " + singleton + " " + singleton.hashCode() );
        singleton = (ScopeTestSingleton) context.getBean("getSingleTonScope");
        System.out.println(singleton.getSomeBeanText() + " " + singleton + " " + singleton.hashCode() );
        ScopeTestPrototype proto = (ScopeTestPrototype) context.getBean("getPrototype");
        System.out.println( proto + " " + proto.hashCode() );
        proto = (ScopeTestPrototype) context.getBean("getPrototype");
        System.out.println( proto + " " + proto.hashCode() );
        System.out.println("===================================================================================");
        // Self Management
        System.out.println(context.getBean("autoBeanOne"));
        System.out.println(context.getBean("autoGameOne"));
        System.out.println(context.getBean("autoGameTwo"));
        System.out.println(context.getBean(AutoGame.class));
        System.out.println(context.getBean("autoGameContainer"));
        AutoScopeTestSingleton autoSingleton = (AutoScopeTestSingleton) context.getBean("autoScopeTestSingleton");
        System.out.println(autoSingleton.getSomeBeanText() + " " + autoSingleton + " " + autoSingleton.hashCode() );
        autoSingleton = (AutoScopeTestSingleton) context.getBean("autoScopeTestSingleton");
        System.out.println(autoSingleton.getSomeBeanText() + " " + autoSingleton + " " + autoSingleton.hashCode() );
        AutoScopeTestPrototype protoAuto = (AutoScopeTestPrototype) context.getBean("autoScopeTestPrototype");
        System.out.println( protoAuto + " " + protoAuto.hashCode() );
        protoAuto = (AutoScopeTestPrototype) context.getBean("autoScopeTestPrototype");
        System.out.println( protoAuto + " " + protoAuto.hashCode() );

        context.close();
    }
}
