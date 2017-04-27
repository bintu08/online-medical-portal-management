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
public class PatientDatabase {
    
    public boolean newAccount(FormBeans form) {
        boolean flag=false;
        try{
        Connection connection=new DatabaseConnection().getConnection();
        
        PreparedStatement pstmt=connection.prepareStatement("insert into users(username,fullname,password,email,city,dob) values(?,?,?,?,?,?)");
        pstmt.setString(1, form.getUserName());
        pstmt.setString(2, form.getFullName());
        pstmt.setString(3, form.getPassword());
        pstmt.setString(4, form.getEmail());
        pstmt.setString(5, form.getCity());
        pstmt.setString(6, form.getDob());
        if(pstmt.executeUpdate()!=0){
            flag=true;
        }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return flag;
    }

    public boolean checkLogin(String userName, String password) {
        boolean loginAccess=false;
        try{
            Connection connetion=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connetion.prepareStatement("select username from users where username=? and password=?");
            
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            
            ResultSet res=pstmt.executeQuery();
            while(res.next()){
                loginAccess=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return loginAccess;
    }

    public ArrayList<FormBeans> searching(String keyword) {
        ArrayList<FormBeans> searchResult=null;
        Connection connection=new DatabaseConnection().getConnection();
        try{
        PreparedStatement pstmt=connection.prepareStatement("select* from doctors where fullname=? or department=? ");
        pstmt.setString(1, keyword);
        pstmt.setString(2, keyword);
        ResultSet result=pstmt.executeQuery();
        searchResult=new ArrayList<FormBeans>();
        while(result.next()){
            FormBeans form=new FormBeans();
            form.setDoctorName(result.getString("fullname"));
            form.setDepartment(result.getString("department"));
            form.setDoctorCity(result.getString("city"));
            form.setDoctorUserName(result.getString("username"));
            form.setExp(result.getInt("exp"));
            
            searchResult.add(form);
        }
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return searchResult;
    }

    public ArrayList<FormBeans> profile(String userName) {
        ArrayList<FormBeans> profile=new ArrayList<FormBeans>();
        try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("select* from users where username=?");
            pstmt.setString(1, userName);
            
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

    public ArrayList<FormBeans> patientRequests(String userName,String admin) {
        ArrayList<FormBeans> requests=new ArrayList<FormBeans>();
        try{
            PreparedStatement pstmt;
            Connection connection=new DatabaseConnection().getConnection();
            if(userName.equals("admin")){
               pstmt=connection.prepareStatement("select* from booking"); 
                
            }else if(admin.equals("admin")){
            pstmt=connection.prepareStatement("select* from booking where  doctorname=?");
            pstmt.setString(1, userName);
           
            }
            else{
                 pstmt=connection.prepareStatement("select* from booking where  username=?");
            pstmt.setString(1, userName);
            }
            ResultSet res=pstmt.executeQuery();
            while(res.next()){
                FormBeans form=new FormBeans();
                //here data retrive
                form.setUserName(res.getString("username"));
                form.setPatientName(res.getString("patient_name"));
                form.setDoctorName(res.getString("doctorname"));
                form.setProblem(res.getString("problem"));
                form.setAge(res.getInt("patient_age"));
                form.setCity(res.getString("city"));
                form.setDate(res.getString("date"));
                form.setStatus(res.getString("status"));
                form.setId(res.getInt("id"));
              //  form.setId(res.getInt("id"));
                
                
               
                
                requests.add(form);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return requests;
    }

    public ArrayList<FormBeans> doctors() {
       ArrayList<FormBeans> viewProfile=new ArrayList<FormBeans>();
        try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("select* from doctors where role='doctor'");
            
            
            ResultSet res=pstmt.executeQuery();
            while(res.next()){
                FormBeans form=new FormBeans();
                //here data retrive
                form.setDoctorUserName(res.getString("username"));
                form.setDoctorName(res.getString("fullname"));
                form.setDepartment(res.getString("department"));
                form.setDoctorCity(res.getString("city"));
                form.setExp(res.getInt("exp"));
              
                
                viewProfile.add(form);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return viewProfile;  
    }

    public boolean bookAppointment(FormBeans form,String username) {
        boolean isBookingSuccess=false;
          try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("insert into booking(username,doctorname,problem,date,patient_age,patient_name,city,status) values(?,?,?,?,?,?,?,?)");
            pstmt.setString(1, username);
            pstmt.setString(2, form.getDoctorName());
            pstmt.setString(3, form.getProblem());
            pstmt.setString (4, form.getDate());
            pstmt.setInt(5, form.getAge());
            pstmt.setString(6, form.getPatientName());
            pstmt.setString(7, form.getCity());
            pstmt.setString(8, "Waiting");
            
            if(pstmt.executeUpdate()!=0){
                isBookingSuccess=true;
            }
          }catch(Exception e){
              e.printStackTrace();
          }
        
        return isBookingSuccess;
                
                
    }

    public boolean Cancel(int id, String userName) {
         boolean isCancel=false;
         try {
            Connection con = new DatabaseConnection().getConnection();
           PreparedStatement pstmt = con.prepareStatement("update booking set status=? where id=? and username=?");

            pstmt.setString(1, "Cancelled");
            pstmt.setInt(2, id);
            pstmt.setString(3, userName);

            int i = pstmt.executeUpdate();

            if (i != 0) {
                isCancel = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCancel;
    }

    public boolean send(String fromUser, String toUser, String message) {
        boolean isSendSuccess=false;
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String time=sdf.format(timestamp);
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("insert into doctorsmessages(userid,patientid,message,date,pmessage)values(?,?,?,?,?)");
            pstmt.setString(1, toUser);
            pstmt.setString(2, fromUser);
            pstmt.setString(3, "..");
            pstmt.setString(4, time);
            pstmt.setString(5, message);
            
            if(pstmt.executeUpdate()!=0){
                isSendSuccess=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return isSendSuccess;
    }

    public ArrayList<FormBeans> RecentDoctors() {
         ArrayList<FormBeans> users=new ArrayList<FormBeans>();
    try{
        Connection connection=new DatabaseConnection().getConnection();
        PreparedStatement pstmt=connection.prepareStatement("select* from doctors where role='doctor'");
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

    public ArrayList<FormBeans> getMessages(String doctor, String uname) {
         ArrayList<FormBeans> messages=new ArrayList<FormBeans>();
        
        try{
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("select* from doctorsmessages where userid=? and patientid=?");
            pstmt.setString(1,doctor);
            pstmt.setString(2,uname);
            
            ResultSet res=pstmt.executeQuery();
            FormBeans form=new FormBeans();
            while(res.next()){
                
                form.setUserName(res.getString("patientid"));
                form.setDoctorUserName(res.getString("userid"));
                form.setDoctorName(res.getString("message"));
                form.setDate(res.getString("date"));
                messages.add(form);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public boolean send(String dname, String pname, String recieved, String send) {
          boolean isSendSuccess=false;
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String time=sdf.format(timestamp);
            Connection connection=new DatabaseConnection().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("insert into doctorsmessages(userid,patientid,message,date,pmessage)values(?,?,?,?,?)");
            pstmt.setString(1, dname);
            pstmt.setString(2, pname);
            pstmt.setString(3, recieved);
            pstmt.setString(4, time);
            pstmt.setString(5, send);
            
            if(pstmt.executeUpdate()!=0){
                isSendSuccess=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return isSendSuccess;
    }

   
    
}
