package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

// Entity and ID in crudRepository
//public interface ProductRepository extends CrudRepository<Product, Integer> {
public interface ProductRepository extends CrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

    public List<Product> findByNameAndDesc(String name, String desc);
    public List<Product> findByPriceGreaterThan(double amount);
    public List<Product> findByDescContains(String contains);
    public List<Product> findByPriceBetween(double to, double from);
    public List<Product> findByNameLike(String like);
    public List<Product> findByNameLike(String like, Pageable pageable);

    //public List<Product> findByDescContains(String contains);
}
