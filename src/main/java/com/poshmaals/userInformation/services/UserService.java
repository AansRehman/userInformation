package com.poshmaals.userInformation.services;

import com.poshmaals.userInformation.controllers.UserController;
import com.poshmaals.userInformation.mappers.UserMapper;
import com.poshmaals.userInformation.models.User;
import com.poshmaals.userInformation.payloads.requests.UserInformationRequest;
import com.poshmaals.userInformation.payloads.responses.UserInformationResponse;
import com.poshmaals.userInformation.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    public void addUser(UserInformationRequest userRequest) {
        System.out.println(userRequest);
//        log.info("User from the in the service: {}", userRequest);
        User user = userMapper.toUserInformation(userRequest);
        userRepository.save(user);
//        log.info("User added to database: {}", user);
    }

    public List<UserInformationResponse> getAllUsers() {
//        log.info("Fetching all users from database");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserInformationResponse)  // Mapping each user to a response DTO
                .collect(Collectors.toList());
    }
}

