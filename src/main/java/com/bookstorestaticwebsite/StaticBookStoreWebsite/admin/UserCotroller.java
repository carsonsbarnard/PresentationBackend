package com.bookstorestaticwebsite.StaticBookStoreWebsite.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserCotroller {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "admin/index";
    }



    @GetMapping("/all")
    public String getAllUsers(Model model){
        List<User> user_list =  userService.getAllUsers();
        model.addAttribute("userList",user_list);
        model.addAttribute("title", "User List");
        return  "admin/user-list";
    }

    @GetMapping("/createUserForm")
    public String createNewUser(Model model){
        model.addAttribute("title", "Create New User");
        return "admin/user-form";
    }

    @PostMapping("/createUser")
    public  String createNewUser(@ModelAttribute("user") User user){
        userService.createNewUser(user);
        return "redirect:/admin/all";
    }

    @PostMapping("/edit/{userId}")
    public String updateUser(@PathVariable long userId, @ModelAttribute User user) {
        userService.updateUser(user, userId);
        return "redirect:/admin/all";
    }


    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable long userId, Model model){
        //Get user by and display it first
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("title", "Edit User");
        return "admin/user-edit";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable long userId){
        userService.deleteUserById(userId);
        return "redirect:/admin/all";
    }





}
