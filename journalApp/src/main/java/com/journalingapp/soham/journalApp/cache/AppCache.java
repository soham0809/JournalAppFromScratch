package com.journalingapp.soham.journalApp.cache;


import com.journalingapp.soham.journalApp.entity.ConfigJournalAppEntity;
import com.journalingapp.soham.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API ;
    }
    public Map<String , String > appCache ;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

    }
}
// frequently used and frequently changing files ko db mai rakh sakte hai , but this
// will increase the number of callls
// solution caching