package com.user.userService.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="micro_user")
public class User {
	@Id
	@Column(name="ID")
	private String userId;
	
	@Column(name="Name",length=20)
	private String name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="About")
	private String about;

	@Transient
	private List<Rating> ratings;

}
