package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("From Employee")
    public List<Employee> findAllEmployee();

    @Query("SELECT e.fName, e.lName From Employee e")
    public List<Object[]> findAllEmployeePartial();

    // Can also be used for update and delete statements
    // for that we should use @Transactional
    @Query("From Employee where fName=:firstName")
    public List<Employee> findAllEmployeeByFirstName(@Param("firstName") String firstName);

    // Can be used to call stored Procedure
    @Query(value = "select * From employee where f_name=:firstName", nativeQuery = true)
    public List<Employee> findAllEmployeeByFirstNameNQ(@Param("firstName") String firstName);
}
