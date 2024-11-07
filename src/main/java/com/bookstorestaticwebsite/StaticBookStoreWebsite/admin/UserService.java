package com.bookstorestaticwebsite.StaticBookStoreWebsite.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){return userRepository.findAll();}

    public void createNewUser(User user){
        if(user != null){
            if(checkEmailExist(user)){
                System.out.println("Already exist");
                return;
            }
            user = new User(user.getEmail() , user.getPassword(), user.getFullName() );
            userRepository.save(user);
        }
    }
    public void updateUser(User user, long userId){
        User existing = getUserById(userId);
        if (checkEmailExist(existing)){
            System.out.println("Already exist");
            return;
        }
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setFullName(user.getFullName());
        userRepository.save(existing);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(long userId) { userRepository.deleteById(userId);
    }
    //check email already exists or not
    public boolean checkEmailExist(User user){
        boolean existed = false;
        List<User> list_user = getAllUsers();
        for (User u : list_user){
            if(user.getEmail().equals(u.getEmail())){
                existed = true;
                break;
            }
        }
        return existed;
    }




}
