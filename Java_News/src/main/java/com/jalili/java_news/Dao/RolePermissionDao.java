package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RolePermissionDao implements IDao<RolePermission> {

    private final String SAVE_QUERY = "INSERT INTO public.\"Role_Permission\"(\n"
            + "            \"Role_Id\", \"canBanUnban\", \"canEnableComment\", \n"
            + "            \"canDisableComment\", \"canDeleteComment\", \"canCreateNews\", \"canEditNews\", \n"
            + "            \"canDeleteNews\", \"canCreateCategory\", \"canEditCategory\", \"canDeleteCategory\")\n"
            + "    VALUES (?, ?, ?, \n"
            + "            ?, ?, ?, ?, \n"
            + "            ?, ?, ?, ?);";
    private final String UPDATE_QUERY = "UPDATE public.\"Role_Permission\"\n"
            + "   SET  \"Role_Id\"=?, \"canBanUnban\"=?, \"canEnableComment\"=?, \n"
            + "       \"canDisableComment\"=?, \"canDeleteComment\"=?, \"canCreateNews\"=?, \n"
            + "       \"canEditNews\"=?, \"canDeleteNews\"=?, \"canCreateCategory\"=?, \"canEditCategory\"=?, \n"
            + "       \"canDeleteCategory\"=?\n"
            + " WHERE \"RolePermission_Id\"=?;";
    private final String DELTE_QUERY = "DELETE FROM public.\"Role_Permission\"\n"
            + " WHERE \"RolePermission_Id\"=?;";
    private final String SELECALL_QUERY = "SELECT *\n"
            + "  FROM public.\"Role_Permission\";";
    private final String SELECTSINGLEBYID_QUERY = "SELECT *\n"
            + "  FROM public.\"Role_Permission\" WHERE \"RolePermission_Id\"=?;";

    @Override
    public int save(RolePermission t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getRole().getId());
            ps.setBoolean(2, t.isCanBanUnban());
            ps.setBoolean(3, t.isCanEnableComment());
            ps.setBoolean(4, t.isCanDisableComment());
            ps.setBoolean(5, t.isCanDeleteComment());
            ps.setBoolean(6, t.isCanCreateNews());
            ps.setBoolean(7, t.isCanEditNews());
            ps.setBoolean(8, t.isCanDeleteNews());
            ps.setBoolean(9, t.isCanCreateCategory());
            ps.setBoolean(10, t.isCanEditCategory());
            ps.setBoolean(11, t.isCanDeleteCategory());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RolePermissionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(RolePermission t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getRole().getId());
            ps.setBoolean(2, t.isCanBanUnban());
            ps.setBoolean(3, t.isCanEnableComment());
            ps.setBoolean(4, t.isCanDisableComment());
            ps.setBoolean(5, t.isCanDeleteComment());
            ps.setBoolean(6, t.isCanCreateNews());
            ps.setBoolean(7, t.isCanEditNews());
            ps.setBoolean(8, t.isCanDeleteNews());
            ps.setBoolean(9, t.isCanCreateCategory());
            ps.setBoolean(10, t.isCanEditCategory());
            ps.setBoolean(11, t.isCanDeleteCategory());
            ps.setLong(12, t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RolePermissionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(RolePermission t) {
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
                Logger.getLogger(RolePermissionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<RolePermission> selectAll() {
        List<RolePermission> list = new ArrayList<RolePermission>();
        RolePermission rolePermission = new RolePermission();
        Connection connection = null;
        PreparedStatement ps = null;
        Role role = new Role();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECALL_QUERY);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rolePermission.setId(rs.getLong(1));
                role.setId(rs.getLong(2));
                rolePermission.setRole(role);
                rolePermission.setCanBanUnban(rs.getBoolean(3));
                rolePermission.setCanEnableComment(rs.getBoolean(4));
                rolePermission.setCanDisableComment(rs.getBoolean(5));
                rolePermission.setCanDeleteComment(rs.getBoolean(6));
                rolePermission.setCanCreateNews(rs.getBoolean(7));
                rolePermission.setCanEditNews(rs.getBoolean(8));
                rolePermission.setCanDeleteNews(rs.getBoolean(9));
                rolePermission.setCanCreateCategory(rs.getBoolean(10));
                rolePermission.setCanEditCategory(rs.getBoolean(11));
                rolePermission.setCanDeleteCategory(rs.getBoolean(12));

                list.add(rolePermission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RolePermissionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public RolePermission selectSingleById(RolePermission t) {
        RolePermission rolePermission = new RolePermission();
        Connection connection = null;
        PreparedStatement ps = null;
        Role role = new Role();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1,t.getId());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rolePermission.setId(rs.getLong(1));
                role.setId(rs.getLong(2));
                rolePermission.setRole(role);
                rolePermission.setCanBanUnban(rs.getBoolean(3));
                rolePermission.setCanEnableComment(rs.getBoolean(4));
                rolePermission.setCanDisableComment(rs.getBoolean(5));
                rolePermission.setCanDeleteComment(rs.getBoolean(6));
                rolePermission.setCanCreateNews(rs.getBoolean(7));
                rolePermission.setCanEditNews(rs.getBoolean(8));
                rolePermission.setCanDeleteNews(rs.getBoolean(9));
                rolePermission.setCanCreateCategory(rs.getBoolean(10));
                rolePermission.setCanEditCategory(rs.getBoolean(11));
                rolePermission.setCanDeleteCategory(rs.getBoolean(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RolePermissionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rolePermission;
    }

}
