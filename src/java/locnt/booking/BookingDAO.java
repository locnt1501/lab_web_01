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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locnt.dtos.BookingDTO;
import locnt.dtos.BookingHistoryDTO;
import locnt.dtos.ResourceDTO;
import locnt.utils.DBUtils;

/**
 *
 * @author LocPC
 */
public class BookingDAO implements Serializable {

    private List<BookingDTO> listBooking;
    private List<BookingHistoryDTO> listBookingHistory;

    public List<BookingHistoryDTO> getListBookingHistory() {
        return listBookingHistory;
    }

    public List<BookingDTO> getListBooking() {
        return listBooking;
    }

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

    public void searchBooking(String email, int status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT BookingId, DateCreate, DateBookingFrom, DateBookingTo, StatusId, Email "
                        + "FROM Booking "
                        + "WHERE StatusId= ? and Email = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    Date dateCreate = rs.getDate("DateCreate");
                    Date dateFrom = rs.getDate("DateBookingFrom");
                    Date dateTo = rs.getDate("DateBookingTo");
                    int statusID = rs.getInt("StatusId");
                    String emailDB = rs.getString("Email");

                    BookingDTO dto = new BookingDTO(bookingId, dateCreate, dateFrom, dateTo, statusID, emailDB);
                    if (this.listBooking == null) {
                        this.listBooking = new ArrayList<BookingDTO>();
                    }
                    this.listBooking.add(dto);
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
    }

    public boolean updateBookingByStatus(int bookingid, int status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "UPDATE Booking Set StatusId = ? WHERE BookingId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, bookingid);
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

    public void getHistoryBooking(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT r.ItemName, r.Category, b.DateCreate, s.Name "
                        + "FROM BookingDetail bd, Booking b, Resource r, Status s "
                        + "WHERE bd.ResourceId = r.ResourceId and bd.BookingId = b.BookingId "
                        + "and b.StatusId = s.StatusId and b.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String itemName = rs.getString("ItemName");
                    String category = rs.getString("Category");
                    Date dateCreate = rs.getDate("DateCreate");
                    String status = rs.getString("Name");
                    BookingHistoryDTO dto = new BookingHistoryDTO(itemName, category, dateCreate, status);
                    if (listBookingHistory == null) {
                        this.listBookingHistory = new ArrayList<BookingHistoryDTO>();
                    }
                    this.listBookingHistory.add(dto);
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
    }
}
