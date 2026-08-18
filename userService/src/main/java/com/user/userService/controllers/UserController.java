package com.user.userService.controllers;

import com.user.userService.entities.User;
import com.user.userService.services.UserServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 =  userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userServices.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(@PathVariable String userId, Exception ex){
        logger.error("Fallback executed because service is down", ex);
        logger.info("Fallback executed bcoz service is down {}", ex.getMessage());
        User user= User.builder().name("Bholu").email("b@gmail.com").about("nice guy").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
//    @Retry(name="ratingHotelService",fallbackMethod="ratingHotelFb")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFb")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userServices.getAllUser();
        return ResponseEntity.ok(allUser);
    }

//    int retryCount=1;

    public ResponseEntity<List<User>> ratingHotelFb( Exception ex){
//        logger.info("retry count {}", retryCount);
//        retryCount++;
        logger.error("Fallback executed because service is down", ex);
        List<User> list = new ArrayList<>();
        User user= User.builder().name("dummy").email("dummy@gmail.com").about("this is a dummy user, bcoz service is down").build();
        list.add(user);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
}