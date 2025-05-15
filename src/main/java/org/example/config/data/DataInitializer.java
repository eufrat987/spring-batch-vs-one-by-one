package org.example.config.data;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @PostConstruct
    public void init() {
        // Load data...
    }

}
