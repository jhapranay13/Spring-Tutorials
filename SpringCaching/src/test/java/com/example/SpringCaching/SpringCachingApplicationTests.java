package com.example.SpringCaching;

import com.example.SpringCaching.entities.MyEntity;
import com.example.SpringCaching.services.MyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@EnableCaching
@SpringBootTest
class SpringCachingApplicationTests {

	@Autowired
	MyService myService;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveTest() {
		long startTime = System.currentTimeMillis();
		MyEntity entity = myService.getById(1);
		long endTime = System.currentTimeMillis();
		System.out.println("Fetching from Store in >> " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		entity = myService.getById(1);
		endTime = System.currentTimeMillis();
		System.out.println("Fetching from Cache in >> " + (endTime - startTime));
	}

	@Test
	public void updateTest() {
		MyEntity entity = myService.getById(1);
		entity.setName("Jack");

		long startTime = System.currentTimeMillis();
		myService.updateData(entity);
		long endTime = System.currentTimeMillis();
		System.out.println("Updating from Store in >> " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		entity = myService.getById(1);
		endTime = System.currentTimeMillis();
		System.out.println("Getting from Cache in >> " + (endTime - startTime));
	}

	@Test
	public void deleteTest() {

		long startTime = System.currentTimeMillis();
		myService.deleteData(1);
		long endTime = System.currentTimeMillis();
		System.out.println("Deleting from Store in >> " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		MyEntity entity = myService.getById(2);
		endTime = System.currentTimeMillis();
		System.out.println("Getting from Store in >> " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		entity = myService.getById(2);
		endTime = System.currentTimeMillis();
		System.out.println("Getting from cache in >> " + (endTime - startTime));
	}
}
