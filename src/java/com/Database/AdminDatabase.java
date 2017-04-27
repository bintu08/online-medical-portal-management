/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ramakrishna
 */
public class AdminDatabase {

    public boolean checkLogin(String userName, String password) {
        boolean loginAccess=false;
        try{
        Connection connection=new DatabaseConnection().getConnection();
        PreparedStatement pstmt=connection.prepareStatement("select username from doctors where username=? and password=?");
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        
        FormBeans form=new FormBeans();
        ResultSet res=pstmt.executeQuery();
        while(res.next()){
            form.setUserName(res.getString("username"));
            loginAccess=true;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return loginAccess;
    }

    public boolean addDoctor(FormBeans form) {
        boolean addDoctors=false;
        try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("insert into doctors(fullname,username,password,department,city,exp) values(?,?,?,?,?,?)");
            pstmt.setString(1, form.getDoctorName());
            pstmt.setString(2, form.getDoctorUserName());
            pstmt.setString(3, form.getDoctorPassword());
            pstmt.setString(4, form.getDepartment());
            pstmt.setString(5, form.getDoctorCity());
            pstmt.setInt(6, form.getExp());
            
            if(pstmt.executeUpdate()!=0){
                addDoctors=true;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return addDoctors;
    }

    ArrayList<FormBeans> users() {
          ArrayList<FormBeans> profile=new ArrayList<FormBeans>();
        try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("select* from users");
          
            
            ResultSet res=pstmt.executeQuery();
            while(res.next()){
                FormBeans form=new FormBeans();
                
                form.setFullName(res.getString("fullname"));
                form.setUserName(res.getString("username"));
                form.setCity(res.getString("city"));
                form.setEmail(res.getString("email"));
                form.setDob(res.getString("dob"));
                
                profile.add(form);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return profile;
    }

    public boolean Accept(int id, String userName) {
        boolean isAccept=false;
         try {
            Connection con = new DatabaseConnection().getConnection();
           PreparedStatement pstmt = con.prepareStatement("update booking set status=? where id=? and doctorname=?");

            pstmt.setString(1, "Accepted");
            pstmt.setInt(2, id);
            pstmt.setString(3, userName);

            int i = pstmt.executeUpdate();

            if (i != 0) {
                isAccept = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAccept;
    }

    public ArrayList<FormBeans> RecentUsers(String userName) {
    ArrayList<FormBeans> users=new ArrayList<FormBeans>();
    try{
        Connection connection=new DatabaseConnection().getConnection();
        PreparedStatement pstmt=connection.prepareStatement("select* from users");
      //  pstmt.setString(1, userName);
        ResultSet res=pstmt.executeQuery();
        
        while(res.next()){
            FormBeans form=new FormBeans();
         
            form.setUserName(res.getString("username"));
            users.add(form);
         
            
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    return users;
    }

    public ArrayList<FormBeans> getMessages(String fromuser, String uname) {
        ArrayList<FormBeans> messages=new ArrayList<FormBeans>();
        
        try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("select* from doctorsmessages where userid=? and patientid=?");
            pstmt.setString(1,uname);
            pstmt.setString(2,fromuser);
            
            ResultSet res=pstmt.executeQuery();
            FormBeans form=new FormBeans();
            while(res.next()){
                
                form.setUserName(res.getString("patientid"));
                form.setDoctorUserName(res.getString("userid"));
                form.setDoctorName(res.getString("pmessage"));
                form.setDate(res.getString("date"));
                messages.add(form);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public boolean send(String pname, String dname, String received,String send) {
         boolean isSendSuccess=false;
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String time=sdf.format(timestamp);
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("insert into doctorsmessages(userid,patientid,message,date,pmessage)values(?,?,?,?,?)");
            pstmt.setString(1, dname);
            pstmt.setString(2, pname);
            pstmt.setString(3, send);
            pstmt.setString(4, time);
            pstmt.setString(5, received);
            
            if(pstmt.executeUpdate()!=0){
                isSendSuccess=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return isSendSuccess;
    }

    public boolean Consult(int id, String userName) {
         boolean isAccept=false;
         try {
            Connection con = new DatabaseConnection().getConnection();
           PreparedStatement pstmt = con.prepareStatement("update booking set status=? where id=? and doctorname=?");

            pstmt.setString(1, "Consulted and Continue");
            pstmt.setInt(2, id);
            pstmt.setString(3, userName);

            int i = pstmt.executeUpdate();

            if (i != 0) {
                isAccept = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAccept;
    }

 
    
}
