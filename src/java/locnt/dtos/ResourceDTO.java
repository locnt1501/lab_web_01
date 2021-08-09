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
public class ResourceDTO implements Serializable {

    private String resourceId;
    private String itemName;
    private String category;
    private int quantity;
    private String color;
    private String highestOfRole;
    private int statusId;

    public ResourceDTO() {
    }

    public ResourceDTO(String resourceId, String itemName, String category, int quantity, String color, String highestOfRole, int statusId) {
        this.resourceId = resourceId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.color = color;
        this.highestOfRole = highestOfRole;
        this.statusId = statusId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
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
    

}
