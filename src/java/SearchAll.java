/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hinal
 */
@Named(value = "searchAll")
@ManagedBean
@SessionScoped
    public class SearchAll implements Serializable {
 
     login lg = new login();
     
     public ArrayList getDisplayAll() throws Exception{
         ArrayList searchlist = new ArrayList();
          Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql = "Select user_id, first_name, last_name, age, gender, city from user_account where user_id !='"+login.ID+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
               User_Account u = new User_Account();
               u.setUser_id(rs.getString("user_id"));
               u.setFirst_name(rs.getString("first_name"));
               u.setLast_name(rs.getString("last_name"));
               u.setAge(rs.getInt("age"));
               u.setGender(rs.getString("gender"));
               u.setCity(rs.getString("city"));
               searchlist.add(u);
            }            
        } catch (Exception e) {
            e.getMessage();
        }
        finally{
            con.close();
            st.close();
        }
         
         return searchlist;
     }
    

    
    
   
}
