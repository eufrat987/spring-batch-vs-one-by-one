# Batch sql vs Iterative sql

Project tests time execution using spring batch update and one by one update.
Both operations updates first name of 100 000 persons.


# Result
Example run:


```
Created 100000 persons.
2025-05-16T18:46:25.475+02:00  INFO 90860 --- [           main] org.example.Main                         : Started Main in 5.429 seconds (process running for 5.76)
Test Bath
Test One by One
StopWatch '': 1.982128715 seconds
----------------------------------------
Seconds       %       Task name
----------------------------------------
0.508018339   26%     Batch test
1.474110376   74%     One by one test

2025-05-16T18:46:27.478+02:00  INFO 90860 --- [           main] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-05-16T18:46:27.480+02:00  INFO 90860 --- [           main] o.s.j.d.e.EmbeddedDatabaseFactory        : Shutting down embedded database: url='jdbc:hsqldb:mem:test'

Process finished with exit code 0
```

# Observation

Batch update is at least 2 times faster.

# Why?

...
