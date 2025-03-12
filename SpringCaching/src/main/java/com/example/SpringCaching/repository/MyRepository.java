package com.example.SpringCaching.repository;

import com.example.SpringCaching.entities.MyEntity;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Repository
public class MyRepository {
    private Map<Integer, MyEntity> store = new ConcurrentHashMap<>();
    private Faker faker = Faker.instance();

    private MyRepository() {
        this.store.put(1, new MyEntity(1, faker.name().firstName(), faker.commerce().productName()));
        this.store.put(2, new MyEntity(1, faker.name().firstName(), faker.commerce().productName()));
        this.store.put(3, new MyEntity(1, faker.name().firstName(), faker.commerce().productName()));
        this.store.put(4, new MyEntity(1, faker.name().firstName(), faker.commerce().productName()));
        this.store.put(5, new MyEntity(1, faker.name().firstName(), faker.commerce().productName()));
    }

    public void save(MyEntity entity) {
        this.store.put(entity.getId(), entity);
    }

    public MyEntity delete(int id) {
        return this.store.remove(id);
    }

    public MyEntity get(int id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.store.get(id);
    }
}
