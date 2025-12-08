package com.journalingapp.soham.journalApp.controller;

import com.journalingapp.soham.journalApp.entity.User;
import com.journalingapp.soham.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok" ;
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
//        userService.saveNewUser(user);
    }
}
