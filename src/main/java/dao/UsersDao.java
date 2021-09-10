package dao;

import models.Users;

public interface UsersDao {
    Boolean addUser(Users newUser);
    Users getUser(String userName);
}
