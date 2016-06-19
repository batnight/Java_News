
package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LikeNewsDao implements IDao<LikeNews>{
    private final String SAVE_QUERY="INSERT INTO public.\"LikeNews\" (\"User_Id\", \"News_Id\") VALUES (?, ?);";
    private final String UPDATE_QUERY="UPDATE public.\"LikeNews\" SET \"LikeNews_Id\"=?, \"User_Id\"=?, \"News_Id\"=? WHERE \"LikeNews_Id\"=?;";
    private final String DELTE_QUERY="DELETE FROM public.\"LikeNews\" WHERE \"LikeNews_Id\"=?;";
    private final String SELECALL_QUERY="SELECT * FROM public.\"LikeNews\";";
    private final String SELECTSINGLEBYID_QUERY="SELECT * FROM public.\"LikeNews\" WHERE \"LikeNews_Id\"=?;";
    @Override
    public int save(LikeNews t) {
        int status=0;
        Connection connection=null;
        PreparedStatement ps=null;
        try{
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getUser().getId());
            ps.setLong(2, t.getNews().getId());
            
            status=ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeNewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(LikeNews t) {
        int status=0;
        Connection connection =null;
        PreparedStatement ps=null;
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getUser().getId());
            ps.setLong(2, t.getNews().getId());
            
            status=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeNewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(LikeNews t) {
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
                Logger.getLogger(LikeNewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<LikeNews> selectAll() {
        List<LikeNews> list=new ArrayList<LikeNews>();
        LikeNews likeNews=new LikeNews();
        Connection connection =null;
        PreparedStatement ps=null;
        User user = new User();
        News news = new News();
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(SELECALL_QUERY);
                       
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                likeNews.setId(rs.getLong("LikeNews_Id"));
                user.setId(rs.getLong("User_Id"));
                news.setId(rs.getLong("News_Id"));
                likeNews.setNews(news);
                likeNews.setUser(user);
                
                list.add(likeNews);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeNewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public LikeNews selectSingleById(LikeNews t) {
        LikeNews likeNews=new LikeNews();
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
                likeNews.setId(rs.getLong("LikeNews_Id"));
                user.setId(rs.getLong("User_Id"));
                news.setId(rs.getLong("News_Id"));
                likeNews.setNews(news);
                likeNews.setUser(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LikeNewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return likeNews;
    }
    
}
