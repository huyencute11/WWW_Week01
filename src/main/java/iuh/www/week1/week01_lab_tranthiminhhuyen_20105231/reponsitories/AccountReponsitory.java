package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories;

import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.connectDB.ConnectDB;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Account;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountReponsitory {
    public List<Account> getAllAccount(){
        ArrayList<Account> listAccount = new ArrayList<Account>();

        try {
            Connection connection = ConnectDB.getInstance().getConnection();
            String sql = "select * from account";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String accountId = rs.getString(1);
                String fullName = rs.getString(2);
                String passWord = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                Status status = Status.valueOf(rs.getString(6));
                Account a = new Account(accountId, fullName, passWord, email, phone, status);
//                System.out.println(a);
                listAccount.add(a);
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listAccount;
    }

    public Optional<Account> login(String email, String password) throws SQLException, ClassNotFoundException {
        Account account = null;
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        String sql = "Select * from account where email = ? and password = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String accountId = rs.getString(1);
                String fullName = rs.getString(2);
                String pass = rs.getString(3);
                String em = rs.getString(4);
                String phone = rs.getString(5);
                Status status = Status.valueOf(rs.getString(6));
                account = new Account(accountId,fullName,pass,em,phone,status);
                return Optional.of(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    public boolean insertAccount(Account account) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT into account VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, account.getAccountId());
            ps.setString(2, account.getFullName());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone());
            ps.setString(6, account.getStatus().toString());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
    //update
    public boolean updateAccount(Account account) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Account SET full_name=?, email=?, password=?, phone=? WHERE account_id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, account.getFullName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getPhone());
//            ps.setString(5, account.getStatus().toString());
            ps.setString(5, account.getAccountId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
//             e.printStackTrace();
        }
        return false;
    }
    public boolean updateAccount2ForAdmin(Account account) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Account SET full_name=?, email=?, password=?, phone=?, status=? WHERE account_id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, account.getFullName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getPhone());
            ps.setString(5, account.getStatus().toString());
            ps.setString(6, account.getAccountId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
//             e.printStackTrace();
        }
        return false;
    }
    public boolean deleteAccount(String accountId) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM Account WHERE account_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, accountId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // e.printStackTrace();
            return false;
        }
    }

    public Account getAccountById(String accountId) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM Account WHERE account_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4),  rs.getString(5) ,Status.valueOf(rs.getString(6))
                );
                return a;

            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        return null;
    }
}
