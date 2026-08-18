package com.user.userService.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private String hotelId;
    private String hotelName;
    private String location;
    private String about;
}