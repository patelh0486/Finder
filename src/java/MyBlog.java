/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hinal
 */
@Named(value = "myBlog")
@ManagedBean
@SessionScoped
public class MyBlog extends blog1 implements Serializable{

     login lg = new login();
     
    public ArrayList getViewMyBlog() throws Exception{
        
        ArrayList mybloglist = new ArrayList();
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql = "Select Subject, BlogBody from blogtable where user_id = '"+login.ID+"' ";
            rs = st.executeQuery(sql);
            while(rs.next()){
                blog1 b = new blog1();
                b.setSubject(rs.getString("Subject"));
                b.setBlogbody(rs.getString("BlogBody"));
                mybloglist.add(b);
                
            }
                     
        } catch (Exception e) {
            e.getMessage();
        }
        finally{
            con.close();
            st.close();
            rs.close();
        }
            
            return mybloglist;
        }
    
    
}
