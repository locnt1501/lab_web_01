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
public class ResourceDTO implements Serializable {

    private int resourceId;
    private String itemName;
    private String category;
    private int quantity;
    private String color;
    private String highestOfRole;
    private int statusId;
    private Date dateFrom;
    private Date dateTo;
    
    public ResourceDTO() {
    }

    public ResourceDTO(int resourceId, String itemName, String category, int quantity, String color, String highestOfRole, int statusId, Date dateFrom, Date dateTo) {
        this.resourceId = resourceId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.color = color;
        this.highestOfRole = highestOfRole;
        this.statusId = statusId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHighestOfRole() {
        return highestOfRole;
    }

    public void setHighestOfRole(String highestOfRole) {
        this.highestOfRole = highestOfRole;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    

}
