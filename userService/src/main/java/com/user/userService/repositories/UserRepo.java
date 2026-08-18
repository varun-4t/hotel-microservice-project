package com.user.userService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.userService.entities.User;

public interface UserRepo extends JpaRepository<User,String>{

}
