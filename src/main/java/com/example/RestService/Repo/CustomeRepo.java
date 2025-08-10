package com.example.RestService.Repo;

import com.example.RestService.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomeRepo extends JpaRepository<Customer,Long> {
}
