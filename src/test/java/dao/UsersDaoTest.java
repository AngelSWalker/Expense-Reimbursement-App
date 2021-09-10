package dao;

import models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class UsersDaoTest {

    UsersDao usersDao;

    //@Mock
    UsersDaoImpl usersDaoImpl = Mockito.mock(UsersDaoImpl.class);

    @BeforeEach
    void setUp(){
        usersDao = UsersDaoImpl.getInstance();
    }
    @Test
    void addUser() {
       /* Boolean expected = true;
        Users user = new Users(1, "username1", "pass123","Angel", "Walker", "email1@gmail.com", 2);

        Mockito.when(usersDaoImpl.addUser(user)).thenReturn(true);

        Boolean actual = usersDao.addUser(user);

        assertEquals(expected, actual);
        *///Mockito.verify(usersDaoImpl, Mockito.times(1)).addUser(user);
    }

    @Test
    void getUser() {
        Users users = new Users(1, "kobe123", "angelcakes","Kobe", "Wheeler", "kobe123@gmail.com", 1);

        Mockito.when(usersDaoImpl.getUser(users.getUsername())).thenReturn(users);

        //doNothing().when(usersDao.getUser("username2"));

        Users actual = usersDao.getUser(users.getUsername());
        //System.out.println(actual);
        //Mockito.verify(usersDaoImpl, Mockito.times(1)).getUser(users.getUsername());
        assertEquals(users.toString(), actual.toString());
    }
}