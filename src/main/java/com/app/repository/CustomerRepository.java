package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Customer;
import java.util.List;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
 Customer findByEmail(String email);
 List<Customer> findAll();
   void deleteByEmail(String email);
}
