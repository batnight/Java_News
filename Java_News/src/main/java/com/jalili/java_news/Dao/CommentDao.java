
package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDao implements IDao<Comment>{
    private final String SAVE_QUERY="INSERT INTO public.\"Comment\"(\"News_Id\", \"User_Id\", \"Title\", \"Comment\", \"IsEnable\") VALUES (?, ?, ?, ?, ?);";
    private final String UPDATE_QUERY="UPDATE public.\"Comment\" SET \"News_Id\"=?, \"User_Id\"=?, \"Title\"=?, \"Comment\"=?,\"IsEnable\"=? WHERE \"Comment_Id\"=?;";
    private final String DELTE_QUERY="DELETE FROM public.\"Comment\" WHERE \"Comment_Id\"=?;";
    private final String SELECALL_QUERY="SELECT * FROM public.\"Comment\";";
    private final String SELECTSINGLEBYID_QUERY="SELECT * FROM public.\"Comment\" WHERE \"Comment_Id\"=?;";
    @Override
    public int save(Comment t) {
        int status=0;
        Connection connection=null;
        PreparedStatement ps=null;
        try{
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getNews().getId());
            ps.setLong(2, t.getUser().getId());
            ps.setString(3, t.getTitle());
            ps.setString(4, t.getComment());
            ps.setBoolean(5, t.getIsEnable());
            
            status=ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(Comment t) {
        int status=0;
        Connection connection =null;
        PreparedStatement ps=null;
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getNews().getId());
            ps.setLong(2, t.getUser().getId());
            ps.setString(3, t.getTitle());
            ps.setString(4, t.getComment());
            ps.setBoolean(5, t.getIsEnable());
            ps.setLong(6, t.getId());
            
            status=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(Comment t) {
        int status=0;
        Connection connection =null;
        PreparedStatement ps=null;
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(DELTE_QUERY);
            ps.setLong(1, t.getId());
            
            status=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<Comment> selectAll() {
        List<Comment> list=new ArrayList<Comment>();
        Comment comment=new Comment();
        Connection connection =null;
        PreparedStatement ps=null;
        News news = new News();
        User user = new User();
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(SELECALL_QUERY);
                       
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                comment.setId(rs.getLong("Comment_Id"));
                comment.setComment(rs.getString("Comment"));
                comment.setTitle(rs.getString("Title"));
                comment.setIsEnable(rs.getBoolean("IsEnable"));
                user.setId(rs.getLong("User_Id"));
                news.setId(rs.getLong("News_Id"));
                comment.setNews(news);
                comment.setUser(user);
                
                list.add(comment);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Comment selectSingleById(Comment t) {
        Comment comment=new Comment();
        Connection connection =null;
        PreparedStatement ps=null;
        User user = new User();
        News news = new News();
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1,t.getId());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                comment.setId(rs.getLong("Comment_Id"));
                comment.setComment(rs.getString("Comment"));
                comment.setTitle(rs.getString("Title"));
                comment.setIsEnable(rs.getBoolean("IsEnable"));
                user.setId(rs.getLong("User_Id"));
                news.setId(rs.getLong("News_Id"));
                comment.setNews(news);
                comment.setUser(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return comment;
    }
    
}
