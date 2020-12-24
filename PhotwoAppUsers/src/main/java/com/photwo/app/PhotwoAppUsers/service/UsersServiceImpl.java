package com.photwo.app.PhotwoAppUsers.service;

import com.photwo.app.PhotwoAppUsers.model.Album;
import com.photwo.app.PhotwoAppUsers.model.User;
import com.photwo.app.PhotwoAppUsers.model.UserResponseModel;
import com.photwo.app.PhotwoAppUsers.repository.UserRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    // RestTemplate restTemplate;
    AlbumServiceClient albumServiceClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AlbumServiceClient albumServiceClient) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.albumServiceClient = albumServiceClient;
    }

    public void createUser(User user) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        if (user == null) { throw new UsernameNotFoundException(email); }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByEmail(s);
        return new org.springframework.security.core.userdetails.
                User(user.email, user.password, true, true,
                true, true, new ArrayList<>());
    }

    public UserResponseModel getUserDetails(String userId) {
        User user = userRepository.findByUserId(userId);
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setEmail(user.getEmail());
        userResponseModel.setFirstName(user.getFirstName());
        userResponseModel.setLastName(user.getLastName());
        userResponseModel.setUserId(user.getUserId());

        try {
            userResponseModel.setAlbums(albumServiceClient.getAlbums(userId));
        } catch (FeignException.FeignClientException e) {
            logger.error(e.getLocalizedMessage());
        }

        return userResponseModel;
    }
}
