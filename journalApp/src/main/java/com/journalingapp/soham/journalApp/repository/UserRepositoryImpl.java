package com.journalingapp.soham.journalApp.repository;

import com.journalingapp.soham.journalApp.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    // we only want list of users , whose sentiment analysis is boolean s
    public List<User> getUsersForSA() {
        Query query = new Query();

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"
         ));
//        query.addCriteria(Criteria.where("email").is(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        // suppose you want to black list some user // what can I do extra
        // suppose you want to black list some user // what can I do extra
//        query.addCriteria(Criteria.where("userName").nin("Rajat"));


         // what can I do extra , role based
//        query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));
        /*
        *         query.addCriteria(criteria.orOperator(
                Criteria.where("email").is(true) ,
                Criteria.where("sentimentAnalysis").is(true)));
        * */


//        query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BSON_TYPES.))
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
