package com.rating.RatingService.repositories;

import com.rating.RatingService.entities.RatingService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<RatingService,String> {
    List<RatingService> findByUserId(String UserId);
    List<RatingService> findByHotelId(String hotelId);
}
