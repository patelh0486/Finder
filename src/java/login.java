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

public class login
{

    /**
     * Creates a new instance of login
     */
    
    private static String id;
    private String psw;
    public static String ID;
    private String oldpsw;
    private String newpsw;
    private String answer;
         private static String input;
 private static String user_id;
    private static String first_name; 
    private static String last_name;
    private static String gender;
    private static int age;
    private static String city;
    private static String interests1;
    private static String interests2;
    private static String interests3;
    private static String occupation;
    private static String bio;

    public  String getInput() {
        return input;
    }

    public  void setInput(String input) {
        login.input = input;
    }

    public  String getUser_id() {
        return user_id;
    }

    public  void setUser_id(String user_id) {
        login.user_id = user_id;
    }

    public  String getFirst_name() {
        return first_name;
    }

    public  void setFirst_name(String first_name) {
        login.first_name = first_name;
    }

    public  String getLast_name() {
        return last_name;
    }

    public  void setLast_name(String last_name) {
        login.last_name = last_name;
    }

    public  String getGender() {
        return gender;
    }

    public  void setGender(String gender) {
        login.gender = gender;
    }

    public  int getAge() {
        return age;
    }

    public  void setAge(int age) {
        login.age = age;
    }

    public  String getCity() {
        return city;
    }

    public  void setCity(String city) {
        login.city = city;
    }

    public  String getInterests1() {
        return interests1;
    }

    public  void setInterests1(String interests1) {
        login.interests1 = interests1;
    }

    public  String getInterests2() {
        return interests2;
    }

    public  void setInterests2(String interests2) {
        login.interests2 = interests2;
    }

    public  String getInterests3() {
        return interests3;
    }

    public  void setInterests3(String interests3) {
        login.interests3 = interests3;
    }

    public  String getOccupation() {
        return occupation;
    }

    public  void setOccupation(String occupation) {
        login.occupation = occupation;
    }

    public  String getBio() {
        return bio;
    }

    public  void setBio(String bio) {
        login.bio = bio;
    }

    
    
    public  String getID() {
        return ID;
    }

    public  void setID(String ID) {
        login.ID = ID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getOldpsw() {
        return oldpsw;
    }

    public void setOldpsw(String oldpsw) {
        this.oldpsw = oldpsw;
    }

    public String getNewpsw() {
        return newpsw;
    }

    public void setNewpsw(String newpsw) {
        this.newpsw = newpsw;
    }

    public String signin()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("error.xhtml");
        }
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        try
        {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            rs = st.executeQuery("select * from user_account where user_id = '"+ id +"' and password = '"+ psw +"'");
            if(rs.next())
            {
                ID = id;
                 interests1 = rs.getString("interests1");
                  interests2 = rs.getString("interests2");
                   interests3 = rs.getString("interests3");
                occupation = rs.getString("occupation");
                 city = rs.getString("city");
                  first_name = rs.getString("first_name");
                   last_name = rs.getString("last_name");
                    bio = rs.getString("Bio");
                 return "LoginWelcome.xhtml";
            }
            else
            {
                
            return "loginFail.xhtml";
            
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
     //update Profile
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
                        + "first_name = '"+ first_name +"', "
                        + "last_name = '"+ last_name +"', "
                        + "city = '"+ city +"', "
                        + "age = '"+ age +"', "
                        + "interests1 = '"+ interests1 +"', "
                        + "interests2 = '"+ interests2 +"', "
                        + "interests3 = '"+ interests3 +"', "
                        + "occupation = '"+ occupation +"', "
                        + "Bio = '"+ bio +"' where user_id = '"+ id +"'");
                
                
            return "success.xhtml";
    
        
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
    public String logout() 
    {
         String s = DateTimeStamp.DateTime();
                
                try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("error.xhtml");
        }
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
            try
            {
                con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
                //update the TimeStamp
                int r = st.executeUpdate("Update user_account set "
                        + "last_login = '" + s + "'"
                        + "where user_id = '"
                        + id + "'");
              
                con.commit();
                con.setAutoCommit(true);
                
                
                
            }
            catch (SQLException e)
            {
                //handle the exception
                e.printStackTrace();
            }
            finally
            {
                //close the database
                try
                {
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            
            return "Logout.xhtml";    
                
        
    }
    
    
    public String reset()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("Error.xhtml");
        }
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        try
        {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            rs = st.executeQuery("select * from user_account where user_id = '"+ id +"' and password = '"+oldpsw +"'");
            
            if(rs.next())
            {
                int r = st.executeUpdate("update user_account set password = '"+newpsw+"' where user_id = '"+id+"'");
                 return ("newPasswordConfirm.xhtml");
            }
            else
            {                
            return ("Error.xhtml");
            
            }
            
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
}
