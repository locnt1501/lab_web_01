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
import locnt.dtos.BookingRequestProcessDTO;
import locnt.dtos.ResourceDTO;
import locnt.utils.DBUtils;

/**
 *
 * @author LocPC
 */
public class BookingDAO implements Serializable {

    public static final int ROW_PER_PAGE = 20;
    private List<BookingRequestProcessDTO> listBooking;
    private List<BookingHistoryDTO> listBookingHistory;

    public List<BookingHistoryDTO> getListBookingHistory() {
        return listBookingHistory;
    }

    public List<BookingRequestProcessDTO> getListBooking() {
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

    public void searchBooking(String email, int status, int page) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT b.BookingId, b.DateCreate, b.DateBookingFrom, DateBookingTo, b.StatusId, b.Email "
                        + "FROM Booking b "
                        + "WHERE b.StatusId= ? and b.Email = ? "
                        + "ORDER BY DateCreate "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, email);
                stm.setInt(3, (page - 1) * ROW_PER_PAGE);
                stm.setInt(4, ROW_PER_PAGE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    Date dateCreate = rs.getDate("DateCreate");
                    Date dateFrom = rs.getDate("DateBookingFrom");
                    Date dateTo = rs.getDate("DateBookingTo");
                    int statusID = rs.getInt("StatusId");
                    String emailDB = rs.getString("Email");

                    String subSql = "SELECT r.ItemName FROM BookingDetail db "
                            + "Left JOIN Resource r ON r.ResourceId = db.ResourceId WHERE db.BookingId = " + bookingId;
                    stm = con.prepareStatement(subSql);
                    ResultSet rsSub = stm.executeQuery();
                    List<String> listItemName = new ArrayList<>();
                    while (rsSub.next()) {
                        listItemName.add(rsSub.getString("ItemName"));
                    }
                    BookingRequestProcessDTO dto = new BookingRequestProcessDTO(bookingId, dateCreate,
                            dateFrom, dateTo, statusID, emailDB, listItemName);
                    if (this.listBooking == null) {
                        this.listBooking = new ArrayList<BookingRequestProcessDTO>();
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

    public void getHistoryBooking(String email, Date dateCreate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT  b.DateCreate, s.Name, b.BookingId "
                        + "FROM Booking b, Status s "
                        + "WHERE b.StatusId = s.StatusId and b.Email = ? and b.DateCreate < ? ORDER BY b.DateCreate";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setDate(2, dateCreate);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    Date dateCreateDB = rs.getDate("DateCreate");
                    String status = rs.getString("Name");
                    
                    String subSql = "SELECT r.ItemName FROM BookingDetail db "
                            + "Left JOIN Resource r ON r.ResourceId = db.ResourceId WHERE db.BookingId = " + bookingId;
                    stm = con.prepareStatement(subSql);
                    ResultSet rsSub = stm.executeQuery();
                    List<String> listItemName = new ArrayList<>();
                    while (rsSub.next()) {
                        listItemName.add(rsSub.getString("ItemName"));
                    }
                    
                    BookingHistoryDTO dto = new BookingHistoryDTO(bookingId, dateCreateDB, status, listItemName);
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

    public int searchTotalBooking(String email, int statusId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT ROW_NUMBER() OVER ( ORDER BY DateCreate ) AS RowNum, DateCreate, DateBookingFrom, DateBookingTo, StatusId, Email "
                        + "FROM Booking "
                        + "WHERE Email = ? and StatusId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, statusId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    count++;
                }
                return count;
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
        return 0;
    }
}
