package com.mycalendar.user;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> users;

    public Users() {
        users = new ArrayList<User>();
    }
    public List<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        users.add(user);
    }
    public User getUser(String name) {
        for (User user : users) if (user.getName().equals(name)) return user;
        return null;
    }
    public boolean containUser(String name) {
        for (User user : users) if (user.getName().equals(name)) return true;
        return false;
    }
    public boolean validMDP(String name, String mpd) {
        for (User user : users) if (user.getName().equals(name) && user.getMdp().equals(mpd)) return true;
        return false;
    }
    public int getNumberOfUsers() {
        return users.size();
    }
}
