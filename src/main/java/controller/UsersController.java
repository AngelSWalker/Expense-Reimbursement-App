package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Response;
import models.Users;
import service.UsersService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class UsersController {
    //controller singleton
    private static UsersController usersController;
    UsersService usersService;
    public UsersController(){
        usersService = new UsersService();
    }

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    public static UsersController getInstance(){
        if(usersController == null)
            usersController = new UsersController();
        return usersController;
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //set type and get print writer to output to user
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        //to receive data from a request body
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //out.println(requestBody);
        //jackson object mapper to change json to string
        Users user = new ObjectMapper().readValue(requestBody, Users.class);

        Users tempUser = usersService.login(user);

        if (tempUser != null) {
            //send back login has been successful
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("userObj", tempUser);
            out.println(new ObjectMapper().writeValueAsString(new Response("login successful", true, tempUser)));
        } else {
            //invalid username or password
            out.println(new ObjectMapper().writeValueAsString(new Response("invalid username or password", false, null)));
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        //to receive data from a request body
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Users user = new ObjectMapper().readValue(requestBody, Users.class);

        //try to creat user
        Users tempUser = usersService.register(user);

        if(tempUser != null){
            //if user was created
            out.println(new ObjectMapper().writeValueAsString(new Response("user has been created", true, tempUser)));
        }else{
            //if username already exsists
            out.println(new ObjectMapper().writeValueAsString(new Response("username already exists in system", false, null)));

        }
    }

    public void checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Users user = (Users) req.getSession().getAttribute("userObj");

        if(user != null){
            out.println(new ObjectMapper().writeValueAsString(new Response("session found", true, user)));
        }else{
            out.println(new ObjectMapper().writeValueAsString(new Response("no session womp womp", false, null)));
        }
    }


    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        req.getSession().setAttribute("userObj", null);

        out.println(new ObjectMapper().writeValueAsString(new Response("session terminated",true,null)));
    }

}

