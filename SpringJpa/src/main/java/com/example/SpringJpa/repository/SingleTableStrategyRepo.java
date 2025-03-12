package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.inheritancestrategy.MyProduct;
import org.springframework.data.repository.CrudRepository;

public interface SingleTableStrategyRepo extends CrudRepository<MyProduct, Integer> {

}
