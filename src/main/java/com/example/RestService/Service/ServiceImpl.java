package com.example.RestService.Service;

import com.example.RestService.Model.Customer;
import com.example.RestService.Repo.CustomeRepo;



import java.util.List;
@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    private final CustomeRepo repo;

    public ServiceImpl(CustomeRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    @Override
    public Customer createCustomers(Customer c) {
        return repo.save(c);
    }


}
