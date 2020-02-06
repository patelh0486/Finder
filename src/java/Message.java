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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author patelh0486
 */
@Named(value = "message")
@SessionScoped
@ManagedBean
public class Message  implements Serializable {

    private String sender;
    private String receiver;
    private String first_name;
    private String last_name;
    private String messageDet;
    private String repliedmessage;
    private Timestamp  Date_Time;
    private String MessageStatus;
    private Boolean msgDelete = false;

    public String validateFriends(){
        boolean isFound = false;
        try {
            SendRequest sf = new SendRequest();
            if(sf.getFriends().contains(receiver)){
                isFound = true;
                return("valid ID");
            }
            else{
                return("Not a valid ID");
            }
        } catch (Exception ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return("valid ID");
    }
    public String InsertMessage(){
        MessageStatus ="Your Message has been sent";
          try{
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            return("internalError.xhtml");
        }
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try{
            con = DriverManager.getConnection(DB_URL,"patelh0486","1839363");
            st = con.createStatement();
             rs = st.executeQuery("select Receiver_Id  from friendrequest where (Receiver_id = '" + receiver +"' AND Sender_Id = '"+ login.ID +"') OR (Receiver_id = '" + login.ID +"' AND Sender_Id = '"+ receiver +"')  ");
            if(rs.next()){
            int r = st.executeUpdate("Insert into messagetable values( '"+ login.ID +"', '"+receiver+"', '"+ messageDet+"', CURRENT_TIMESTAMP,'pending')");
            return (MessageStatus);
            }
            else{
           return("blogFail.xhtml");
    
             }
   
         
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
    
    public ArrayList getShowMessage() throws Exception{
        ArrayList messagelist = new  ArrayList();
         Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql = "select  M.Sender, first_name, last_name, Message from  messagetable m, user_account u where m.Sender = u.user_id and  MessageStatus = 'pending' AND Receiver = '" +login.ID+"' ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
            Message m = new Message();
            m.setSender(rs.getString("Sender"));
            m.setFirst_name(rs.getString("first_name"));
            m.setLast_name(rs.getString("last_name"));
            m.setMessageDet(rs.getString("Message"));
             messagelist.add(m);
           
            
            }            
        } catch (Exception e) {
            e.getMessage();
           
        }
        finally{
            con.close();
            st.close();
        }        
        
        return messagelist;        
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public String UpdateReply(String sender){
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
            int r = st.executeUpdate("update messagetable set MessageStatus = 'replied' where receiver = '"+login.ID+"' and sender = '"+ sender+"';");
            msgDelete = true;
            return ("MessageNotification");
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
    
    public String ReplyMessage(){
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
            int r = st.executeUpdate("Insert into messagetable values( '"+ login.ID +"', '"+receiver+"', '"+ messageDet+"', CURRENT_TIMESTAMP,'pending')");
              return ("MessageNotification");
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
    
      public ArrayList getOldMessage() throws Exception{
        ArrayList oldlist = new  ArrayList();
         Connection con =null;
        Statement st = null;
        ResultSet rs = null;
        final String url ="jdbc:mysql://mis-sql.uhcl.edu/patelh0486?useSSL=false";
        
        try {
            con = DriverManager.getConnection(url,"patelh0486","1839363");
            st = con.createStatement();
            
            String sql = "select  M.Sender, first_name, last_name, Message, Date_Time from  messagetable m, user_account u where m.Sender = u.user_id and  MessageStatus = 'replied' AND Receiver = '" +login.ID+"' ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
            Message m = new Message();
            m.setSender(rs.getString("Sender"));
            m.setFirst_name(rs.getString("first_name"));
            m.setLast_name(rs.getString("last_name"));
            m.setMessageDet(rs.getString("Message"));
            m.setDate_Time(rs.getTimestamp("Date_Time"));
             oldlist.add(m);
           
            
            }            
        } catch (Exception e) {
            e.getMessage();
           
        }
        finally{
            con.close();
            st.close();
        }        
        
        return oldlist;        
    }
    
 public void Reset(){
     setReceiver(null);
     setMessageDet(null);
 }

    public Boolean getMsgDelete() {
        return msgDelete;
    }

    public void setMsgDelete(Boolean msgDelete) {
        this.msgDelete = msgDelete;
    }
 
    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public String getMessageDet() {
        return messageDet;
    }

    public void setMessageDet(String messageDet) {
        this.messageDet = messageDet;
    }

    public Timestamp getDate_Time() {
        return Date_Time;
    }

    public void setDate_Time(Timestamp Date_Time) {
        this.Date_Time = Date_Time;
    }

   

    public String getMessageStatus() {
        return MessageStatus;
    }

    public void setMessageStatus(String MessageStatus) {
        this.MessageStatus = MessageStatus;
    }
    
    
    
    
}
