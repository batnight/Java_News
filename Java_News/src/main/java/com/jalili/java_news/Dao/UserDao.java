package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements IDao<User> {

    private final String SAVE_QUERY = "INSERT INTO public.\"User\"(\n"
            + "            \"RolePermission_Id\", \"Name\", \"LastName\", \"Gender\", \n"
            + "            \"Email\", \"IsBan\", \"Username\", \"Password\")\n"
            + "    VALUES (?, ?, ?, ?, \n"
            + "            ?, ?, ?, ?);";
    private final String UPDATE_QUERY = "UPDATE public.\"User\"\n"
            + "   SET  \"RolePermission_Id\"=?, \"Name\"=?, \"LastName\"=?, \"Gender\"=?, \n"
            + "       \"Email\"=?, \"IsBan\"=?, \"Username\"=?, \"Password\"=?\n"
            + " WHERE \"User_Id\"=?;";
    private final String DELTE_QUERY = "DELETE FROM public.\"User\"\n"
            + " WHERE \"User_Id\"=?;";
    private final String SELECALL_QUERY = "SELECT *\n"
            + "  FROM public.\"User\";";
    private final String SELECTSINGLEBYID_QUERY = "SELECT *\n"
            + "  FROM public.\"User\" WHERE \"User_Id\"=?;";

    @Override
    public int save(User t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getRolePermission().getId());
            ps.setString(2, t.getName());
            ps.setString(3, t.getLastName());
            ps.setString(4, t.getGender());
            ps.setString(5, t.getEmail());
            ps.setBoolean(6, t.isIsBan());
            ps.setString(7, t.getUsername());
            ps.setString(8, t.getPassword());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(User t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getRolePermission().getId());
            ps.setString(2, t.getName());
            ps.setString(3, t.getLastName());
            ps.setString(4, t.getGender());
            ps.setString(5, t.getEmail());
            ps.setBoolean(6, t.isIsBan());
            ps.setString(7, t.getUsername());
            ps.setString(8, t.getPassword());
            ps.setLong(9,t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(User t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(DELTE_QUERY);
            ps.setLong(1,t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<User> selectAll() {
        List<User> list = new ArrayList<User>();
        User user = new User();
        Connection connection = null;
        PreparedStatement ps = null;
        RolePermission rolePermission = new RolePermission();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECALL_QUERY);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(1));
                rolePermission.setId(rs.getLong(2));
                user.setRolePermission(rolePermission);
                user.setName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setIsBan(rs.getBoolean(7));
                user.setUsername(rs.getString(8));
                user.setPassword(rs.getString(9));

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public User selectSingleById(User t) {
        User user = new User();
        Connection connection = null;
        PreparedStatement ps = null;
        RolePermission rolePermission = new RolePermission();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1,t.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                rolePermission.setId(rs.getLong(2));
                user.setRolePermission(rolePermission);
                user.setName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setIsBan(rs.getBoolean(7));
                user.setUsername(rs.getString(8));
                user.setPassword(rs.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

}
