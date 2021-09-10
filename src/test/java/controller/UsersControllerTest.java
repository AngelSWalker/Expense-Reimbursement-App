package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Response;
import models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UsersControllerTest extends Mockito{

    UsersController usersController;

    UsersService usersService = Mockito.mock(UsersService.class);

    UsersControllerTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        usersController = new UsersController(usersService);

    }

    /*@Test
    void login() throws IOException {
        Users user = new Users(1, "username", "pass123", "Angel", "Walker", "email@gmail.com", 2);

        Mockito.when(req.getReader()).thenReturn(
                new BufferedReader(new StringReader("{\n" +
                        "    \"id\" : 1,\n" +
                        "    \"username\" : \"username\",\n" +
                        "    \"password\" : \"pass123\",\n" +
                        "    \"firstName\" : \"Angel\",\n" +
                        "    \"lastName\" : \"Walker\",\n" +
                        "    \"email\" : \"email@gmail.com\",\n" +
                        "    \"userRoleId\" : 2\n" +
                        "}")));
        //Mockito.when(req.getContentType()).thenReturn("application/json");
        *//*String requestBody = "{\n" +
                "    \"username\" : \"username\",\n" +
                "    \"password\" : \"pass123\",\n" +
                "    \"userRoleId\" : 2\n" +
                "}";*//*
        //Mockito.when(req.getReader().lines().collect(Collectors.joining(System.lineSeparator()))).thenReturn(requestBody);
        //Mockito.spy(new ObjectMapper().readValue(requestBody, Users.class)).equals(requestBody);
        //Mockito.when(out.println(new ObjectMapper()))
        Mockito.when(usersService.login(user)).thenReturn(user);

        usersController.login(req, resp);
        //Mockito.verify(usersService, Mockito.times(1)).login(user);
        //assertEquals(user, user);
    }*/

    @Test
    void register() {
    }

    @Test
    void checkSession() throws IOException {
        Users user = new Users(1, "username", "pass123", "Angel", "Walker", "test@gmail.com", 1);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);

        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        when(req.getSession().getAttribute("userObj")).thenReturn(user);

        usersController.checkSession(req, resp);
    }

    /*@Test
    void logout() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        usersController.logout(req, resp);

    }*/
}