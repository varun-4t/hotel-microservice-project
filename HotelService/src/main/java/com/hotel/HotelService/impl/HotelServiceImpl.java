package com.hotel.HotelService.impl;

import com.hotel.HotelService.Exceptions.ResourceNotFound;
import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.repositories.HotelRepository;
import com.hotel.HotelService.service.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelServices {
    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setHotelId(randomId);
        Hotel hotel1 = hotelRepo.save(hotel);
        return hotel1;
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFound("Hotel with given ID not found"));
    }

    @Override
    public List<Hotel> getAllHotel() {
        List<Hotel> hotels = hotelRepo.findAll();
        return hotels;
    }
}
