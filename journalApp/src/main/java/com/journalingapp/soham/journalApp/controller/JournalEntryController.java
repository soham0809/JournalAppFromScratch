package com.journalingapp.soham.journalApp.controller;


import com.journalingapp.soham.journalApp.JournalApplication;
import com.journalingapp.soham.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public Map<Long , JournalEntry> journalEntries = new HashMap<>();


    @GetMapping
    public List<JournalEntry> getAll(){

        return new ArrayList<>(journalEntries.values()) ;
    }

    @PostMapping
     public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }


    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable Long myId , @RequestBody JournalEntry myEntry){

        return journalEntries.put(myId , myEntry);

    }
}




