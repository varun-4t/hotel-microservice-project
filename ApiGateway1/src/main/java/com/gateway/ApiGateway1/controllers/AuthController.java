package com.gateway.ApiGateway1.controllers;

import ch.qos.logback.core.model.Model;
import com.gateway.ApiGateway1.models.AuthResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model
    ){
        logger.info("User email id: {}",user.getEmail());
        //creating auth response object
        AuthResponse authResponse = new AuthResponse();
        //setting email to auth response
        authResponse.setUserId(user.getEmail());
        //setting token to auth response
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());
        assert client.getRefreshToken() != null;
        authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        authResponse.setExpireAt(
                client.getAccessToken().getExpiresAt() != null
                        ? client.getAccessToken().getExpiresAt().getEpochSecond()
                        : 0
        );
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        authResponse.setAuthorise(authorities);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
