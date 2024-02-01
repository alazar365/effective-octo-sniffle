package com.alexspring.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer newCustomer){
        Customer customer = new Customer();
        customer.setName(newCustomer.getName());
        customer.setEmail(newCustomer.getEmail());
        customer.setAge(newCustomer.getAge());

        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }

    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer updatedCustomer){

        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("student with id " + id + " does not exist"));
        customer.setAge(updatedCustomer.getAge());
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());

        customerRepository.save(customer);

        return ResponseEntity.ok(customer);
    }
}
