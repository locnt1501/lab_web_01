package locnt.dtos;


import java.io.Serializable;
import java.sql.Date;

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
    private String itemName;
    private String category;
    private Date createDate;
    private String status;

    public BookingHistoryDTO() {
    }

    public BookingHistoryDTO(String itemName, String category, Date createDate, String status) {
        this.itemName = itemName;
        this.category = category;
        this.createDate = createDate;
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    
}
