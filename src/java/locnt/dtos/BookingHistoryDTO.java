package locnt.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LocPC
 */
public class BookingHistoryDTO implements Serializable {

    private int bookingId;
    private Date createDate;
    private String status;
    private List<String> listItemName;

    public BookingHistoryDTO() {
    }

    public BookingHistoryDTO(int bookingId, Date createDate, String status, List<String> listItemName) {
        this.bookingId = bookingId;
        this.createDate = createDate;
        this.status = status;
        this.listItemName = listItemName;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getListItemName() {
        return listItemName;
    }

    public void setListItemName(List<String> listItemName) {
        this.listItemName = listItemName;
    }

}
