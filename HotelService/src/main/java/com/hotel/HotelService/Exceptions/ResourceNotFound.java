package com.hotel.HotelService.Exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String s) {
        super(s);
    }
    public ResourceNotFound() {
        super("Resource not Found");
    }
}
