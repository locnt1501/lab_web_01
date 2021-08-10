/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.resource;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author LocPC
 */
public class ResourceSearch implements Serializable {
    private int resourceId;
    private String itemName;
    private int quantity;

    public ResourceSearch() {
    }

    public ResourceSearch(int resourceId, String itemName, int quantity) {
        this.resourceId = resourceId;
        this.itemName = itemName;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
    
    
    
    
}
