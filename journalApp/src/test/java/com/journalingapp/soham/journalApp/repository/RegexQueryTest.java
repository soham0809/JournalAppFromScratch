package com.journalingapp.soham.journalApp.repository;

import com.journalingapp.soham.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
public class RegexQueryTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void debugUserQuery() {
        System.out.println("--- DEBUG STARTS HERE ---");

        // 1. Check total users
        List<User> allUsers = mongoTemplate.findAll(User.class);
        System.out.println("Total users in DB: " + allUsers.size());

        // 2. Print details of all users to verify fields
        for (User user : allUsers) {
            System.out.println("User: " + user.getUserName());
            System.out.println("  Email: '" + user.getEmail() + "'");
            System.out.println("  SentimentAnalysis: " + user.isSentimentAnalysis());
        }

        // 3. Test exact email match (no regex)
        Query exactQuery = new Query();
        exactQuery.addCriteria(Criteria.where("email").is("sohamj69@gmail.com"));
        List<User> exactMatch = mongoTemplate.find(exactQuery, User.class);
        System.out.println("Exact match count: " + exactMatch.size());

        // 4. Test the repository method
        List<User> regexResult = userRepository.getUsersForSA();
        System.out.println("Repository method result count: " + regexResult.size());

        System.out.println("--- DEBUG ENDS HERE ---");
    }
}
