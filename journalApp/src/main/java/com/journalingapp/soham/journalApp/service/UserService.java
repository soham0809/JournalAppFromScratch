    package com.journalingapp.soham.journalApp.service;

    import com.journalingapp.soham.journalApp.entity.User;
    import com.journalingapp.soham.journalApp.repository.UserRepository;
    import lombok.extern.slf4j.Slf4j;
    import org.bson.types.ObjectId;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.dao.DuplicateKeyException;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Component;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Optional;

    @Component
    @Slf4j
    public class UserService {

        @Autowired
        private UserRepository userRepository;

//        private final Logger logger = LoggerFactory.getLogger(UserService.class);

        private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        public boolean saveNewUser(User user){
            try{
                //extra code
                user.setPassword(passwordEncoder.encode(user.getPassword())) ;
                user.setRoles(Arrays.asList("USER"));
                //extra code end
                userRepository.save(user) ;
                return true ;
            }catch (DuplicateKeyException e){
                log.info("info logger");
                log.warn("warn logger");
                log.debug("debug logger");
                log.error("error logger");
                log.trace("trace logger");
                return false ;
            }
        }

        public void saveAdminUser(User user){
            //extra code
            user.setPassword(passwordEncoder.encode(user.getPassword())) ;
            user.setRoles(Arrays.asList("USER","ADMIN"));
            //extra code end
            userRepository.save(user) ;
        }

        public void saveUser(User user){
            userRepository.save(user) ;
        }

        public List<User> getAll(){
            return userRepository.findAll();
        }

        public Optional<User> findById(ObjectId id){
            return userRepository.findById(id);

        }

        public void deleteById(ObjectId id ){
            userRepository.deleteById(id);
        }

        public User findByUserName(String userName){
            return userRepository.findByUserName(userName);
        }

    }


    // controller ---> service ---> repository
    // class paths is just a list of jars and directories jo jvm use karta hai ,
    // jvm wants byte code and that byte code kahi pe ruka bhi to hoga
    // vo class path mai rakha hota hai