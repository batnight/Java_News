package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDao implements IDao<Role> {

    private final String SAVE_QUERY = "INSERT INTO public.\"Role\"(\"Role_Name\") VALUES (?);";
    private final String UPDATE_QUERY = "UPDATE public.\"Role\"\n"
            + "   SET  \"Role_Name\"=?\n"
            + " WHERE \"Role_Id\"=?;";
    private final String DELTE_QUERY = "DELETE FROM public.\"Role\"\n"
            + " WHERE \"Role_Id\"=?;";
    private final String SELECALL_QUERY = "SELECT *\n"
            + "  FROM public.\"Role\";";
    private final String SELECTSINGLEBYID_QUERY = "SELECT *\n"
            + "  FROM public.\"Role\" WHERE \"Role_Id\"=?;";

    @Override
    public int save(Role t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setString(1, t.getRoleName());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(Role t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setString(1, t.getRoleName());
            ps.setLong(1, t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(Role t) {
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
                Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> list = new ArrayList<Role>();
        Role role = new Role();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECALL_QUERY);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role.setId(rs.getLong("Role_Id"));
                role.setRoleName(rs.getString("Role_Name"));

                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Role selectSingleById(Role t) {
        Role role = new Role();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1, t.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role.setId(rs.getLong("Role_Id"));
                role.setRoleName(rs.getString("Role_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return role;
    }

}
