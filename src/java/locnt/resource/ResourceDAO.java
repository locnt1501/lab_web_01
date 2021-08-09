/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.resource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locnt.dtos.ResourceDTO;
import locnt.utils.DBUtils;

/**
 *
 * @author LocPC
 */
public class ResourceDAO implements Serializable {

    public List<ResourceDTO> listSearch;

    public List<ResourceDTO> getListSearch() {
        return listSearch;
    }

    public void searchLastname(String catelory, String name, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "Select * "
                        + "From Resource "
                        + "Where lastname Catelory = ? and ItemName = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, catelory);
                stm.setString(2, name);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String resourceId = rs.getString("ResourceId");
                    String itemName = rs.getString("ItemName");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String color = rs.getString("Color");
                    String highestOfRole = rs.getString("HighestOfRole");
                    int status = rs.getInt("StatusId");
                    ResourceDTO dto = new ResourceDTO(resourceId, itemName, category, quantity, color, highestOfRole, status);
                    if (this.listSearch == null) {
                        this.listSearch = new ArrayList<ResourceDTO>();
                    }
                    this.listSearch.add(dto);
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
}
