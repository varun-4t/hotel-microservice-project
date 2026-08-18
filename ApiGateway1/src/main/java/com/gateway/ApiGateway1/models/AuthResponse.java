package com.gateway.ApiGateway1.models;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse
{
    private String UserId;
    private String accessToken;
    private String refreshToken;
    private long expireAt;
    private Collection<String> authorise;

}
