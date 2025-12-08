package com.journalingapp.soham.journalApp.controller;


import com.journalingapp.soham.journalApp.entity.JournalEntry;
import com.journalingapp.soham.journalApp.entity.User;
import com.journalingapp.soham.journalApp.service.JournalEntryService;
import com.journalingapp.soham.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    //done
//    @GetMapping("/{userName}")
//    public ResponseEntity<?> getAllJournalEntrinesOfUser(@PathVariable String userName){
//        System.out.println("Debug1");
//        User user = userService.findByUserName(userName);
//        System.out.println("Debug2");
//        List<JournalEntry> all = user.getJournalEntries() ;
//        System.out.println("Debug3");
//        if(all!= null && !all.isEmpty()){
//            System.out.println("Debug4");
//            return new ResponseEntity<>(all , HttpStatus.OK);
//        }
//        System.out.println("Debug5");
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
// //as above is not secure , anyone can access anyone's journal entries
//
    @GetMapping()
    public ResponseEntity<?> getAllJournalEntrinesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        if(user == null){
            return new ResponseEntity<>("User not found ",HttpStatus.NOT_FOUND);
        }
        List<JournalEntry> all = user.getJournalEntries() ;
        if(all== null && all.isEmpty()){
            return new ResponseEntity<>("No journal entries found",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all , HttpStatus.OK);

    }
    //done
    @PostMapping()
     public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
//            myEntry.setDate(LocalDateTime.now());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e ){
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }


    //done
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(! collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND) ;

    }


    //done
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        boolean removed = journalEntryService.deleteById(myId, userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //doing
    //old
//    @PutMapping("id/{myId}")
//    public ResponseEntity   <?> updateJournalEntryById(@PathVariable ObjectId myId , @RequestBody JournalEntry updatedEntry , @PathVariable String userName){
//
//        JournalEntry old = journalEntryService.findById(myId).orElse(null);
//
//        if(old != null){
//            old.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ? updatedEntry.getTitle()  : old.getTitle());
//            old.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ?  updatedEntry.getContent() : old.getContent());
//            journalEntryService.saveEntry(old);
//            return new ResponseEntity<>(old , HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}




