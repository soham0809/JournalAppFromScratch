package com.journalingapp.soham.journalApp.service;

import com.journalingapp.soham.journalApp.entity.User;
import com.journalingapp.soham.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


//    @Test
//    public void findByUserName(){
////        assertEquals(4 , 2+2);
//
//        User byUserName = userRepository.findByUserName("Soham");
////        assertNotNull( userRepository.findByUserName("Soham"));
//        assertTrue(!byUserName.getJournalEntries().isEmpty());
//    }


//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Soham",
//            "Soham1",
//            "Soham2"
//    })
//    public void testFindByUserName(String name){
//        assertNotNull(userRepository.findByUserName(name));
//    }



    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser (User user ){
//        assertNotNull(userRepository.findByUserName(name));
        assertTrue(userService.saveNewUser(user));
    }

//    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a , int b ,int expected){
        assertEquals(expected , a+b);
    }
}

