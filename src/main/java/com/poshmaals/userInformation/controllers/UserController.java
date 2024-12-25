package com.poshmaals.userInformation.controllers;

import com.poshmaals.userInformation.payloads.requests.UserInformationRequest;
import com.poshmaals.userInformation.payloads.responses.UserInformationResponse;
import com.poshmaals.userInformation.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

//    private static final Logger log = LogManager.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserInformationRequest user) {
        log.debug("User recieved in the controller: {}" + user);
        userService.addUser(user);
        log.debug("POST: User added successfully");
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserInformationResponse>> getAllUsers() {
        log.debug("GET: Fetching all users");
//        return ResponseEntity.ok(userService.getAllUsers());
        List<UserInformationResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }

}
