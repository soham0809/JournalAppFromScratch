package com.journalingapp.soham.journalApp.repository;
import com.journalingapp.soham.journalApp.entity.ConfigJournalAppEntity;
import com.journalingapp.soham.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}

