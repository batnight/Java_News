package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LikeCommentDao implements IDao<LikeComment> {

    private final String SAVE_QUERY = "INSERT INTO public.\"LikeComment\"(\"User_Id\", \"Comment_Id\")VALUES (?, ?);";
    private final String UPDATE_QUERY = "UPDATE public.\"LikeComment\" SET \"User_Id\"=?, \"Comment_Id\"=? WHERE \"LikeComment_Id\"=?;";
    private final String DELTE_QUERY = "DELETE FROM public.\"LikeComment\" WHERE \"LikeComment_Id\"=?;";
    private final String SELECALL_QUERY = "SELECT * FROM public.\"LikeComment\";";
    private final String SELECTSINGLEBYID_QUERY = "SELECT * FROM public.\"LikeComment\" WHERE \"LikeComment_ID\"=?;";

    @Override
    public int save(LikeComment t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getUser().getId());
            ps.setLong(2, t.getComment().getId());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeCommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(LikeComment t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getUser().getId());
            ps.setLong(2, t.getComment().getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeCommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(LikeComment t) {
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
                Logger.getLogger(LikeCommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<LikeComment> selectAll() {
        List<LikeComment> list = new ArrayList<LikeComment>();
        LikeComment likeComment = new LikeComment();
        Connection connection = null;
        PreparedStatement ps = null;
        User user = new User();
        Comment comment = new Comment();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECALL_QUERY);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comment.setId(rs.getLong("Comment_Id"));
                user.setId(rs.getLong("User_Id"));
                likeComment.setComment(comment);
                likeComment.setUser(user);

                list.add(likeComment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeCommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public LikeComment selectSingleById(LikeComment t) {
        LikeComment likeComment = new LikeComment();
        Connection connection = null;
        PreparedStatement ps = null;
        Comment comment = new Comment();
        User user = new User();
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1,t.getId());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comment.setId(rs.getLong("Comment_Id"));
                user.setId(rs.getLong("User_Id"));
                likeComment.setComment(comment);
                likeComment.setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeCommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return likeComment;
    }

}
