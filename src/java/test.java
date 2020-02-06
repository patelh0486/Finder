/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class test {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    private String id;
    private String name;
    private String gender;
    private String fname;
    private String lname;
    private int age;
    private String city;
    private String int1;
    private String int2;
    private String int3;
    private String occ;
    private String bio;
    private String que;
    private String ans;

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInt1() {
        return int1;
    }

    public void setInt1(String int1) {
        this.int1 = int1;
    }

    public String getInt2() {
        return int2;
    }

    public void setInt2(String int2) {
        this.int2 = int2;
    }

    public String getInt3() {
        return int3;
    }

    public void setInt3(String int3) {
        this.int3 = int3;
    }

    public String getOcc() {
        return occ;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    private String psw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
/*This method is used to check the user id and if its not in the database 
    then it will create a new one*/
    //Registration-Part 1 //
    public String test1() 
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("Loading Driver Unsuccessful");
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
                 return "testing.xhtml";
            }
            else
            {
                int r = st.executeUpdate("insert into user_account values"
                        + "('"+ id+"','','','"+gender+"',0,'','','','','"+psw+"','',0,0,'','','','"+que+"','"+ans+"')");
            return "signUp2.xhtml";
            
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "Something Wrong!";
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

    //Registration-Part 1
    
    public String test2()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("Loading Driver Unsuccessful");
        }
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        try
        {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
                int r = st.executeUpdate("update user_account set "
                        + "first_name = '"+ fname +"', "
                        + "last_name = '"+ lname +"', "
                        + "gender = '"+ gender +"', "
                        + "city = '"+ city +"', "
                        + "age = '"+ age +"', "
                        + "interests1 = '"+ int1 +"', "
                        + "interests2 = '"+ int2 +"', "
                        + "interests3 = '"+ int3 +"', "
                        + "occupation = '"+ occ +"', "
                        + "sec_que = '"+ que +"', "
                        + "sec_ans = '"+ ans +"', "
                        + "Bio = '"+ bio +"' where user_id = '"+ id +"'");
                
                
            return "signup3.xhtml";
           
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "Error.xhtml";
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
