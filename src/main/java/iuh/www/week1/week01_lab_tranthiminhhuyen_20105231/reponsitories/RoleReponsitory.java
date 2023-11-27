package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories;

import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.connectDB.ConnectDB;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Grant;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Role;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleReponsitory {
    //get all role
    public List<Role> getListRole() throws SQLException, ClassNotFoundException {
        List<Role> listRole = new ArrayList<Role>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        String sql = "select * from role";
        ps = con.prepareStatement(sql);
        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String roleID = rs.getString(1);
                String roleName = rs.getString(2);
                String des = rs.getString(3);
                int status = rs.getInt(4);
    //            Grant a = Grant.values(status);
                listRole.add(new Role(roleID, roleName, des, Status.valueOf(String.valueOf(status))));

            }
            return listRole;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
//    get role by id
    public Role getRoleById(String id) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from role where role_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String roleID = rs.getString(1);
                String roleName = rs.getString(2);
                String des = rs.getString(3);
                int status = rs.getInt(4);
                return new Role(roleID, roleName, des, Status.valueOf(String.valueOf(status)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean addRole(Role role) throws SQLException, ClassNotFoundException{
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into role(role_id, role_name, description, status) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, role.getRoleId());
            ps.setString(2, role.getRoleName());
            ps.setString(3, role.getDescription());
            ps.setString(4, role.getStatus().toString());
            ps.executeUpdate();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
//    update role
    public boolean updateRole(Role role) throws SQLException, ClassNotFoundException{
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "Update role set role_name = ?, description = ?, status = ? where role_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, role.getRoleName());
            ps.setString(2, role.getDescription());
            ps.setString(3, role.getStatus().toString());
            ps.setString(4, role.getRoleId());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
    return false;
    }
//   delete role
    public boolean deleteRole(String id) throws SQLException, ClassNotFoundException{
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from Role where role_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
