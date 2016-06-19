package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubCategoryDao implements IDao<SubCategory> {

    private final String SAVE_QUERY = "INSERT INTO public.\"SubCategory\"(\n"
            + "\"Category_Id\", \"SubCategory_Name\")\n"
            + "    VALUES (?, ?);";
    private final String UPDATE_QUERY = "UPDATE public.\"SubCategory\"\n"
            + "   SET \"Category_Id\"=?, \"SubCategory_Name\"=?\n"
            + " WHERE \"SubCategory_Id\"=?;";
    private final String DELTE_QUERY = "DELETE FROM public.\"SubCategory\"\n"
            + " WHERE SubCategory_Id;";
    private final String SELECALL_QUERY = "SELECT *\n"
            + "  FROM public.\"SubCategory\";";
    private final String SELECTSINGLEBYID_QUERY = "SELECT *\n"
            + "  FROM public.\"SubCategory\" WHERE \"SubCategory_Id\"=?;";

    @Override
    public int save(SubCategory t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getCategory().getId());
            ps.setString(2, t.getSubCategoryName());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(SubCategory t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getCategory().getId());
            ps.setString(2, t.getSubCategoryName());
            ps.setLong(3, t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(SubCategory t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(DELTE_QUERY);
            ps.setLong(1, t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<SubCategory> selectAll() {
        List<SubCategory> list = new ArrayList<SubCategory>();
        SubCategory subCategory = new SubCategory();
        Connection connection = null;
        PreparedStatement ps = null;
        Category category = new Category();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECALL_QUERY);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subCategory.setId(rs.getLong(1));
                category.setId(rs.getLong(2));
                subCategory.setCategory(category);
                subCategory.setSubCategoryName(rs.getString(3));

                list.add(subCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public SubCategory selectSingleById(SubCategory t) {
        SubCategory subCategory = new SubCategory();
        Connection connection = null;
        PreparedStatement ps = null;
        Category category = new Category();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1,t.getId());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subCategory.setId(rs.getLong(1));
                category.setId(rs.getLong(2));
                subCategory.setCategory(category);
                subCategory.setSubCategoryName(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return subCategory;
    }

}
