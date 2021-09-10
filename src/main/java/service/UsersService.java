package service;

import dao.UsersDao;
import dao.UsersDaoImpl;
//import models.SendEmail;
import models.Users;

public class UsersService {
    UsersDao usersDao;

    public UsersService(){
        usersDao = UsersDaoImpl.getInstance();
    }

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public Users register(Users newUser){
        //check if username exists in the system
        Users tempUser = usersDao.getUser(newUser.getUsername());
        if(tempUser != null)
            return null;
        usersDao.addUser(newUser);

        return usersDao.getUser(newUser.getUsername());
    }

    public Users login(Users user){
        //check username exists in system
        Users tempUser = usersDao.getUser(user.getUsername());
        if(tempUser == null)
            return null;
        if(!tempUser.getPassword().equals(user.getPassword()))
            return null;
        return tempUser;
    }



    /*public String getUsername(Integer userId){
        return usersDao.getUsername(userId);
    }

    public String getPassword(Integer userId){
        return usersDao.getPassword(userId);
    }

    public String getFirstName(Integer userId){
        return usersDao.getFirstName(userId);
    }*/
}
