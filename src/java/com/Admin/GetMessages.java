/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Admin;

import com.Database.AdminDatabase;
import com.Database.FormBeans;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
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
public class GetMessages extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String fromuser=request.getParameter("username");
    
    HttpSession session=request.getSession();
    String uname=(String)session.getAttribute("userName");
    System.out.print(fromuser);
    ArrayList<FormBeans> messages=new AdminDatabase().getMessages(fromuser,uname);
     //HashSet<FormBeans> recent= new AdminDatabase().RecentUsers(uname);
        ArrayList<FormBeans> recent= new AdminDatabase().RecentUsers(uname);
     
       String path="";
        if(!messages.isEmpty()){
            System.out.print("------");
            request.setAttribute("recent", recent);  
        
      
        
            System.out.print("------");
            request.setAttribute("message", messages);
            path="./adminRecieved1.jsp";
        }else{
              request.setAttribute("recent", recent);  
              path="./adminRecieved.jsp";
        }
        //request.setAttribute("messages", messages);
        RequestDispatcher dis=request.getRequestDispatcher(path);
        dis.forward(request, response);
    
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
