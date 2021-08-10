/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import locnt.dtos.AccountDTO;
import locnt.utils.DBUtils;

/**
 *
 * @author LocPC
 */
public class AccountDAO implements Serializable {

    public boolean checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "Select email, password From Account Where email = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public AccountDTO getInformation(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "Select Email, Password, Name, Address, Phone, CreateDate, RoleId, StatusId "
                        + "From Account "
                        + "Where Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String emailTemp = rs.getString("Email");
                    String password = rs.getString("Password");
                    String name = rs.getString("Name");
                    String address = rs.getString("Address");
                    int phone = rs.getInt("Phone");
                    Date date = rs.getDate("CreateDate");
                    int roleId = rs.getInt("RoleId");
                    int statusId = rs.getInt("StatusId");

                    AccountDTO dto = new AccountDTO(emailTemp, password, name,
                            address, phone, date, roleId, statusId);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createAccount(AccountDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnect();
            Date dateNow = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            if (con != null) {
                String sql = "INSERT INTO Account VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getName());
                stm.setString(4, dto.getAddress());
                stm.setInt(5, dto.getPhone());
                stm.setDate(6, dateNow);
                stm.setInt(7, dto.getRoleId());
                stm.setInt(8, dto.getStatusId());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
