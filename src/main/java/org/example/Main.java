package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class);

        // Query to test: update t_person set first_name = ?, last_name = ? where id = ?
        // We measure here time to complete whole update operation
        // 1. Update rows one by one
        // 2. Update rows using batch
    }
}