package com.photwo.app.PhotwoAppUsers.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photwo.app.PhotwoAppUsers.model.LoginRequestModel;
import com.photwo.app.PhotwoAppUsers.service.UsersServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.User;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.security.Key;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UsersServiceImpl usersServiceImpl;
    private Environment environment;

    public AuthenticationFilter(UsersServiceImpl usersServiceImpl, Environment environment, AuthenticationManager authenticationManager) {
        this.usersServiceImpl = usersServiceImpl;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            LoginRequestModel requestModel = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
            return getAuthenticationManager().
                    authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(),
                            requestModel.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String userName = ((UserDetails) authResult.getPrincipal()).getUsername();
        String userId = this.usersServiceImpl.findByEmail(userName).getUserId();
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration"))))
                .signWith(key)
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userId);
    }
}
