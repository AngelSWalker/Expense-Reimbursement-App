package dao;

import models.Users;

import java.sql.*;

public class UsersDaoImpl implements UsersDao{
    private static UsersDao usersDao;

    public UsersDaoImpl() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static UsersDao getInstance(){
        if(usersDao == null)
            usersDao = new UsersDaoImpl();
        return usersDao;
    }

    //connecting to database
    public static String url = "jdbc:postgresql://" + System.getenv("POSTGRES_URL") + "/project1_db";
    public static String username = System.getenv("POSTGRES_USERNAME");
    public static String password = System.getenv("POSTGRES_PASSWORD");



    @Override
    public Boolean addUser(Users newUser) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ers_users values(DEFAULT, ?, ?, " +
                    "?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getFirstName());
            ps.setString(4, newUser.getLastName());
            ps.setString(5, newUser.getEmail());
            ps.setInt(6, newUser.getUserRoleId());



        return ps.executeUpdate()!=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Users getUser(String userName) {
        Users user = null;

        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, userName);
            //this is wh
            ResultSet rs = ps.executeQuery();


            //this is iterating through the records
            while(rs.next()) {
                user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /*@Override
    public String getUsername(Integer userId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT ers_username FROM ers_users WHERE ers_users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPassword(Integer userId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT ers_password FROM ers_users WHERE ers_users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFirstName(Integer userId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT user_first_name FROM ers_users WHERE ers_users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }*/
}
