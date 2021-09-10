package dao;

import models.Reimbursement;
import models.Reimbursements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{

    //connecting to database
    public static String url = "jdbc:postgresql://" + System.getenv("POSTGRES_URL") + "/project1_db";
    public static String username = System.getenv("POSTGRES_USERNAME");
    public static String password = System.getenv("POSTGRES_PASSWORD");

    public static ReimbursementDao reimbursementDao;

    ReimbursementDaoImpl() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ReimbursementDao getInstance(){
        if(reimbursementDao == null)
            reimbursementDao = new ReimbursementDaoImpl();
        return reimbursementDao;
    }


    @Override
    public List<Reimbursements> getAllReimb() {
        List<Reimbursements> reimbursements = new ArrayList<>();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String sql = "SELECT * FROM Reimbursements WHERE NOT reimb_status = 'Pending';";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursements.add(
                        new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13))
                );
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursements> getPendReimb() {
        List<Reimbursements> reimbursements = new ArrayList<>();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String sql = "SELECT * FROM Reimbursements WHERE reimb_status = 'Pending';";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursements.add(
                        new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13))
                );
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }


    @Override
    public List<Reimbursements> getEmpReimb(Integer userId) {
        List<Reimbursements> reimbursements = new ArrayList<>();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String sql = "SELECT * FROM Reimbursements WHERE ers_users_id = ? AND NOT reimb_status = 'Pending';";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursements.add(
                        new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13))
                );
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursements> getEmpPendReimb(Integer userId) {
        List<Reimbursements> reimbursements = new ArrayList<>();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String sql = "SELECT * FROM Reimbursements WHERE ers_users_id = ? AND reimb_status = 'Pending';";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursements.add(
                        new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13))
                );
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursements> getStatus(Integer statusId) {
        List<Reimbursements> reimbursements = new ArrayList<>();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String input = null;
            if(statusId == 0){
                input = "Denied";
            }else if(statusId == 1){
                input = "Approved";
            }else
                input = "Pending";

            String sql = "SELECT * FROM Reimbursements WHERE  reimb_status = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, input);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursements.add(
                        new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13))
                );
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursements> getEmpStatus(Integer userId, Integer statusId) {
        List<Reimbursements> reimbursements = new ArrayList<>();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String input = null;
            if(statusId == 0){
                input = "Denied";
            }else if(statusId == 1){
                input = "Approved";
            }else
                input = "Pending";

            String sql = "SELECT * FROM Reimbursements WHERE ers_users_id = ? AND reimb_status = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, input);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursements.add(
                        new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13))
                );
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public boolean addReimb(Reimbursement newReimb) {
        if(newReimb.equals(""))
            return false;

        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ers_reimbursement VALUES (DEFAULT, ?, " +
                    "current_timestamp, NULL, ?, ?, ?, NULL, 2, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, newReimb.getAmount());
            ps.setString(2, newReimb.getDescription());
            ps.setString(3, newReimb.getReceipt());
            ps.setInt(4, newReimb.getAuthorId());
            ps.setInt(5, newReimb.getTypeId());


            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateStatus(Integer reimbId, Integer userId, Integer statusId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE ers_reimbursement SET reimb_status_id = ? WHERE reimb_id = ?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolver = ? WHERE reimb_id =?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolved = now() WHERE reimb_id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, statusId);
            ps.setInt(2, reimbId);
            ps.setInt(3, userId);
            ps.setInt(4, reimbId);
            ps.setInt(5, reimbId);

            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean approveReimb(Integer reimbId, Integer userId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE ers_reimbursement SET reimb_status_id = 1 WHERE reimb_id = ?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolver = ? WHERE reimb_id =?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolved = now() WHERE reimb_id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, reimbId);
            ps.setInt(2, userId);
            ps.setInt(3, reimbId);
            ps.setInt(4, reimbId);

            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean denyReimb(Integer reimbId, Integer userId) {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE ers_reimbursement SET reimb_status_id = 0 WHERE reimb_id = ?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolver = ? WHERE reimb_id =?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolved = now() WHERE reimb_id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //parameters
            ps.setInt(1, reimbId);
            ps.setInt(2, userId);
            ps.setInt(3, reimbId);
            ps.setInt(4, reimbId);

            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reimbursements getReimb(Integer reimbId) {
        Reimbursements reimbursement = new Reimbursements();

        try{
            //connection
            Connection conn = DriverManager.getConnection(url, username, password);

            //sql statement to execute
            String sql = "SELECT * FROM Reimbursements WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, reimbId);

            //execute sql statement and return result
            ResultSet rs = ps.executeQuery();

            //loop through records
            while(rs.next()) {
                reimbursement = new Reimbursements(rs.getInt(1), rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11), rs.getString(12),
                                rs.getString(13));
            }
            //close connectiong
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursement;
    }
}
