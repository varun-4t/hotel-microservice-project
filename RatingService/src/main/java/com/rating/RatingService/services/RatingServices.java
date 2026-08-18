package com.rating.RatingService.services;

import com.rating.RatingService.entities.RatingService;
import org.springframework.stereotype.Service;
import java.util.*;

public interface RatingServices {
    public RatingService create(RatingService ratingService);
    public List<RatingService> getAllRatings();
    public List<RatingService> getAllByUserId(String userId);
    public List<RatingService> getAllByHotelId(String hotelId);
}
