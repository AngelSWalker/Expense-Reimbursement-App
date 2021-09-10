package service;

import dao.UsersDao;
import models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {

    UsersService usersService;

    //mock object
    UsersDao usersDao = Mockito.mock(UsersDao.class);

    @BeforeEach
    void setUp(){
        usersService = new UsersService(usersDao);
    }
    @Test
    void register() {
        Users expected = new Users(1, "username", "pass123", "Angel", "Walker", "email.gmail.com", 2);

        //usersDao.addUser(new Users(1, "username", "pass123", "Angel", "Walker", "email.gmail.com", 2))).thenReturn(expected);
        //Mockito.when(usersDao.getUser(expected.getUsername())).thenReturn(expected);
        Users actual = usersService.register(expected);

        //assertEquals(expected, actual);
        Mockito.verify(usersDao, Mockito.times(1)).addUser(expected);
    }

    @Test
    void login(){
        Users expected = new Users(1, "username", "pass123", "Angel", "Walker", "email.gmail.com", 2);

        Mockito.when(usersDao.getUser(expected.getUsername())).thenReturn(expected);

        Users actual = usersService.login(new Users(1, "username", "pass123", "Angel", "Walker", "email.gmail.com", 2));

        assertEquals(expected, actual);
    }

}