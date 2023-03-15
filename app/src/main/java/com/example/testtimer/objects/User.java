package com.example.testtimer.objects;

public class User {

    public String first_name;
    public String last_name;
    public String bd;
    public String email;
    public String username;
    public String password;
    public int points;


    public User(String fn,String ln,String bd,String email, String usn,String pw){
        first_name = fn;
        last_name = ln;
        this.bd = bd;
        this.email = email;
        username = usn;
        password = pw;
        points = 0;
    }
}
