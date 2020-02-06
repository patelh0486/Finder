/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hinal
 */
@Named(value = "sendRequest")
@ManagedBean
@SessionScoped
public class SendRequest implements Serializable {

      private String sendID;
    private String receivID;
    private String status;
    private String first_name;
    private String last_name;
    private String user_id;
    
    private boolean reqSent = false;
    private boolean reqNotSent = false;
    
    public String getRID() {
        return user_id;
    }

    public void setRID(String RID) {
        this.user_id = RID;
    }

    public String getSendID() {
        return sendID;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public String getReceivID() {
        return receivID;
    }

    public void setReceivID(String receivID) {
        this.receivID = receivID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public login getLg() {
        return lg;
    }

    public void setLg(login lg) {
        this.lg = lg;
    }

    public boolean isReqSent() {
        return reqSent;
    }

    public void setReqSent(boolean reqSent) {
        this.reqSent = reqSent;
    }

    public boolean isReqNotSent() {
        return reqNotSent;
    }

    public void setReqNotSent(boolean reqNotSent) {
        this.reqNotSent = reqNotSent;
    }
    
    
    
    
    login lg = new login();
    
    public String ValidateRequest(String receivID){
         try{
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e){
            
            e.printStackTrace();
           
        }
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try{
            con = DriverManager.getConnection(DB_URL,"patelh0486","1839363");
            st = con.createStatement();
            rs = st.executeQuery("select Receiver_Id, Sender_Id from friendrequest where (Receiver_id = '" + receivID +"' AND Sender_Id = '"+ login.ID +"') OR (Receiver_id = '" + login.ID +"' AND Sender_Id = '"+ receivID +"')  ");
            if(rs.next()){
                reqNotSent = true;
               return("SearchAll.xhtml");
            }
            else{
            InsertRequest(receivID);
                        }
        }
        catch(SQLException e){
            e.printStackTrace();
             
        }
        finally{
            try{
                con.close();
                st.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                
            }
            
        }
      return("SearchAll.xhtml");
    }
    
    
    public String InsertRequest(String receivID){
        
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
           
             int r = st.executeUpdate("insert into friendrequest values( '"+receivID+"','"+login.ID+"','pending' )");
             reqSent = true;
            return ("SearchAll.xhtml");
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return("blogFail.xhtml");
        }
        finally{
            try{
                con.close();
                st.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    
    }
    
 public ArrayList getShowRequest() throws Exception{
         ArrayList requestlist = new ArrayList();
          Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql = "select  f.Sender_id, first_name, last_name, status from  friendrequest f, user_account u where f.Sender_id = u.user_id and  status = 'pending' AND Receiver_id = '" +login.ID+"' ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
             SendRequest r = new SendRequest();
             r.setSendID(rs.getString("Sender_id"));
             r.setFirst_name(rs.getString("first_name"));
             r.setLast_name(rs.getString("last_name"));
             r.setStatus(rs.getString("status"));
               
               requestlist.add(r);
            }            
        } catch (Exception e) {
            e.getMessage();
           
        }
        finally{
            con.close();
            st.close();
        }
         
         return requestlist;
     }
 
 public String Accept(String sendID){
    
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
            int r = st.executeUpdate(" update friendrequest set status = 'accepted' where  Receiver_Id = '" + login.ID+ "' and Sender_Id = '" + sendID +"'");
            return ("Notification");
        }
        catch(SQLException e){
            e.printStackTrace();
            return("blogFail.xhtml");
        }
        finally{
            try{
                con.close();
                st.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    
 }
    public String Reject(String sendID){
    
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
            int r = st.executeUpdate(" update friendrequest set status = 'rejected' where  Receiver_Id = '" + login.ID+ "' and Sender_Id = '" + sendID +"'");
            return ("Notification");
        }
        catch(SQLException e){
            e.printStackTrace();
            return("blogFail.xhtml");
        }
        finally{
            try{
                con.close();
                st.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    
 }
    
     public ArrayList getFriends() throws Exception{
         ArrayList friendlist = new ArrayList();
          Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
          rs = st.executeQuery("select f.Sender_Id, first_name, last_name from  friendrequest f, user_account a where f.Sender_Id = a.user_id and status = 'accepted' AND f.Receiver_id = '" + login.ID +"' UNION ALL select g.Receiver_Id, first_name, last_name from  friendrequest g, user_account u where g.Receiver_Id = u.user_id and status = 'accepted' AND g.Sender_id = '" + login.ID +"'  ");
          
       //  rs = st.executeQuery("select f.receiver_Id, first_name, last_name from  friendrequest f, user_account a where f.Receiver_Id = a.user_id and status = 'accepted' AND f.Sender_Id = '" + login.ID +"' ");
         
            while (rs.next()) {
          SendRequest sr = new SendRequest();
          sr.setSendID(rs.getString("Sender_Id"));
          sr.setFirst_name(rs.getString("first_name"));
          sr.setLast_name(rs.getString("last_name"));
          
          friendlist.add(sr);
            }            
        } catch (Exception e) {
            
            e.getMessage();
            
        }
        finally{
            con.close();
            st.close();
            
        }
         
         return friendlist;
     }
   
}
