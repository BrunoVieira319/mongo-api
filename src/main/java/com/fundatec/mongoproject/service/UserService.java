package com.fundatec.mongoproject.service;

import com.fundatec.mongoproject.dao.UserDao;
import com.fundatec.mongoproject.domain.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user.getDocument());
    }

    public List<User> getUserByUsername(String username) {
        List<Document> fetchedUsers = userDao.filter(new Document("username", username));
        return fetchedUsers.stream()
                .map(d -> new User(
                                d.getString("username"),
                                d.getString("name"),
                                d.getString("city")
                        ))
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        List<Document> users = userDao.findAll();
        return users.stream()
                .map(d -> new User(
                        d.getString("username"),
                        d.getString("name"),
                        d.getString("city")))
                .collect(Collectors.toList());
    }

    public long updateUser(String username, Document updates) {
        return userDao.update(username, updates);
    }

    public long deleteUserByName(String username) {
        long deleted = userDao.delete(new Document("username", username));
        return deleted;
    }
}
