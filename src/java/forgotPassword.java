/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author harsh
 */
@ManagedBean
@SessionScoped
public class forgotPassword implements Serializable
{

    /**
     * Creates a new instance of forgotPassword
     */
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    
    private String id;
    private String ans;
    private String psw;

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String forgot() 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("exError.xhtml");
        }
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        try
        {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            rs = st.executeQuery("select * from user_account where user_id = '"+ id +"'");
            if(rs.next())
            {
                 return (rs.getString(17));
            }
            else
            {                
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return ("exError.xhtml");
            
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "exError.xhtml";
       }
        finally
        {
            try
            {
                con.close();
                st.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public String forgot2()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("exError.xhtml");
        }
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        try
        {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            rs = st.executeQuery("select * from user_account where user_id = '"+ id +"' and sec_ans = '"+ans +"'");
            
            if(rs.next())
            {
                int r = st.executeUpdate("update user_account set password = '"+psw+"' where user_id = '"+id+"'");
                 return ("newPasswordSuccess.xhtml");
            }
            else
            {                
            return ("exError.xhtml");
            
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "exError.xhtml";
       }
        finally
        {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

            try
            {
                con.close();
                st.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
}
