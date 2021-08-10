/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.booking;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import locnt.dtos.BookingDTO;
import locnt.utils.DBUtils;

/**
 *
 * @author LocPC
 */
public class BookingDAO implements Serializable {

    public int checkoutBookingReturnBookingID(Date createDate, Date dateBookingFrom, Date dateBookingTo,
            int status, String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            String sql = "INSERT INTO Booking(DateCreate,DateBookingFrom,DateBookingTo,StatusId, Email) "
                    + "OUTPUT inserted.BookingId "
                    + "VALUES (?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setDate(1, createDate);
            stm.setDate(2, dateBookingFrom);
            stm.setDate(3, dateBookingTo);
            stm.setInt(4, status);
            stm.setString(5, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("BookingId");
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

        return -1;
    }
}
