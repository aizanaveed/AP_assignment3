package com.example.demo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    String suUser(UserModel u)
    {
        List<UserModel> userList = userRepository.findAll();
        for(int i=0; i<userList.size(); i++)
        {
            if(u.username.matches(userList.get(i).username))
                return "Username already exists";
        }

        if(!u.password.matches(u.confirmp))
            return "Password and confirm password do not match";

        if(!u.gender.matches("female") && !u.gender.matches("male"))
            return "Please input appropriate gender option";

        userRepository.save(u);
        return "you have signed up";
    }

    String loguser(UserModel u)
    {
        List<UserModel> userList = userRepository.findAll();
        for(int i=0; i<userList.size(); i++)
        {
            if(u.username.matches(userList.get(i).username))
            {
                if(userList.get(i).logged == 0)
                {
                    if(u.password.matches(userList.get(i).password))
                    {
                        UserModel user = userRepository.getOne(u.username);
                        user.logged =1;
                        userRepository.save(user);
                        return "You are logged in. Welcome " + u.username;
                    }
                }
                else
                    return "You are already logged in";
            }
        }
        return "Username or password is incorrect";
    }

    String details(UserModel u)
    {
        List<UserModel> userList = userRepository.findAll();
        for(int i=0; i<userList.size(); i++)
        {
            if(userList.get(i).username.matches(u.username))
            {
                if(userList.get(i).logged == 1)
                {
                    UserModel user = userList.get(i);
                    return "username: " + user.username + "\n email: " + user.email + "\n password: " + user.password + "\n gender: " + user.gender + "\n DOB: " + user.dob;
                }
                else
                    return "You have to be logged in to view details";
            }
        }
        return "You have not signed up";
    }

    String logout(UserModel u)
    {
        List<UserModel> userList = userRepository.findAll();
        for(int i=0; i<userList.size(); i++)
        {
            if(userList.get(i).username.matches(u.username))
            {
                UserModel user = userList.get(i);
                if(user.logged == 1)
                {
                    user.logged = 0;
                    userRepository.save(user);
                    return "You have been logged out";
                }
                else
                    return "You have to log in before you log out";
            }
        }
        return "You are not signed up";
    }

    String updateinfo(UserModel u)
    {
        List<UserModel> user = userRepository.findAll();
        UserModel theone = null;
        for(int i=0; i<user.size(); i++)
        {
            if(user.get(i).username.matches(u.username))
                theone = user.get(i);
        }
        if(theone == null)
            return "You have not signed up or are using incorrect username";

        if(theone.logged == 1)
        {
            if(!u.password.matches(u.confirmp))
                return "Your new passwords do not match";

            if(!u.gender.matches("female") && !u.gender.matches("male"))
                return "Please input appropriate gender option";
        }
        else
            return "You have to be logged in to update information";

        u.logged = 1;
        userRepository.save(u);
        return "Account information has been updated";
    }

    String delete(UserModel u)
    {
        List <UserModel> user = userRepository.findAll();
        UserModel theone = null;

        for(int i=0; i<user.size(); i++)
        {
            if(user.get(i).username.matches(u.username))
                theone = user.get(i);
        }

        if(theone == null)
            return "You are not signed up";

        if(theone.logged == 1)
        {
            userRepository.delete(u);
            return "Your account has been removed";
        }
        else
            return "You have to be logged in to delete account";
    }
}
