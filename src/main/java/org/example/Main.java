package org.example;

import org.example.data.JdbcPersonDao;
import org.example.model.Person;
import org.example.testscenario.update.BatchUpdateTimeMeasure;
import org.example.testscenario.update.OneByOneUpdateTimeMeasure;
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

        var personDao = context.getBean(JdbcPersonDao.class);

        var somePerson = personDao.getPerson(1234);
        assert somePerson.getFirstName().equals("John 1234") : "Should be created and set.";

//        {
//            System.out.println("Test Bath");
//            var batch = context.getBean(BatchUpdateTimeMeasure.class);
//            batch.setNewFirstName("Batch");
//            batch.test();
//        }
//
//        somePerson = personDao.getPerson(1234);
//        assert somePerson.getFirstName().equals("Batch") : "Should be updated.";

        {
            System.out.println("Test One by One");
            var oneByOne = context.getBean(OneByOneUpdateTimeMeasure.class);
            oneByOne.setNewFirstName("One");
            oneByOne.test();
        }

        somePerson = personDao.getPerson(1234);
        assert somePerson.getFirstName().equals("One") : "Should be updated.";

        SpringApplication.exit(context);
    }
}