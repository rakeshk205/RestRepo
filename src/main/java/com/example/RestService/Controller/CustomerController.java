package com.example.RestService.Controller;

import com.example.RestService.Model.Customer;

import com.example.RestService.Service.ServiceImpl;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Cust")
public class CustomerController {
    private final ServiceImpl service;

    public CustomerController(ServiceImpl service) {
        this.service = service;
    }
@PostMapping
public ResponseEntity<Customer> createCustomers(@RequestBody Customer c){
       // return ResponseEntity.ok(service.createCustomers(c));
    Customer cust=service.createCustomers(c);
    return ResponseEntity.status(HttpStatus.CREATED).body(cust);
}
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustoners(){

        return ResponseEntity.ok(service.getCustomers());
    }


}
