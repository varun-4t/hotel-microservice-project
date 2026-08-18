package com.rating.RatingService.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) {

        System.out.println("URI = " +
                environment.getProperty("spring.data.mongodb.uri"));

        System.out.println("Database from template = "
                + mongoTemplate.getDb().getName());

        System.out.println("Factory = "
                + mongoTemplate.getMongoDatabaseFactory());

    }
}