package com.user.userService;

import com.user.userService.entities.Rating;
import com.user.userService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder().userId("54d704b9-6392-4e41-a49b-bdab5ecdc841").hotelId("55ffc763-bf41-47b8-a95a-c398754ab14b").rating(9).feedback("my dad loved the food here, that means this hotel is good").build();
		Rating rating1 = ratingService.createRating(rating);
		System.out.println("new rating created");
	}

}
