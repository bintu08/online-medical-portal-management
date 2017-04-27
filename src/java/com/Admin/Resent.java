/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Admin;

import com.Database.AdminDatabase;
import com.Database.DatabaseConnection;
import com.Database.FormBeans;
import com.Database.PatientDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramakrishna
 */
public class Resent extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pname=request.getParameter("fromuser");
        String dname=request.getParameter("touser");
        String recieved=request.getParameter("message1");
        String send=request.getParameter("message");
         boolean isSend=new AdminDatabase().send(pname,dname,recieved,send);
       String path="";
       if(isSend){
           request.setAttribute("status", "Message sent Success..");
          ArrayList<FormBeans> messages=new AdminDatabase().getMessages(pname,dname);
    ArrayList<FormBeans> recent= new AdminDatabase().RecentUsers(dname);
     
        
            System.out.print("------");
            request.setAttribute("recent", recent);
     
            request.setAttribute("message", messages);
       }else{
           request.setAttribute("status", "Message Sending Failed..");
           ArrayList<FormBeans> messages=new AdminDatabase().getMessages(pname,dname);
             ArrayList<FormBeans> recent= new AdminDatabase().RecentUsers(dname);
     
       
        
            System.out.print("------");
            request.setAttribute("recent", recent);
           request.setAttribute("message", messages);
       }
       RequestDispatcher dis=request.getRequestDispatcher("adminRecieved1.jsp");
       dis.forward(request, response);
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
