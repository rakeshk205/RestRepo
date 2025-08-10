package com.example.RestService.Service;


import com.example.RestService.Model.Customer;


import java.util.List;

public interface Service  {
    List<Customer> getCustomers();
    Customer createCustomers(Customer c);
}
