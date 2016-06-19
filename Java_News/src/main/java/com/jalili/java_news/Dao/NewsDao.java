package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsDao implements IDao<News> {

    private final String SAVE_QUERY = "INSERT INTO public.\"News\"(\n"
            + "            \"SubCategory_Id\", \"Title\", \"Preview_Text\", \"Context\", \n"
            + "            \"Image\", \"Date\", \"User_Id\")\n"
            + "    VALUES (?, ?, ?, ?, \n"
            + "            ?, ?, ?);";
    private final String UPDATE_QUERY = "UPDATE public.\"News\"\n"
            + "   SET \"SubCategory_Id\"=?, \"Title\"=?, \"Preview_Text\"=?, \n"
            + "       \"Context\"=?, \"Image\"=?, \"Date\"=?, \"User_Id\"=?\n"
            + " WHERE \"News_Id\"=?;";
    private final String DELTE_QUERY = "DELETE FROM public.\"News\"\n"
            + " WHERE \"News_Id\"=?;";
    private final String SELECALL_QUERY = "SELECT *\n"
            + "  FROM public.\"News\";";
    private final String SELECTSINGLEBYID_QUERY = "SELECT *\n"
            + "  FROM public.\"News\" WHERE \"News_Id\"=?;";

    @Override
    public int save(News t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        File file = null;
        FileInputStream fis = null;
        
        try {
            file = new File(t.getImage());
            fis = new FileInputStream(file);
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setLong(1, t.getSubCategory().getId());
            ps.setString(2, t.getTitle());
            ps.setString(3, t.getPreviewText());
            ps.setString(4, t.getContex());
            ps.setBinaryStream(5, fis, file.length());
            ps.setDate(6,t.getCreatedDate());
            ps.setLong(7, t.getUser().getId());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(News t) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        File file = null;
        FileInputStream fis = null;
        
        try {
            file = new File(t.getImage());
            fis = new FileInputStream(file);
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setLong(1, t.getSubCategory().getId());
            ps.setString(2, t.getTitle());
            ps.setString(3, t.getPreviewText());
            ps.setString(4, t.getContex());
            ps.setBinaryStream(5, fis, file.length());
            ps.setDate(6,t.getCreatedDate());
            ps.setLong(7, t.getUser().getId());
            ps.setLong(8, t.getId());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(News t) {
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
                Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<News> selectAll() {
        List<News> list = new ArrayList<News>();
        News news = new News();
        Connection connection = null;
        PreparedStatement ps = null;
        SubCategory sb = new SubCategory();
        User user = new User();
        byte[] image=null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECALL_QUERY);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sb.setId(rs.getLong("SubCategory_Id"));
                user.setId(rs.getLong("User_Id"));
                news.setId(rs.getLong("News_Id"));
                news.setSubCategory(sb);
                news.setUser(user);
                news.setContex(rs.getString("Context"));
                news.setTitle(rs.getString("Title"));
                news.setPreviewText(rs.getString("Preview_Text"));
                news.setCreatedDate(rs.getDate("Date"));
                image=rs.getBytes("Image");
                if(!image.equals(null)){
                    try{
                        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Mohammad\\Desktop\\News\\web\\img\\temp\\"
                                + rs.getString("Title")+".jpg"));
                        fos.write(image);
                        fos.flush();
                        fos.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public News selectSingleById(News t) {
        News news = new News();
        Connection connection = null;
        PreparedStatement ps = null;
        SubCategory sb = new SubCategory();
        User user = new User();
        byte[] image=null;
        try {
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1,t.getId());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sb.setId(rs.getLong("SubCategory_Id"));
                user.setId(rs.getLong("User_Id"));
                news.setId(rs.getLong("News_Id"));
                news.setSubCategory(sb);
                news.setUser(user);
                news.setContex(rs.getString("Context"));
                news.setTitle(rs.getString("Title"));
                news.setPreviewText(rs.getString("Preview_Text"));
                news.setCreatedDate(rs.getDate("Date"));
                image=rs.getBytes("Image");
                if(!image.equals(null)){
                    try{
                        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Mohammad\\Desktop\\News\\web\\img\\temp\\"
                                + rs.getString("Title")+".jpg"));
                        fos.write(image);
                        fos.flush();
                        fos.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return news;
    }

}
