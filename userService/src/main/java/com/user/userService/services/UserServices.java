package com.user.userService.services;

import com.user.userService.entities.User;
import com.user.userService.exceptions.ResourceNotFound;

import java.util.*;

public interface UserServices {
User saveUser(User user);
List<User> getAllUser();
User getUser(String userId);
}