package com.user.userService.external.services;

import com.user.userService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("HOTELSERVICE")
public interface HotelService
{
    @GetMapping("/hotels/{hotelid}")
    Hotel getHotel(@PathVariable String hotelid);
}
