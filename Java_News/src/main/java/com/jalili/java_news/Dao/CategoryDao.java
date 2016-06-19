
package com.jalili.java_news.Dao;

import com.jalili.java_news.Dao.Util.DaoConfiguration;
import com.jalili.java_news.Dao.Util.IDao;
import com.jalili.java_news.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao implements IDao<Category>{
    private final String SAVE_QUERY="INSERT INTO public.\"Category\"(\"Category_Name\") VALUES (?);";
    private final String UPDATE_QUERY="UPDATE public.\"Category\" SET \"Category_Name\"=? WHERE \"Category_ID\"=?;";
    private final String DELTE_QUERY="DELETE FROM public.\"Category\" WHERE \"Category_ID\"=?;";
    private final String SELECALL_QUERY="SELECT * FROM public.\"Category\";";
    private final String SELECTSINGLEBYID_QUERY="SELECT * FROM public.\"Category\" WHERE \"Category_ID\"=?;";
    @Override
    public int save(Category t) {
        int status=0;
        Connection connection=null;
        PreparedStatement ps=null;
        try{
            connection = DaoConfiguration.getConnection();
            ps = connection.prepareStatement(SAVE_QUERY);
            ps.setString(1, t.getCategoryName());
            
            status=ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int update(Category t) {
        int status=0;
        Connection connection =null;
        PreparedStatement ps=null;
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(UPDATE_QUERY);
            ps.setString(1, t.getCategoryName());
            
            status=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public int delete(Category t) {
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
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public List<Category> selectAll() {
        List<Category> list=new ArrayList<Category>();
        Category category=new Category();
        Connection connection =null;
        PreparedStatement ps=null;
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(SELECALL_QUERY);
                       
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                category.setId(rs.getLong("Category_Id"));
                category.setCategoryName(rs.getString("Category_Name"));
                
                list.add(category);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Category selectSingleById(Category t) {
        Category category=new Category();
        Connection connection =null;
        PreparedStatement ps=null;
        try{
            connection=DaoConfiguration.getConnection();
            ps=connection.prepareStatement(SELECTSINGLEBYID_QUERY);
            ps.setLong(1, t.getId());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                category.setId(rs.getLong("Category_Id"));
                category.setCategoryName(rs.getString("Category_Name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return category;
    }
    
}
