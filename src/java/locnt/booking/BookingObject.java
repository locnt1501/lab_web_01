/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import locnt.dtos.CartDTO;

/**
 *
 * @author LocPC
 */
public class BookingObject implements Serializable {

    private List<CartDTO> listCart;

    public List<CartDTO> getListCart() {
        return listCart;
    }

    public void setListCart(List<CartDTO> listCart) {
        this.listCart = listCart;
    }

    public void addItemToCart(String title, int resourceId) {
        if (title == null) {
            return;
        }

        if (title.trim().isEmpty()) {
            return;
        }
        if (this.listCart == null) {
            this.listCart = new ArrayList<CartDTO>();
        }
        int quantity = 1;
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getItemName().equals(title)) {
                listCart.get(i).setQuantity(listCart.get(i).getQuantity() + 1);
            } else {
                CartDTO dto = new CartDTO(resourceId, title, quantity);
                listCart.add(dto);
            }
        }
    }

    public void removeItemFromCart(String title) {
        
    }
}
