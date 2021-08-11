/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.dtos;

import java.io.Serializable;

/**
 *
 * @author LocPC
 */
public class BookingDetailDTO implements Serializable {

    private int bookingDetailId;
    private int amount;
    private int bookingId;
    private int resourceId;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(int bookingDetailId, int amount, int bookingId, int resourceId) {
        this.bookingDetailId = bookingDetailId;
        this.amount = amount;
        this.bookingId = bookingId;
        this.resourceId = resourceId;
    }

    public int getBookingDetailId() {
        return bookingDetailId;
    }

    public void setBookingDetailId(int bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

}
