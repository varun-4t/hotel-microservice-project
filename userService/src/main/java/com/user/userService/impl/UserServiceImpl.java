package com.user.userService.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.user.userService.entities.Rating;
import com.user.userService.entities.Hotel;
import com.user.userService.external.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user.userService.entities.User;
import com.user.userService.exceptions.ResourceNotFound;
import com.user.userService.services.UserServices;
import com.user.userService.repositories.UserRepo;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserServices{

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate; //this configuration bean is created in UserServiceApplication.java

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString(); //unique user id in the form of string
		user.setUserId(randomUserId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = userRepo.findAll();
		//implementing rating service
		for (User user:users) {
			ArrayList forObject = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(), ArrayList.class);
			user.setRatings(forObject);
		}
		return users;
	}

	@Override
	public User getUser(String userId){
        try {
			User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User with given ID is not found"));
			//fetch rating of the above user from rating service
			//we'll communicate with rating service via http APIs
			//whenever 1 service communicate with other service it communicates via some http client

			Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/"+user.getUserId(), Rating[].class);

			List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
			logger.info(String.valueOf(ratingOfUser));

			List<Rating> ratingList = ratings.stream().map(rating -> {
//			//api call to get hotel service
//			//http://localhost:8082/hotels/55ffc763-bf41-47b8-a95a-c398754ab14b
Hotel hotel = hotelService.getHotel(rating.getHotelId());
//				ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//				Hotel hotel = forEntity.getBody();
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());

			user.setRatings(ratingList);

			return user;
		} catch (ResourceNotFound e) {
            throw new RuntimeException(e);
        }
    }

}
