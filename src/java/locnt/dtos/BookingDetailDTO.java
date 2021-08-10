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
    private int BookingDetailId;
    private int Amount;
    private int BookingId;
    private int ResourceId;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(int BookingDetailId, int Amount, int BookingId, int ResourceId) {
        this.BookingDetailId = BookingDetailId;
        this.Amount = Amount;
        this.BookingId = BookingId;
        this.ResourceId = ResourceId;
    }

    public int getBookingDetailId() {
        return BookingDetailId;
    }

    public void setBookingDetailId(int BookingDetailId) {
        this.BookingDetailId = BookingDetailId;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int BookingId) {
        this.BookingId = BookingId;
    }

    public int getResourceId() {
        return ResourceId;
    }

    public void setResourceId(int ResourceId) {
        this.ResourceId = ResourceId;
    }
    
    
}
