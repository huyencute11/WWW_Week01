package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories;

import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.connectDB.ConnectDB;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GrantAccessReponsitory {
    public boolean insertGrandAccess(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        try {
            String sql = "insert into account value(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grantAccess.getRole().getRoleId());
            ps.setString(2, grantAccess.getAccount().getAccountId());
            ps.setString(3, grantAccess.getIsGrant().toString());
            ps.setString(4, grantAccess.getNote());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
       return false;
    }
    // get all

    public List<GrantAccess> getListGrantAccess() throws SQLException, ClassNotFoundException{
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from grant_access join account on grant_access.account_id = account.account_id join role on grant_access.role_id = role.role_id";
            ps = connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }
    //get grand by id account and id role
    public GrantAccess getGrantAccessById(String accountId, String roleId) throws SQLException, ClassNotFoundException{
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "Select * from grant_access " +
                    "join account on grant_access.account_id = account.account_id " +
                    "join role on grant_access.role_id = role.role_id " +
                    "where grant_access.account_id = ? and grant_access.role_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, roleId);
            ps.setString(2, accountId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getString("account_id"));
                Role role = new Role(rs.getString("role_id"));
                GrantAccess gr = new GrantAccess(role, account, Grant.valueOf(String.valueOf(3)), rs.getString(4));
                return gr;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public GrantAccess getGrantByAccountId(String accountId) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from grant_access where account_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getString("account_id"));
                Role role = new Role(rs.getString("role_id"));
                GrantAccess gr = new GrantAccess(role, account, Grant.valueOf(String.valueOf(rs.getString("is_grant"))), rs.getString("note"));
                return gr;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //insert role
    public boolean insertGrantAccess(GrantAccess grantAccess)throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, grantAccess.getAccount().getAccountId());
            ps.setString(2, grantAccess.getRole().getRoleId());
            ps.setString(3, grantAccess.getIsGrant().toString());
            ps.setString(4, grantAccess.getNote());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateGrantAccess(GrantAccess gr) throws SQLException, ClassNotFoundException{
        Connection connection;
        connection =ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE grant_access SET is_grant=?, note=? WHERE account_id=? and role_id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, gr.getIsGrant().toString());
            ps.setString(2, gr.getNote());
            ps.setString(3, gr.getAccount().getAccountId());
            ps.setString(4, gr.getRole().getRoleId());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    //update id role grantAccess
    public boolean updateIdRoleInGrantAccess(String idRole, String accountId) throws SQLException, ClassNotFoundException{
        Connection connection;
        connection =ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE grant_access SET  role_id =? WHERE account_id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, idRole);
            ps.setString(2, accountId);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    //delete
    public boolean delete(String accountId, String roleId) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM grant_access WHERE account_id=? and role_id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.setString(2, roleId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

