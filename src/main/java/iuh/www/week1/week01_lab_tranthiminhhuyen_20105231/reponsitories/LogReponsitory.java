package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories;


import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.connectDB.ConnectDB;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Account;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogReponsitory {

    //create log
    public boolean createLog(Logs log) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into log(account_id, login_time, logout_time, notes) values( ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, log.getAccount().getAccountId());
            LocalDate login_time = log.getLogin_time();
            LocalDate logout_time = log.getLogout_time();
            ps.setDate(2, java.sql.Date.valueOf(login_time));
            ps.setDate(3, java.sql.Date.valueOf(logout_time));
            ps.setString(4, log.getNotes());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public List<Logs> getListLog() throws SQLException, ClassNotFoundException {
        List<Logs> listLog = new ArrayList<>();
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from log";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String accountId = rs.getString(2);
                LocalDate loginTime = rs.getDate(3).toLocalDate();
                LocalDate logoutTime = rs.getDate(4).toLocalDate();
                String note = rs.getString(5);
                Account a = new Account(accountId);
                Logs l = new Logs(id, a, loginTime, logoutTime, note);
            }
            return listLog;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
//    get log by id
    public Logs getLogById(int id) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from Log where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                int idLog = rs.getInt(1);
                String accountId = rs.getString(2);
                LocalDate loginTime = rs.getDate(3).toLocalDate();
                LocalDate logoutTime = rs.getDate(4).toLocalDate();
                String note = rs.getString(5);
                Account a = new Account(accountId);
                Logs l;
                l = new Logs(idLog, a, loginTime, logoutTime, note);
                return l;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    //delete log by id
    public boolean deleteLog(String id) throws SQLException, ClassNotFoundException{
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from log where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException E){
            E.printStackTrace();
        }
        return false;
    }


}
