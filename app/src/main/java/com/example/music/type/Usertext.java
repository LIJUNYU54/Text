package com.example.music.type;

public class Usertext {
//    private  int id;
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usertext(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usertext{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
