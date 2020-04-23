package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "allusers")

public class UserModel implements Serializable {

    @Id
    String username;
    String email;
    String password;
    String confirmp;
    String gender;
    String dob;
    int logged;

    public UserModel()
    {
    }

    public UserModel(@JsonProperty ("username") String u, @JsonProperty ("email") String e, @JsonProperty ("password") String p, @JsonProperty ("confirmp") String cp, @JsonProperty ("gender") String g, @JsonProperty ("dob") String d)
    {
        username = u;
        email = e;
        password = p;
        confirmp = cp;
        gender = g;
        dob = d;
        logged = 0;
    }
}
