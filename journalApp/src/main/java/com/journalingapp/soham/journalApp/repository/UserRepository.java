package com.journalingapp.soham.journalApp.repository;
import com.journalingapp.soham.journalApp.entity.JournalEntry;
import com.journalingapp.soham.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}

