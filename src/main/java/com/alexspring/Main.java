package com.alexspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse("Hello");
//    }

    @GetMapping("/greet")
    public GreetResponseRecord greet(){
        return new GreetResponseRecord(
                "Hello",
                List.of("Java", "C#", "Python"),
                new Person("Alex", 28, 30_000));
    }

    record Person (String name, int age, double savings){

    }

    record GreetResponseRecord(
                String greet,
                List<String> favProgrammingLanguages,
                Person person) {}
}
