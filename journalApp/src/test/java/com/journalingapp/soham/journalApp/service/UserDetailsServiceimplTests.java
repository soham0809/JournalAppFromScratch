package com.journalingapp.soham.journalApp.service;

import com.journalingapp.soham.journalApp.entity.User;
import com.journalingapp.soham.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
// in case of user repository , you need to use springboot test class
public class UserDetailsServiceimplTests {

    @InjectMocks // here autowired
    private UserDetailsServiceImpl userDetailsService ;

    @Mock       // here mockbean
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("shyam").password("shyam").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("shyam");
        Assertions.assertNotNull(user);

    }

}
