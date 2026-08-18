package com.user.userService.payload;

import org.springframework.http.HttpStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
	private String msg;
	private boolean success;
	private HttpStatus status;
}
