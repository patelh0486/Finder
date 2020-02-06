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
import javax.inject.Named;
import java.util.ArrayList;


import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;


/**
 *
 * @author mohan
 */
@Named(value = "top3")
@ManagedBean
@SessionScoped
public class top3 extends User_Account implements Serializable  {
    
     //*******For storing search result
    public ArrayList<User_Account>specificList ;

    public ArrayList<User_Account> getSpecificList() {
        return specificList;
    }

    public void setSpecificList(ArrayList<User_Account> specificList) {
        this.specificList = specificList;
    }

    public User_Account getUser() {
        return user;
    }

    public void setUser(User_Account user) {
        this.user = user;
    }
    //*******For viewing a user's profile
    private User_Account user;
    
 
   

    

    
        login lg=new login();
   
   
    
    public ArrayList getTopM() throws Exception{
        ArrayList topList = new ArrayList();
        Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql = "Select user_id, first_name, last_name, age, gender, city, ViewsCount from user_account u where user_id!='"+lg.getId()+"'"
                          + "and 3 > (select count(distinct q.ViewsCount) from user_account q where u.ViewsCount<q.ViewsCount and q.gender='m') and gender='m' ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                User_Account sp = new User_Account();
                sp.setUser_id(rs.getString("user_id"));
                sp.setFirst_name(rs.getString("first_name"));
                sp.setLast_name(rs.getString("last_name"));
                sp.setGender(rs.getString("gender"));
                sp.setAge(rs.getInt("age"));                
                sp.setCity(rs.getString("city"));
                topList.add(sp);
            }            
        } catch (Exception e) {
            e.getMessage();
        }
        finally{
            con.close();
            st.close();
        }
        return topList;
    }
     public ArrayList getTopF() throws Exception{
        ArrayList botList = new ArrayList();
        Connection con=null;
        Statement st =null;
        ResultSet rs=null ;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql ="Select user_id, first_name, last_name, age, gender, city, ViewsCount from user_account u where user_id!='"+lg.getId()+"'"
                    + "and 3 > (select count(distinct q.ViewsCount) from user_account q where u.ViewsCount<q.ViewsCount and q.gender='f') and gender='f' ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                User_Account sp = new User_Account();
                sp.setUser_id(rs.getString("user_id"));
                sp.setFirst_name(rs.getString("first_name"));
                sp.setLast_name(rs.getString("last_name"));
                sp.setGender(rs.getString("gender"));
                sp.setAge(rs.getInt("age"));                
                sp.setCity(rs.getString("city"));
                botList.add(sp);
            }            
        } catch (Exception e) {
            e.getMessage();
        }
        finally{
            con.close();
            st.close();
        }
        return botList;
    }

    
   
    public String search() {
        
        Connection con =null;
        Statement st = null;
        ResultSet rs =null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();

                      if(input.equals("any") && input3.equals("any") && input4.equals("any")){
              rs = st.executeQuery("select  user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where (age between '" + input1 + "' AND '" + input2 + "') AND user_id != '"+ lg.getId()+"'");
            }
            else if(input.equals("any") && input4.equals("any")){
              rs = st.executeQuery("select  user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where  city = '"+input3+"' and (age between '" + input1 + "' AND '" + input2 + "') AND user_id != '"+ lg.getId()+"'");
            }
           else if(input3.equals("any") && input4.equals("any")){
              rs = st.executeQuery("select  user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where gender = '"+input+"' and (age between '" + input1 + "' AND '" + input2 + "') AND user_id != '"+ lg.getId()+"'");
            }
            else if(input.equals("any") && input3.equals("any")){
              rs = st.executeQuery("select  user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where (interests1 = '"+input4+"' or interests2 = '"+input4+"' or interests3 = '"+input4+"') and (age between '" + input1 + "' AND '" + input2 + "') AND user_id != '"+ lg.getId()+"'");
            }
            else if(input4.equals("any")){
             rs = st.executeQuery("select user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where gender = '"+input+"' AND  Age between '" + input1 + "' AND '" + input2 + "' AND city = '"+input3+"' AND user_id != '"+ lg.getId()+"'"); 
          }
          else if(input3.equals("any")){
              rs = st.executeQuery("select user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where gender = '"+input+"' AND  Age between '" + input1 + "' AND '" + input2 + "' And (interests1 = '"+input4+"' or interests2 = '"+input4+"' or interests3 = '"+input4+"') AND user_id != '"+ lg.getId()+"'");
          }
          
          else if(input.equals("any")){
              rs = st.executeQuery("select user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3 from user_account where  Age between '" + input1 + "' AND '" + input2 + "' AND city = '"+input3+"' And (interests1 = '"+input4+"' or interests2 = '"+input4+"' or interests3 = '"+input4+"') AND user_id != '"+ lg.getId()+"'");
          }
          
            else{String sql = "Select user_id, first_name, last_name, age, gender, city,interests1,interests2,interests3 from user_account where user_id!='"+lg.getId()+"' and gender='"+input+"' and city='"+input3+"'and (age between '"+input1+"' and '"+input2+"' ) and(interests1='"+input4+"' or interests2 ='"+input4+"' or interests3 ='"+input4+"')";
            rs = st.executeQuery(sql);}
            specificList =new ArrayList<>();
            boolean found =false;
            while (rs.next()) {
                found =true;
                User_Account sp = new User_Account();
                sp.setUser_id(rs.getString("user_id"));
                sp.setFirst_name(rs.getString("first_name"));
                sp.setLast_name(rs.getString("last_name"));
                sp.setGender(rs.getString("gender"));
                sp.setAge(rs.getInt("age"));                
                sp.setCity(rs.getString("city"));
                sp.setInterests1(rs.getString("interests1"));
                sp.setInterests2(rs.getString("interests2"));
                sp.setInterests3(rs.getString("interests3"));
                
                specificList.add(sp);
            }  
             if(found){
                return "searchResult.xhtml";
            }
            else{
                return "noUsersFound.xhtml";
            }
        } catch (Exception e) {
            e.getMessage();
            return "error.xhtml";
        }
       
        
    }
    //For viewing the profile
    public ArrayList getProfile() throws Exception
    {
          ArrayList botList1 = new ArrayList();
        Connection con=null;
        Statement st =null;
        ResultSet rs=null ;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
           String sql ="Select * from user_account  where user_id ='"+lg.getId()+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                User_Account sp = new User_Account();
                sp.setUser_id(rs.getString("user_id"));
                sp.setFirst_name(rs.getString("first_name"));
                sp.setLast_name(rs.getString("last_name"));
                sp.setGender(rs.getString("gender"));
                sp.setAge(rs.getInt("age"));                
                sp.setCity(rs.getString("city"));
                sp.setInterests1(rs.getString("interests1"));
                sp.setInterests2(rs.getString("interests2"));
                sp.setInterests3(rs.getString("interests3"));
                sp.setOccupation(rs.getString("occupation"));
                sp.setBio(rs.getString("Bio"));
                botList1.add(sp);
            }            
        } catch (Exception e) {
            e.getMessage();
        }
        finally{
            con.close();
            st.close();
        }
        return botList1;
    }
    
   public String viewProfile(User_Account user, String componentID) {
        
        Connection con =null;
        Statement st = null;
        ResultSet rs =null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
         specificList = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
           rs = st.executeQuery("Select * from user_account where user_id = '" +user.getUser_id()+ "'");
           boolean found = false;
           if(rs.next()){
               found = true;
              User_Account sp = new User_Account();
                sp.setUser_id(rs.getString("user_id"));
                sp.setFirst_name(rs.getString("first_name"));
                sp.setLast_name(rs.getString("last_name"));
                sp.setGender(rs.getString("gender"));
                sp.setAge(rs.getInt("age"));                
                sp.setCity(rs.getString("city"));
                sp.setInterests1(rs.getString("interests1"));
                sp.setInterests2(rs.getString("interests2"));
                sp.setInterests3(rs.getString("interests3"));
                sp.setOccupation(rs.getString("occupation"));
                sp.setBio(rs.getString("Bio"));
                sp.setLast_login(rs.getString("last_login"));
                specificList.add(sp);
               
              
           }
           if(found && "submitRequest".equalsIgnoreCase(componentID)){
               return "profile.xhtml";
           }
           //To view the profile of the user who sent the friend request. component is the button id
           else if(found && "submitRequest".equalsIgnoreCase(componentID)){
               return "viewRequestProfile.xhtml?faces-redirect=true";
           }
           else{
               return "noUsersFound.xhtml";
           }
        } catch (Exception e) {
            e.getMessage();
            return "error.xhtml";
        }
        
         
    }
     
    
}

