package com.rating.RatingService.impl;

import com.rating.RatingService.entities.RatingService;
import com.rating.RatingService.repositories.RatingRepo;
import com.rating.RatingService.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingImpl implements RatingServices {
    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public RatingService create(RatingService ratingService) {

        RatingService saved = ratingRepo.save(ratingService);

        System.out.println("ID after save = " + saved.getRatingId());

        System.out.println("Count = " + ratingRepo.count());

        return saved;
    }

    @Override
    public List<RatingService> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<RatingService> getAllByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<RatingService> getAllByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
