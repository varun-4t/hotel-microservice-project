package com.hotel.HotelService.service;

import com.hotel.HotelService.entities.Hotel;
import java.util.*;

public interface HotelServices {
public Hotel createHotel(Hotel hotel);
public Hotel getHotel(String hotelId);
public List<Hotel> getAllHotel();
}
