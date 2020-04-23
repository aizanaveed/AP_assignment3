package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String greeting()
    {
        return "Welcome to Facebook";
    }

    @PostMapping("/signup")
    String add(@RequestBody UserModel u)
    {
        return userService.suUser(u);
    }

    @GetMapping("/login")
    String log(@RequestBody UserModel u)
    {
        return userService.loguser(u);
    }

    @GetMapping("login/details")
    String deets(@RequestBody UserModel u)
    {
        return userService.details(u);
    }

    @PostMapping("/logout")
    String logout(@RequestBody UserModel u)
    {
        return userService.logout(u);
    }

    @PostMapping("login/update")
    String update(@RequestBody UserModel u)
    {
        return userService.updateinfo(u);
    }

    @DeleteMapping("login/delete")
    String delete(@RequestBody UserModel u)
    {
        return userService.delete(u);
    }
}
