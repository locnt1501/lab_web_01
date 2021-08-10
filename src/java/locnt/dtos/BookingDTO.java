/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author LocPC
 */
public class BookingDTO implements Serializable {
    private int bookingId;
    private Date dateCreate;
    private Date dateBookingFrom;
    private Date dateBookingTo;
    private int statusId;
    private String email;

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, Date dateCreate, Date dateBookingFrom, Date dateBookingTo, int statusId, String email) {
        this.bookingId = bookingId;
        this.dateCreate = dateCreate;
        this.dateBookingFrom = dateBookingFrom;
        this.dateBookingTo = dateBookingTo;
        this.statusId = statusId;
        this.email = email;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateBookingFrom() {
        return dateBookingFrom;
    }

    public void setDateBookingFrom(Date dateBookingFrom) {
        this.dateBookingFrom = dateBookingFrom;
    }

    public Date getDateBookingTo() {
        return dateBookingTo;
    }

    public void setDateBookingTo(Date dateBookingTo) {
        this.dateBookingTo = dateBookingTo;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
