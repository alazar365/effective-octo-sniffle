package com.alexspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse("Hello");
//    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer updatedCustomer){


        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("student with id " + id + " does not exist"));
        customer.setAge(updatedCustomer.getAge());
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());

        customerRepository.save(customer);

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/greet")
    public GreetResponseRecord greet(){
        return new GreetResponseRecord(
                "Hello",
                List.of("Java", "C#", "Python"),
                new Person("Alex", 28, 30_000));
    }

    record Person (String name, int age, double savings){

    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}

    record GreetResponseRecord(
                String greet,
                List<String> favProgrammingLanguages,
                Person person) {}
}
