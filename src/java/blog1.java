/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hinal
 */
@ManagedBean
@SessionScoped
public class blog1 {

    private String user_id;
    private String blogimage;
    private String subject;
    private String blogbody;
    private Boolean Blogwritten = false;

    public Boolean getBlogwritten() {
        return Blogwritten;
    }

    public void setBlogwritten(Boolean Blogwritten) {
        this.Blogwritten = Blogwritten;
    }
            
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBlogimage() {
        return blogimage;
    }

    public void setBlogimage(String blogimage) {
        this.blogimage = blogimage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBlogbody() {
        return blogbody;
    }

    public void setBlogbody(String blogbody) {
        this.blogbody = blogbody;
    }
    
    
    public String WriteBlog(){
        blogimage = "null";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            return("internalError.xhtml");
        }
        Connection con = null;
        Statement st = null;
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try{
            con = DriverManager.getConnection(DB_URL,"patelh0486","1839363");
            st = con.createStatement();
            int r = st.executeUpdate("insert into blogtable values('"+user_id+"', '"+blogimage+"', '"+subject+"', '"+blogbody+"')");
             Blogwritten = true;
           return(null);
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return("blogFail.xhtml");
        }
        finally{
            try{
                con.close();
                st.close();
                Reset();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    
    }
    
     public void Reset(){
     setUser_id(null);
     setSubject(null);
     setBlogbody(null);
 }
    
}
