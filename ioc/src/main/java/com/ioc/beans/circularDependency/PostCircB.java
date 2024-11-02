package com.ioc.beans.circularDependency;

import org.springframework.stereotype.Component;

@Component
public class PostCircB {
    private PostCircA postCircA;

    public void setPostCircA(PostCircA postCircA) {
        System.out.println("PostCircB Setting beanA");
        this.postCircA = postCircA;
    }
}
