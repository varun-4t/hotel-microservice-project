package com.rating.RatingService.controllers;

import com.rating.RatingService.entities.RatingService;
import com.rating.RatingService.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RequestMapping("/ratings")
@RestController
public class RatingController {
    @Autowired
    public RatingServices ratingServices;

    @PostMapping
    public ResponseEntity<RatingService> createRating(@RequestBody RatingService ratingService){
        System.out.println("Received: " + ratingService);
        RatingService r = ratingServices.create(ratingService);
        System.out.println("Saved: " + r);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @GetMapping
    public ResponseEntity<List<RatingService>> getRating(){
        return ResponseEntity.ok(ratingServices.getAllRatings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingService>> getUserRating(@PathVariable String userId){
        return ResponseEntity.ok(ratingServices.getAllByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingService>> getRating(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingServices.getAllByHotelId(hotelId));
    }

}
