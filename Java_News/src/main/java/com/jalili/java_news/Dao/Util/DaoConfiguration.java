
package com.jalili.java_news.Dao.Util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DaoConfiguration {
    public static  Connection getConnection() {
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbNews","postgres","90630690");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
