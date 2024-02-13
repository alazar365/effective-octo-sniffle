package com.alexspring.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer.NewCustomerRequest request){
        customerService.addCustomer(new Customer(request));
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer updatedCustomer){
        return customerService.updateCustomer(id, updatedCustomer);
    }
}
