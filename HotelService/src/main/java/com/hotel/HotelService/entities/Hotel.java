package com.hotel.HotelService.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    private String hotelId;
    private String hotelName;
    private String location;
    private String about;
}
