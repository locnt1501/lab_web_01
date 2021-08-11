/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.resource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
    public static final int ROW_PER_PAGE = 20;

    public List<ResourceDTO> listSearch;

    public List<ResourceDTO> getListSearch() {
        return listSearch;
    }

    public void searchResource(String catelory, String name, Date dateFrom, Date dateTo, int page) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT ResourceId, ItemName, Category, Quantity, Color, HighestOfRole, StatusId, DateFrom, DateTo "
                        + "FROM Resource WHERE Category = ? and ItemName = ? AND DateFrom between ? AND ? "
                        + "ORDER BY ItemName "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, catelory);
                stm.setString(2, name);
                stm.setDate(3, dateFrom);
                stm.setDate(4, dateTo);
                stm.setInt(5, (page - 1) * ROW_PER_PAGE);
                stm.setInt(6, ROW_PER_PAGE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int resourceId = rs.getInt("ResourceId");
                    String itemName = rs.getString("ItemName");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String color = rs.getString("Color");
                    String highestOfRole = rs.getString("HighestOfRole");
                    int status = rs.getInt("StatusId");
                    Date dateF = rs.getDate("DateFrom");
                    Date dateT = rs.getDate("DateTo");

                    ResourceDTO dto = new ResourceDTO(resourceId, itemName, category,
                            quantity, color, highestOfRole, status, dateF, dateT);
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

    public int totoalRecordSearch(String catelory, String name, Date dateFrom, Date dateTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT ROW_NUMBER() OVER ( ORDER BY Category ) AS RowNum, ResourceId, ItemName, Category, Quantity, Color, HighestOfRole, StatusId, DateFrom, DateTo "
                        + "FROM Resource "
                        + "WHERE Category = ? and ItemName = ? AND DateFrom between ? AND ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, catelory);
                stm.setString(2, name);
                stm.setDate(3, dateFrom);
                stm.setDate(4, dateTo);
                rs = stm.executeQuery();
                while (rs.next()) {
                    count++;
                }
                return count;
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
        return 0;
    }

    public ResourceDTO searchResourceById(int resourceId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnect();
            if (con != null) {
                String sql = "SELECT ResourceId, ItemName, Category, Quantity, Color, HighestOfRole, StatusId, DateFrom, DateTo "
                        + "FROM Resource WHERE ResourceId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, resourceId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int resourceIdDB = rs.getInt("ResourceId");
                    String itemName = rs.getString("ItemName");
                    String category = rs.getString("Category");
                    int quantity = rs.getInt("Quantity");
                    String color = rs.getString("Color");
                    String highestOfRole = rs.getString("HighestOfRole");
                    int status = rs.getInt("StatusId");
                    Date dateF = rs.getDate("DateFrom");
                    Date dateT = rs.getDate("DateTo");

                    ResourceDTO dto = new ResourceDTO(resourceIdDB, itemName, category,
                            quantity, color, highestOfRole, status, dateF, dateT);
                    return dto;
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
        return null;
    }

    public boolean updateQuantity(int quantity, int resourceId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnect();
            String sql = "UPDATE Resource SET Quantity = ? WHERE ResourceId =?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, resourceId);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
